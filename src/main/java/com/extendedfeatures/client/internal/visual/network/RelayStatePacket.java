package com.extendedfeatures.client.internal.visual.network;

import com.extendedfeatures.client.internal.gui.relay.RelayScreenHandler;
import com.extendedfeatures.client.internal.gui.relay.RelaySnapshot;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RelayStatePacket {

    private final RelaySnapshot snapshot;

    public RelayStatePacket(RelaySnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public static void encode(RelayStatePacket msg, FriendlyByteBuf buf) {
        msg.snapshot.encode(buf);
    }

    public static RelayStatePacket decode(FriendlyByteBuf buf) {
        return new RelayStatePacket(RelaySnapshot.decode(buf));
    }

    public static void handle(RelayStatePacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() != LogicalSide.CLIENT) return;

            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> RelayScreenHandler.handleState(msg.snapshot));
        });
        ctx.setPacketHandled(true);
    }
}
