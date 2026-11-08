package com.extendedfeatures.client.internal.visual.network;

import com.extendedfeatures.client.internal.logic.multiblock.MatrixDataRelayMachine;
import com.extendedfeatures.client.internal.visual.PacketManager;

import com.gregtechceu.gtceu.api.machine.MetaMachine;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class RelayRequestPacket {

    private static final double MAX_DISTANCE_SQR = 16 * 16;

    private final BlockPos pos;

    public RelayRequestPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(RelayRequestPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
    }

    public static RelayRequestPacket decode(FriendlyByteBuf buf) {
        return new RelayRequestPacket(buf.readBlockPos());
    }

    static MatrixDataRelayMachine resolve(ServerPlayer player, BlockPos pos) {
        if (!player.level().isLoaded(pos)) return null;
        if (player.distanceToSqr(Vec3.atCenterOf(pos)) > MAX_DISTANCE_SQR) return null;
        return MetaMachine.getMachine(player.level(), pos) instanceof MatrixDataRelayMachine relay ? relay : null;
    }

    public static void handle(RelayRequestPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() != LogicalSide.SERVER) return;

            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            MatrixDataRelayMachine relay = resolve(player, msg.pos);
            if (relay != null) {
                PacketManager.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                        new RelayStatePacket(relay.createSnapshot()));
            }
        });
        ctx.setPacketHandled(true);
    }
}
