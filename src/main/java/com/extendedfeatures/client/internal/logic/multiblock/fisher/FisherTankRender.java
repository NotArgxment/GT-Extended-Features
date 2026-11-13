package com.extendedfeatures.client.internal.logic.multiblock.fisher;

import com.extendedfeatures.ExtendedFeaturesCore;

import com.google.common.collect.MapMaker;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.RenderUtil;

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
public class FisherTankRender extends DynamicRender<IWorkableMultiController, FisherTankRender> {

    public static final Codec<FisherTankRender> CODEC = RecordCodecBuilder.create(i -> i.group(
            Entry.CODEC.listOf().fieldOf("entries").forGetter(r -> r.entries),
            Codec.BOOL.optionalFieldOf("only_when_active", false).forGetter(r -> r.onlyWhenActive),
            Codec.FLOAT.optionalFieldOf("grow_seconds", 2.0f).forGetter(r -> r.growSeconds)
    ).apply(i, FisherTankRender::new));

    public static final DynamicRenderType<IWorkableMultiController, FisherTankRender> TYPE = new DynamicRenderType<>(CODEC);
    private static final Map<MultiblockControllerMachine, Anim> ANIMS = new MapMaker().weakKeys().makeMap();
    private static boolean warned = false;
    private final List<Entry> entries;
    private final boolean onlyWhenActive;
    private final float growSeconds;

    private static final class Anim {
        boolean initialized;
        float progress;
        long lastNanos;
    }

    public record Entry(Region region,
                        BlockState body,
                        BlockState top,
                        float scale,
                        float offsetY,
                        boolean sampleLightAbove) {

        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(i -> i.group(
                        Region.CODEC.fieldOf("region").forGetter(Entry::region),
                        BlockState.CODEC.fieldOf("body").forGetter(Entry::body),
                        BlockState.CODEC.optionalFieldOf("top").forGetter(e -> Optional.ofNullable(e.top)),
                        Codec.FLOAT.optionalFieldOf("scale", 1.0f).forGetter(Entry::scale),
                        Codec.FLOAT.optionalFieldOf("offset_y", 0.0f).forGetter(Entry::offsetY),
                        Codec.BOOL.optionalFieldOf("sample_light_above", false).forGetter(Entry::sampleLightAbove))
                .apply(i, (region, body, top, scale, offsetY, sampleLightAbove) -> new Entry(region, body, top.orElse(null), scale, offsetY, sampleLightAbove)));

        public Entry(Region region, BlockState body) {
            this(region, body, null, 1.0f, 0.0f, false);
        }

        public Entry(Region region, BlockState body, BlockState top) {
            this(region, body, top, 1.0f, 0.0f, false);
        }

        public Entry(Region region, BlockState body, BlockState top, float scale) {
            this(region, body, top, scale, 0.0f, false);
        }

        public Entry(Region region, BlockState body, BlockState top, float scale, float offsetY) {
            this(region, body, top, scale, offsetY, false);
        }
    }

    public FisherTankRender(List<Entry> entries, boolean onlyWhenActive) {
        this(entries, onlyWhenActive, 2.0f);
    }

    public FisherTankRender(List<Entry> entries, boolean onlyWhenActive, float growSeconds) {
        this.entries = List.copyOf(entries);
        this.onlyWhenActive = onlyWhenActive;
        this.growSeconds = growSeconds;
    }

    private static BlockPos toWorldOffset(MultiblockControllerMachine machine, int front, int left, int up) {
        Direction frontFace = machine.getFrontFacing();
        Direction upwardsFace = machine.getUpwardsFacing();
        boolean flipped = machine.isFlipped();
        return BlockPos.ZERO
                .relative(RelativeDirection.FRONT.getRelative(frontFace, upwardsFace, flipped), front)
                .relative(RelativeDirection.LEFT.getRelative(frontFace, upwardsFace, flipped), left)
                .relative(RelativeDirection.UP.getRelative(frontFace, upwardsFace, flipped), up);
    }

    private float updateProgress(MultiblockControllerMachine machine, boolean target) {
        Anim animate = ANIMS.computeIfAbsent(machine, k -> new Anim());
        long now = System.nanoTime();
        float goal = target ? 1f : 0f;
        if (!animate.initialized || growSeconds <= 0f) {
            animate.progress = goal;
            animate.initialized = true;
        } else {
            float deltaTime = Math.min((now - animate.lastNanos) / 1.0e9f, 0.1f);
            float step = deltaTime / growSeconds;
            animate.progress = goal > animate.progress ? Math.min(goal, animate.progress + step) : Math.max(goal, animate.progress - step);
        }
        animate.lastNanos = now;
        return animate.progress;
    }

    @Override
    public DynamicRenderType<IWorkableMultiController, FisherTankRender> getType() {
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
        MultiblockControllerMachine machineController = machine.self();
        var level = machineController.getLevel();
        if (level == null) return;

        boolean target = machine.isFormed() && (!onlyWhenActive || machine.isActive());
        float prog = updateProgress(machineController, target);
        if (prog <= 0f) return;
        float eased = prog * prog * (3 - 2 * prog);

        try {
            for (Entry ent : entries) {
                Region reg = ent.region();
                int layers = reg.maxUp() - reg.minUp() + 1;
                float visible = eased * layers;
                int headLayer = (int) Math.ceil(visible) - 1;
                for (int front = reg.minFront(); front <= reg.maxFront(); front++) {
                    for (int left = reg.minLeft(); left <= reg.maxLeft(); left++) {
                        for (int var = 0; var < layers; var++) {
                            float vis = Math.min(1f, visible - var);
                            if (vis <= 0f) break;
                            BlockState state = (ent.top() != null && var == headLayer) ? ent.top() : ent.body();
                            BlockPos off = toWorldOffset(machineController, front, left, reg.minUp() + var);
                            poseStack.pushPose();
                            poseStack.translate(off.getX(), off.getY() + ent.offsetY(), off.getZ());
                            if (vis < 1f) {
                                poseStack.scale(1f, vis, 1f);
                            }
                            if (ent.scale() != 1.0f) {
                                poseStack.translate(0.5, 0.5, 0.5);
                                poseStack.scale(ent.scale(), ent.scale(), ent.scale());
                                poseStack.translate(-0.5, -0.5, -0.5);
                            }
                            BlockPos basePos = machineController.getPos().offset(off);
                            BlockPos lightPos = ent.sampleLightAbove()
                                    ? basePos.above()
                                    : basePos;
                            RenderUtil.drawBlock(level, lightPos, state, buffer, poseStack);
                            poseStack.popPose();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            if (!warned) {
                warned = true;
                ExtendedFeaturesCore.LOGGER.error("FisherTankRender failed", ex);
            }
        }
    }

    @Override
    public AABB getRenderBoundingBox(IWorkableMultiController machine) {
        MultiblockControllerMachine m = machine.self();
        BlockPos bPos = m.getPos();
        AABB box = new AABB(bPos);
        for (Entry ent : entries) {
            Region reg = ent.region();
            BlockPos posA = bPos.offset(toWorldOffset(m, reg.minFront(), reg.minLeft(), reg.minUp()));
            BlockPos posB = bPos.offset(toWorldOffset(m, reg.maxFront(), reg.maxLeft(), reg.maxUp()));
            box = box.minmax(new AABB(posA, posB).inflate(1));
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

}