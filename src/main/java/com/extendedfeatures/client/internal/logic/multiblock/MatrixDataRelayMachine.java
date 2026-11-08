package com.extendedfeatures.client.internal.logic.multiblock;

import com.extendedfeatures.client.internal.gui.relay.RelayScreenHandler;
import com.extendedfeatures.client.internal.gui.relay.RelaySnapshot;
import com.extendedfeatures.client.internal.logic.machine.ExpandedDataAccessHatch;
import com.extendedfeatures.client.internal.logic.machine.WirelessOpticalHatch;
import com.extendedfeatures.client.internal.visual.PacketManager;
import com.extendedfeatures.client.internal.visual.linking.ParticleAnimator;
import com.extendedfeatures.client.internal.visual.linking.ParticleRenderer;
import com.extendedfeatures.client.internal.visual.network.RelayStatePacket;
import com.extendedfeatures.client.internal.visual.range.RangePackets;
import com.extendedfeatures.gtef.misc.ConfigClass;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IInteractedMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMaintenanceMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.transfer.fluid.FluidHandlerList;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.research.DataBankMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DataAccessHatchMachine;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;

import lombok.Getter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTMaterials.PCBCoolant;
import static com.gregtechceu.gtceu.utils.GTTransferUtils.drainFluidAccountNotifiableList;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.*;

public class MatrixDataRelayMachine extends DataBankMachine implements IInteractedMachine {

    public static final int hatchAmpUsage = 1;
    public static final int expandedDataHatchUsage = 8192;
    public static final int coolantAmount = 144;
    public static final int consuptionInterval = 20;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            MatrixDataRelayMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    private static final int durationTicks = 200;
    private static final int intervalTicks = 4;
    private static final int scanCooldown = 100;
    private static final int resyncInterval = 100;

    private final List<ParticleAnimator> particleAnimators = new ArrayList<>();

    private IFluidHandler coolantHandler = new FluidHandlerList(new ArrayList<>());

    private int coolantTickCounter = 0;

    @Getter private boolean coolantStarved = false;
    @Persisted private long lastScanGameTime = Long.MIN_VALUE / 2;

    private boolean showRangeEnabled = false;
    private boolean showLinksEnabled = false;

    private ParticleAnimator linksLoopAnimator;
    private TickableSubscription rangeResyncSubscription;
    private int rangeResyncCounter = 0;

    private BlockPos rangeShownAt;

    public MatrixDataRelayMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (!isFormed())
            return;

        List<IFluidHandler> coolantContainers = new ArrayList<>();

        Long2ObjectMap<IO> ioMap = getMultiblockState().getMatchContext().getOrCreate("ioMap", Long2ObjectMaps::emptyMap);

