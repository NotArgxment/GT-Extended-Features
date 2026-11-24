package com.extendedfeatures.client.internal.gui.relay;

import com.extendedfeatures.client.internal.logic.multiblock.MatrixDataRelay;

import net.minecraft.client.Minecraft;

public final class RelayScreenHandler {

    private RelayScreenHandler() {}

    public static void open(MatrixDataRelay machine) {
        Minecraft.getInstance().setScreen(new MatrixRelayScreen(machine));
    }

    public static void handleState(RelaySnapshot snapshot) {
        if (Minecraft.getInstance().screen instanceof MatrixRelayScreen screen
                && screen.getRelayPos().equals(snapshot.pos())) {
            screen.applySnapshot(snapshot);
        }
    }
}
