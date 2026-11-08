package com.extendedfeatures.client.internal.logic.multiblock.fisher;

import com.google.common.collect.MapMaker;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("all")
public class FisherKelpRenderer extends DynamicRender<IWorkableMultiController, FisherKelpRenderer> {

    public static final Codec<FisherKelpRenderer> CODEC = RecordCodecBuilder.create(i -> i.group(
            Entry.CODEC.listOf().fieldOf("entries").forGetter(r -> r.entries),
            Codec.BOOL.optionalFieldOf("only_when_active", false).forGetter(r -> r.onlyWhenActive),
            Codec.FLOAT.optionalFieldOf("grow_seconds", 2.0f).forGetter(r -> r.growSeconds)
    ).apply(i, FisherKelpRenderer::new));

    public static final DynamicRenderType<IWorkableMultiController, FisherKelpRenderer> TYPE = new DynamicRenderType<>(CODEC);
    private static final Map<MultiblockControllerMachine, Anim> ANIMS = new MapMaker().weakKeys().makeMap();
    private static boolean warned = false;
    private final List<Entry> entries;
    private final boolean onlyWhenActive;
    private final float growSeconds;

    public FisherKelpRenderer(List<Entry> entries, boolean onlyWhenActive) {
        this(entries, onlyWhenActive, 2.0f);
    }
    public FisherKelpRenderer(List<Entry> entries, boolean onlyWhenActive, float growSeconds) {
        this.entries = List.copyOf(entries);
        this.onlyWhenActive = onlyWhenActive;
        this.growSeconds = growSeconds;
    }

    private static BlockPos toWorldOffset(MultiblockControllerMachine m, int front, int left, int up) {
        Direction f = m.getFrontFacing();
        Direction u = m.getUpwardsFacing();
        boolean flipped = m.isFlipped();
        return BlockPos.ZERO
                .relative(RelativeDirection.FRONT.getRelative(f, u, flipped), front)
                .relative(RelativeDirection.LEFT.getRelative(f, u, flipped), left)
                .relative(RelativeDirection.UP.getRelative(f, u, flipped), up);
    }

    private float updateProgress(MultiblockControllerMachine m, boolean target) {
        Anim a = ANIMS.computeIfAbsent(m, k -> new Anim());
        long now = System.nanoTime();
        float goal = target ? 1f : 0f;
        if (!a.initialized || growSeconds <= 0f) {
            a.progress = goal;
            a.initialized = true;
        } else {
            float dt = Math.min((now - a.lastNanos) / 1.0e9f, 0.1f);
            float step = dt / growSeconds;
            a.progress = goal > a.progress ? Math.min(goal, a.progress + step) : Math.max(goal, a.progress - step);
        }
        a.lastNanos = now;
        return a.progress;
    }

    @Override
    public DynamicRenderType<IWorkableMultiController, FisherKelpRenderer> getType() {
        return TYPE;
    }

    @Override
    public int getViewDistance() {
        return 128;
    }

    @Override
    public boolean shouldRenderOffScreen(IWorkableMultiController machine) {
        return true;
    }

    @Override
    public void render(IWorkableMultiController machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        MultiblockControllerMachine m = machine.self();
        var level = m.getLevel();
        if (level == null) return;

        boolean target = machine.isFormed() && (!onlyWhenActive || machine.isActive());
        float p = updateProgress(m, target);
        if (p <= 0f) return;
        float eased = p * p * (3 - 2 * p);

    }

    @Override
    public AABB getRenderBoundingBox(IWorkableMultiController machine) {
        MultiblockControllerMachine m = machine.self();
        BlockPos p = m.getPos();
        AABB box = new AABB(p);
        for (Entry e : entries) {
            Region r = e.region();
            BlockPos a = p.offset(toWorldOffset(m, r.minFront(), r.minLeft(), r.minUp()));
            BlockPos b = p.offset(toWorldOffset(m, r.maxFront(), r.maxLeft(), r.maxUp()));
            box = box.minmax(new AABB(a, b).inflate(1));
        }
        return box;
    }

    public record Region(
            int minFront,
            int maxFront,
            int minLeft,
            int maxLeft,
            int minUp,
            int maxUp
    ) {

        public static final Codec<Region> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.INT.fieldOf("min_front").forGetter(Region::minFront),
                Codec.INT.fieldOf("max_front").forGetter(Region::maxFront),
                Codec.INT.fieldOf("min_left").forGetter(Region::minLeft),
                Codec.INT.fieldOf("max_left").forGetter(Region::maxLeft),
                Codec.INT.fieldOf("min_up").forGetter(Region::minUp),
                Codec.INT.fieldOf("max_up").forGetter(Region::maxUp)).apply(i, Region::new));
    }

    public record Entry(Region region, BlockState body, BlockState top, float scale) {

        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(i -> i.group(
                        Region.CODEC.fieldOf("region").forGetter(Entry::region),
                        BlockState.CODEC.fieldOf("body").forGetter(Entry::body),
                        BlockState.CODEC.optionalFieldOf("top").forGetter(e -> Optional.ofNullable(e.top)),
                        Codec.FLOAT.optionalFieldOf("scale", 1.0f).forGetter(Entry::scale))
                .apply(i, (region, body, top, scale) -> new Entry(region, body, top.orElse(null), scale)));

        public Entry(Region region, BlockState body) {
            this(region, body, null, 1.0f);
        }

        public Entry(Region region, BlockState body, BlockState top) {
            this(region, body, top, 1.0f);
        }
    }

    private static final class Anim {

        boolean initialized;
        float progress;
        long lastNanos;
    }
}