        for (IMultiPart part : getParts()) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE || io == IO.OUT) continue;
            for (var handlerList : part.getRecipeHandlers()) {
                if (!handlerList.isValid(io)) continue;
                handlerList.getCapability(FluidRecipeCapability.CAP).stream()
                        .filter(IFluidHandler.class::isInstance)
                        .map(IFluidHandler.class::cast)
                        .forEach(coolantContainers::add);
            }
        }
        this.coolantHandler = new FluidHandlerList(coolantContainers);
        this.coolantTickCounter = 0;
        this.coolantStarved = false;
    }

    @Override
    public void onStructureInvalid() {
        stopVisuals();
        super.onStructureInvalid();
        forceIdle();
        this.coolantHandler = new FluidHandlerList(new ArrayList<>());
        this.coolantTickCounter = 0;
        this.coolantStarved = false;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        showRangeEnabled = false;
        showLinksEnabled = false;
    }

    @Override
    public void onUnload() {
        super.onUnload();
        stopVisuals();
    }

    @Override
    public void tick() {

        if (!isFormed()) {
            super.tick();
            if (!isRemote() && getRecipeLogic().getStatus() != RecipeLogic.Status.IDLE) {
                forceIdle();
            }
            return;
        }

        if (!isWorkingEnabled()) {
            if (!getRecipeLogic().isSuspend()) getRecipeLogic().setStatus(RecipeLogic.Status.SUSPEND);
            coolantTickCounter = 0;
            return;
        }

        super.tick();

        if (++coolantTickCounter >= consuptionInterval) {
            coolantTickCounter = 0;
            coolantStarved = !consumeCoolant();
        }

        if (coolantStarved) {
            getRecipeLogic().setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_in")
                    .append(": ").append(FluidRecipeCapability.CAP.getName()));
        }
    }

    private void forceIdle() {
        RecipeLogic logic = getRecipeLogic();
        logic.resetRecipeLogic();
        logic.setStatus(RecipeLogic.Status.IDLE);
        if (!isRemote()) scheduleRenderUpdate();
    }

    private boolean consumeCoolant() {
        FluidStack required = PCBCoolant.getFluid(coolantAmount);
        FluidStack simulated = drainFluidAccountNotifiableList(coolantHandler, required, SIMULATE);
        if (simulated.getAmount() < coolantAmount)
            return false;
        drainFluidAccountNotifiableList(coolantHandler, required, EXECUTE);
        return true;
    }

    private long hatchAmpConsuption() {
        long fullConsuption = 0;
        for (IMultiPart part : getParts()) {
            if (!(part instanceof WirelessOpticalHatch hatch)) continue;
            int energyTier = hatch.getWirelessTier().gtTier + 1;
            if (energyTier >= GTValues.V.length) continue;
            fullConsuption += (long) hatchAmpUsage * GTValues.V[energyTier];
        }
        return fullConsuption;
    }

    private long expandedDataHatchConsuption() {
        long fullConsuption = 0;
        for (IMultiPart part : getParts()) {
            if (!(part instanceof ExpandedDataAccessHatch)) continue;
            fullConsuption += expandedDataHatchUsage;
        }
        return fullConsuption;
    }

    @Override
    protected int calculateEnergyUsage() {
        int baseUsage = super.calculateEnergyUsage();
        long surcharge = hatchAmpConsuption() + expandedDataHatchConsuption();
        if (surcharge == 0) return baseUsage;
        long total = baseUsage + surcharge;
        return total > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) total;
    }

    private WirelessOpticalHatch findTransmissor() {
        for (IMultiPart part : getParts()) {
            if (part instanceof WirelessOpticalHatch hatch && hatch.isTransmitter()) return hatch;
        }
        return null;
    }

    private int maintenanceProblems() {
        for (IMultiPart part : getParts()) {
            if (part instanceof IMaintenanceMachine maintenance) return maintenance.getNumMaintenanceProblems();
        }
        return 0;
    }

    private int activeDataHatches() {
        int count = 0;
        for (IMultiPart part : getParts()) {
            if (part instanceof DataAccessHatchMachine) count++;
        }
        return count;
    }

    private RelaySnapshot.Status currentStatus() {
        if (!isFormed()) return RelaySnapshot.Status.UNFORMED;
        if (!isWorkingEnabled()) return RelaySnapshot.Status.DISABLED;
        if (coolantStarved) return RelaySnapshot.Status.NO_PCB_COOLANT;
        if (getRecipeLogic().isWaiting()) return RelaySnapshot.Status.NO_POWER;
        return isActive() ? RelaySnapshot.Status.ACTIVE : RelaySnapshot.Status.IDLE;
    }

    private long currentConsumption() {
        RelaySnapshot.Status status = currentStatus();
        if (status != RelaySnapshot.Status.ACTIVE && status != RelaySnapshot.Status.IDLE) return 0;

        long usage = getEnergyUsage();
        return usage + maintenanceProblems() * usage / 10;
    }

    public RelaySnapshot createSnapshot() {
        WirelessOpticalHatch transmissor = findTransmissor();
        List<BlockPos> receptors = transmissor == null ? List.of() : transmissor.getLinkedReceptorPositions();

        return new RelaySnapshot(
                getPos(), currentStatus(), isWorkingEnabled(), maintenanceProblems(),
                currentConsumption(), coolantAmount * 20 / consuptionInterval, activeDataHatches(),
                transmissor == null ? -1 : transmissor.getWirelessTier().gtTier,
                transmissor == null ? 0 : transmissor.getLinkedDataHatchPositions().size(),
                transmissor == null ? 0 : transmissor.getWirelessTier().maxConnections,
                receptors, showRangeEnabled, showLinksEnabled, hasScannedOnce(), lastScanGameTime + scanCooldown);
    }

    public void sendStateSyncTo(ServerPlayer player) {
        PacketManager.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new RelayStatePacket(createSnapshot()));
    }

    @Override
    public InteractionResult onUse(BlockState state, Level world,
                                   BlockPos pos, Player player,
                                   InteractionHand hand, BlockHitResult hit) {
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }
        if (player.isShiftKeyDown()) {
            InteractionResult result = super.onUse(state, world, pos, player, hand, hit);
            return result == InteractionResult.PASS ? InteractionResult.CONSUME : result;
        }
        if (world.isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> RelayScreenHandler.open(this));
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    public void togglePower() {
        setWorkingEnabled(!isWorkingEnabled());
    }

    public void toggleShowRange(ServerPlayer player) {
        WirelessOpticalHatch transmissor = requireTransmissor(player);
        if (transmissor == null) return;

        showRangeEnabled = !showRangeEnabled;
        updateRangeLoop();
    }

    public void toggleShowLinks(ServerPlayer player) {
        if (requireTransmissor(player) == null) return;
        if (!showLinksEnabled && !hasScannedOnce()) {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.scan_required"));
            return;
        }
        showLinksEnabled = !showLinksEnabled;
        updateLinksLoop();
    }

    public void handleScanRequest(ServerPlayer player) {
        WirelessOpticalHatch transmissor = requireTransmissor(player);
        if (transmissor == null) return;
        if (!(getLevel() instanceof ServerLevel serverLevel)) return;
        long now = serverLevel.getGameTime();
        if (now - lastScanGameTime < scanCooldown) {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.scan_cooldown"));
            return;
        }

        lastScanGameTime = now;
        scanAndLink(player, serverLevel, transmissor);
    }

    public boolean hasScannedOnce() {
        return lastScanGameTime != Long.MIN_VALUE / 2;
    }

    private WirelessOpticalHatch requireTransmissor(ServerPlayer player) {
        if (!isFormed()) {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.not_formed"));
            return null;
        }
        WirelessOpticalHatch transmissor = findTransmissor();
        if (transmissor == null) {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.matrix_data_relay.no_transmissor"));
        }
        return transmissor;
    }

    private void stopVisuals() {
        for (ParticleAnimator animator : List.copyOf(particleAnimators)) {
            animator.stop();
        }
        particleAnimators.clear();

        if (linksLoopAnimator != null) {
            linksLoopAnimator.stop();
            linksLoopAnimator = null;
        }

        if (rangeResyncSubscription != null) {
            rangeResyncSubscription.unsubscribe();
            rangeResyncSubscription = null;
        }

        broadcastRangeState(false);
        showRangeEnabled = false;
        showLinksEnabled = false;
    }

    private void updateRangeLoop() {
        boolean shouldRun = showRangeEnabled && isFormed() && findTransmissor() != null;
        if (shouldRun && rangeResyncSubscription == null) {
            rangeResyncCounter = 0;
            broadcastRangeState(true);
            rangeResyncSubscription = subscribeServerTick(this::rangeResyncTick);
        } else if (!shouldRun && rangeResyncSubscription != null) {
            rangeResyncSubscription.unsubscribe();
            rangeResyncSubscription = null;
            broadcastRangeState(false);
        }
    }

    private void rangeResyncTick() {
        if (!isFormed()) return;
        if (++rangeResyncCounter >= resyncInterval) {
            rangeResyncCounter = 0;
            broadcastRangeState(true);
        }
    }

    private void broadcastRangeState(boolean show) {
        if (!(getLevel() instanceof ServerLevel serverLevel)) return;

        if (show) {
            WirelessOpticalHatch transmissor = findTransmissor();
            if (transmissor == null) return;
            rangeShownAt = transmissor.getPos();
            PacketManager.CHANNEL.send(
                    PacketDistributor.TRACKING_CHUNK.with(() -> serverLevel.getChunkAt(transmissor.getPos())),
                    new RangePackets(transmissor.getPos(), transmissor.getWirelessTier().range, true));
        } else if (rangeShownAt != null) {
            BlockPos shown = rangeShownAt;
            rangeShownAt = null;
            PacketManager.CHANNEL.send(
                    PacketDistributor.TRACKING_CHUNK.with(() -> serverLevel.getChunkAt(shown)),
                    new RangePackets(shown, 0, false));
        }
    }

    private void updateLinksLoop() {
        boolean shouldRun = showLinksEnabled && isFormed();
        if (shouldRun && linksLoopAnimator == null) {
            linksLoopAnimator = new ParticleAnimator(-1, intervalTicks, this::drawAllLinkBeams);
            linksLoopAnimator.start(this::subscribeServerTick, () -> linksLoopAnimator = null);
        } else if (!shouldRun && linksLoopAnimator != null) {
            linksLoopAnimator.stop();
        }
    }

    private void drawAllLinkBeams() {
        WirelessOpticalHatch transmissor = findTransmissor();
        if (!isFormed() || transmissor == null) return;
        if (!(getLevel() instanceof ServerLevel serverLevel)) return;

        BlockPos center = transmissor.getPos();

        for (BlockPos receptorPos : transmissor.getLinkedReceptorPositions()) {
            if (!serverLevel.isLoaded(receptorPos)) continue;
            emitBeam(serverLevel, center, receptorPos, ParticleTypes.END_ROD);
        }
        for (BlockPos dataHatchPos : transmissor.getLinkedDataHatchPositions()) {
            if (!serverLevel.isLoaded(dataHatchPos)) continue;
            emitBeam(serverLevel, center, dataHatchPos, ParticleTypes.FLAME);
        }
    }

    private void emitBeam(ServerLevel level, BlockPos from, BlockPos to, ParticleOptions particle) {
        Vec3 start = ParticleRenderer.faceCenterTowards(from, to);
        Vec3 end = ParticleRenderer.faceCenterTowards(to, from);
        ParticleRenderer.emitLine(level, start, end, particle);
    }

    private void spawnLinkBeam(ServerLevel level, BlockPos from, BlockPos to, ParticleOptions particle) {
        Vec3 start = ParticleRenderer.faceCenterTowards(from, to);
        Vec3 end = ParticleRenderer.faceCenterTowards(to, from);

        ParticleAnimator animator = new ParticleAnimator(durationTicks, intervalTicks,
                () -> ParticleRenderer.emitLine(level, start, end, particle));
        particleAnimators.add(animator);
        animator.start(this::subscribeServerTick, () -> particleAnimators.remove(animator));
    }

    private void scanAndLink(ServerPlayer player, ServerLevel level, WirelessOpticalHatch transmissor) {
        BlockPos center = transmissor.getPos();
        int range = transmissor.getWirelessTier().range;

        int newReceiverLinks = linkReceivers(level, transmissor, center, range);
        int newDataHatchLinks = linkDataHatches(level, transmissor, center, range);

        if (newReceiverLinks > 0 || newDataHatchLinks > 0) {
            player.sendSystemMessage(
                    Component.translatable(
                            "extendedfeatures.machine.wireless_optical_hatch.linked_summary",
                            newReceiverLinks, newDataHatchLinks));
        } else {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.no_receptors_found"));
        }
    }

    private int linkReceivers(ServerLevel level, WirelessOpticalHatch transmissor, BlockPos center, int range) {
        int remainingSlots = transmissor.getWirelessTier().maxConnections -
                transmissor.getLinkedReceptorPositions().size();
        if (remainingSlots <= 0) return 0;

        List<WirelessOpticalHatch> candidates = findCandidateReceptors(level, transmissor, center, range);
        candidates.sort(Comparator.comparingDouble(a -> a.getPos().distSqr(center)));

        int newLinks = 0;
        for (WirelessOpticalHatch receiver : candidates) {
            if (remainingSlots <= 0) break;

            if (!transmissor.addLinkedReceptor(receiver.getPos())) continue;

            receiver.setLinkedTransmissorPos(center);
            spawnLinkBeam(level, center, receiver.getPos(), ParticleTypes.END_ROD);
            remainingSlots--;
            newLinks++;
        }
        return newLinks;
    }

    private int linkDataHatches(ServerLevel level, WirelessOpticalHatch transmissor, BlockPos center, int range) {
        int newLinks = 0;
        for (DataAccessHatchMachine dataHatch : findCandidateDataHatches(level, center, range)) {
            BlockPos dataHatchPos = dataHatch.getPos();

            if (!transmissor.addLinkedDataHatch(dataHatchPos)) continue;

            spawnLinkBeam(level, center, dataHatchPos, ParticleTypes.FLAME);
            newLinks++;
        }
        return newLinks;
    }

    private List<WirelessOpticalHatch> findCandidateReceptors(ServerLevel level, WirelessOpticalHatch transmissor,
                                                              BlockPos center, int range) {
        List<WirelessOpticalHatch> found = new ArrayList<>();

        forEachBlockEntityInRange(level, center, range, candidatePos -> {
            if (MetaMachine.getMachine(level, candidatePos) instanceof WirelessOpticalHatch other &&
                    !other.isTransmitter() &&
                    other.getWirelessTier() == transmissor.getWirelessTier() &&
                    other.isFormed()) {
                found.add(other);
            }
        });
        return found;
    }

    // Behavior Switch
    private List<DataAccessHatchMachine> findCandidateDataHatches(ServerLevel level, BlockPos center, int range) {
        List<DataAccessHatchMachine> found = new ArrayList<>();

        forEachBlockEntityInRange(level, center, range, candidatePos -> {
            if (MetaMachine.getMachine(level, candidatePos) instanceof DataAccessHatchMachine dataHatch &&
                    dataHatch.isFormed() && validTarget(dataHatch)) {
                found.add(dataHatch);
            }
        });
        return found;
    }

    private boolean validTarget(DataAccessHatchMachine dataHatch) {
        if (!ConfigClass.INSTANCE.DataHatchLinkingBehavior) return true;
        return ((IMultiPart) dataHatch).getControllers().stream()
                .anyMatch(controller -> controller instanceof MatrixDataRelayMachine);
    }

    private boolean isWithinCubeRange(BlockPos candidate, BlockPos center, int range) {
        return Math.abs(candidate.getX() - center.getX()) <= range &&
                Math.abs(candidate.getY() - center.getY()) <= range &&
                Math.abs(candidate.getZ() - center.getZ()) <= range;
    }

    private void forEachBlockEntityInRange(ServerLevel level, BlockPos center, int range, PosVisitor visitor) {
        int chunkMinX = (center.getX() - range) >> 4;
        int chunkMaxX = (center.getX() + range) >> 4;
        int chunkMinZ = (center.getZ() - range) >> 4;
        int chunkMaxZ = (center.getZ() + range) >> 4;

        for (int cx = chunkMinX; cx <= chunkMaxX; cx++) {
            for (int cz = chunkMinZ; cz <= chunkMaxZ; cz++) {
                if (!level.hasChunk(cx, cz)) continue;

                LevelChunk chunk = level.getChunk(cx, cz);

                for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                    BlockPos candidatePos = blockEntity.getBlockPos();

                    if (!isWithinCubeRange(candidatePos, center, range)) continue;
                    if (candidatePos.equals(center)) continue;

                    visitor.visit(candidatePos);
                }
            }
        }
    }

    private interface PosVisitor {
        void visit(BlockPos pos);
    }
}