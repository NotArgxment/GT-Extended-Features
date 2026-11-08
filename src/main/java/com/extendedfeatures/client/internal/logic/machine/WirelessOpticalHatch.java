package com.extendedfeatures.client.internal.logic.machine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IDataAccessHatch;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DataAccessHatchMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.OpticalDataHatchMachine;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import lombok.Getter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("unused")
public class WirelessOpticalHatch extends OpticalDataHatchMachine implements IMachineLife {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            WirelessOpticalHatch.class, MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    public enum WirelessTier {
        LuV(GTValues.LuV, 16, 4),
        ZPM(GTValues.ZPM, 32, 8),
        UV(GTValues.UV, 64, 16);

        public final int gtTier;
        public final int range;
        public final int maxConnections;

        WirelessTier(int gtTier, int range, int maxConnections) {
            this.gtTier = gtTier;
            this.range = range;
            this.maxConnections = maxConnections;
        }

        public static WirelessTier byGTTier(int gtTier) {
            for (WirelessTier value : values()) {
                if (value.gtTier == gtTier) return value;
            }
            return null;
        }
    }

    @Getter
    private final WirelessTier wirelessTier;

    // Receptor side: the transmissor this receptor is linked to
    @Persisted
    private BlockPos linkedTransmissorPos;

    // Transmissor side: linked wireless receptors
    @Persisted
    private final List<BlockPos> linkedReceptorPositions = new ArrayList<>();

    // Transmissor side: linked physical Data Access Hatches
    @Persisted
    private final List<BlockPos> linkedDataHatchPositions = new ArrayList<>();

    public WirelessOpticalHatch(IMachineBlockEntity holder, boolean isTransmissor, int gtTier) {
        super(holder, isTransmissor);
        this.wirelessTier = WirelessTier.byGTTier(gtTier);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    // Link data (the controller drives these)
    public List<BlockPos> getLinkedReceptorPositions() {
        return List.copyOf(linkedReceptorPositions);
    }

    public List<BlockPos> getLinkedDataHatchPositions() {
        return List.copyOf(linkedDataHatchPositions);
    }

    public BlockPos getLinkedTransmissorPos() {
        return linkedTransmissorPos;
    }

    public void setLinkedTransmissorPos(BlockPos pos) {
        this.linkedTransmissorPos = pos;
    }

    public boolean addLinkedReceptor(BlockPos pos) {
        return !linkedReceptorPositions.contains(pos) && linkedReceptorPositions.add(pos);
    }

    public boolean addLinkedDataHatch(BlockPos pos) {
        return !linkedDataHatchPositions.contains(pos) && linkedDataHatchPositions.add(pos);
    }

    public boolean isLinked() {
        return isTransmitter()
                ? !linkedReceptorPositions.isEmpty() || !linkedDataHatchPositions.isEmpty()
                : linkedTransmissorPos != null;
    }

    public String getWirelessTierName() {
        return wirelessTier.name().toLowerCase(Locale.ROOT);
    }

    // Lifecycle
    @Override
    public void onMachineRemoved() {

        // Clean up bidirectional bookkeeping so stale links don't linger
        Level level = getLevel();
        if (level == null) return;

        if (isTransmitter()) {
            for (BlockPos receptorPos : linkedReceptorPositions) {
                if (MetaMachine.getMachine(level, receptorPos) instanceof WirelessOpticalHatch receptor &&
                        getPos().equals(receptor.linkedTransmissorPos)) {
                    receptor.linkedTransmissorPos = null;
                }
            }
        } else if (linkedTransmissorPos != null &&
                MetaMachine.getMachine(level, linkedTransmissorPos) instanceof WirelessOpticalHatch transmissor) {
                    transmissor.linkedReceptorPositions.remove(getPos());
                }
    }

    // Recipe logic
    @Override
    public boolean isRecipeAvailable(GTRecipe recipe, Collection<IDataAccessHatch> seen) {
        seen.add(this);

        if (!isFormed()) return false;

        Level level = getLevel();
        if (level == null) return false;

        if (isTransmitter()) {
            for (BlockPos dataHatchPos : linkedDataHatchPositions) {
                if (!level.isLoaded(dataHatchPos)) continue;
                if (!(MetaMachine.getMachine(level, dataHatchPos) instanceof DataAccessHatchMachine dataHatch))
                    continue;
                if (seen.contains(dataHatch)) continue;

                if (dataHatch.isRecipeAvailable(recipe, seen)) return true;
            }
            return false;
        }

        if (linkedTransmissorPos == null || !level.isLoaded(linkedTransmissorPos)) return false;

        if (!(MetaMachine.getMachine(level, linkedTransmissorPos) instanceof WirelessOpticalHatch partner) ||
                !partner.isTransmitter() || seen.contains(partner)) {
            return false;
        }
        return partner.isRecipeAvailable(recipe, seen);
    }
}
