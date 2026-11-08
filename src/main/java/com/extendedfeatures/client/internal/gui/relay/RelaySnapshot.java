package com.extendedfeatures.client.internal.gui.relay;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public record RelaySnapshot(BlockPos pos, Status status, boolean workingEnabled,
                            int maintenanceProblems, long consumingEUt, int coolantPerSecond,
                            int activeDataHatches, int transmissorTier, int linkedDataHatches,
                            int maxConnections, List<BlockPos> receptors, boolean showRange,
                            boolean showLinks, boolean hasScannedOnce, long cooldownEndGameTime) {

    public enum Status {
        UNFORMED,
        DISABLED,
        NO_PCB_COOLANT,
        NO_POWER,
        ACTIVE,
        IDLE
    }

    public boolean hasTransmissor() {
        return transmissorTier >= 0;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeEnum(status);
        buf.writeBoolean(workingEnabled);
        buf.writeVarInt(maintenanceProblems);
        buf.writeVarLong(consumingEUt);
        buf.writeVarInt(coolantPerSecond);
        buf.writeVarInt(activeDataHatches);
        buf.writeVarInt(transmissorTier + 1);
        buf.writeVarInt(linkedDataHatches);
        buf.writeVarInt(maxConnections);
        buf.writeVarInt(receptors.size());

        for (BlockPos receptor : receptors)
            buf.writeBlockPos(receptor);

        buf.writeBoolean(showRange);
        buf.writeBoolean(showLinks);
        buf.writeBoolean(hasScannedOnce);
        buf.writeVarLong(cooldownEndGameTime);
    }

    public static RelaySnapshot decode(FriendlyByteBuf buf) {

        BlockPos pos = buf.readBlockPos();
        Status status = buf.readEnum(Status.class);

        boolean workingEnabled = buf.readBoolean();
        int maintenanceProblems = buf.readVarInt();
        long consuming = buf.readVarLong();
        int coolant = buf.readVarInt();
        int activeHatches = buf.readVarInt();
        int tier = buf.readVarInt() - 1;
        int linkedHatches = buf.readVarInt();
        int maxConnections = buf.readVarInt();
        int count = buf.readVarInt();

        List<BlockPos> receptors = new ArrayList<>(count);

        for (int i = 0; i < count; i++)
            receptors.add(buf.readBlockPos());

        boolean showRange = buf.readBoolean();
        boolean showLinks = buf.readBoolean();
        boolean scanned = buf.readBoolean();
        long cooldownEnd = buf.readVarLong();

        return new RelaySnapshot(
                pos, status, workingEnabled,
                maintenanceProblems, consuming, coolant,
                activeHatches, tier, linkedHatches,
                maxConnections, receptors, showRange,
                showLinks, scanned, cooldownEnd
        );
    }
}
