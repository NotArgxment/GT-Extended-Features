package com.extendedfeatures.client.internal.visual;

import com.extendedfeatures.ExtendedFeaturesCore;

import com.extendedfeatures.client.internal.visual.network.RelayActionPacket;
import com.extendedfeatures.client.internal.visual.network.RelayRequestPacket;
import com.extendedfeatures.client.internal.visual.network.RelayStatePacket;
import com.extendedfeatures.client.internal.visual.range.RangePackets;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@SuppressWarnings("all")
public class PacketManager {

    private static final String PROTOCOL_VERSION = "2";

    public static final SimpleChannel CHANNEL = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(ExtendedFeaturesCore.MOD_ID, "main"),
                    () -> PROTOCOL_VERSION, // Network
                    PROTOCOL_VERSION::equals, // Client
                    PROTOCOL_VERSION::equals); // Server

    private static int nextId = 0;

    public static void register() {

        CHANNEL.registerMessage(nextId++,
                RangePackets.class, RangePackets::encode, RangePackets::decode, RangePackets::handle);

        CHANNEL.registerMessage(nextId++,
                RelayRequestPacket.class, RelayRequestPacket::encode,
                RelayRequestPacket::decode, RelayRequestPacket::handle);

        CHANNEL.registerMessage(nextId++,
                RelayActionPacket.class, RelayActionPacket::encode,
                RelayActionPacket::decode, RelayActionPacket::handle);

        CHANNEL.registerMessage(nextId++,
                RelayStatePacket.class, RelayStatePacket::encode,
                RelayStatePacket::decode, RelayStatePacket::handle);
    }
}
