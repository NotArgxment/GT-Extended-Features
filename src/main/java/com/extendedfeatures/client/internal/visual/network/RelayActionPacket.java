package com.extendedfeatures.client.internal.visual.network;

import com.extendedfeatures.client.internal.logic.multiblock.MatrixDataRelayMachine;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RelayActionPacket {

    public enum Action {
        TOGGLE_RANGE,
        TOGGLE_LINKS,
        SCAN_AND_LINK,
        TOGGLE_POWER
    }

    private final BlockPos pos;
    private final Action action;

    public RelayActionPacket(BlockPos pos, Action action) {
        this.pos = pos;
        this.action = action;
    }

    public static void encode(RelayActionPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeEnum(msg.action);
    }

    public static RelayActionPacket decode(FriendlyByteBuf buf) {
        return new RelayActionPacket(buf.readBlockPos(), buf.readEnum(Action.class));
    }

    public static void handle(RelayActionPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() != LogicalSide.SERVER) return;

            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            MatrixDataRelayMachine relay = RelayRequestPacket.resolve(player, msg.pos);
            if (relay == null) return;

            switch (msg.action) {
                case TOGGLE_RANGE -> relay.toggleShowRange(player);
                case TOGGLE_LINKS -> relay.toggleShowLinks(player);
                case SCAN_AND_LINK -> relay.handleScanRequest(player);
                case TOGGLE_POWER -> relay.togglePower();
            }
            // Always answer with the new state so the screen never shows stale toggles
            relay.sendStateSyncTo(player);
        });
        ctx.setPacketHandled(true);
    }
}
