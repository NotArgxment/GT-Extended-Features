package com.extendedfeatures.client.integration;

import com.extendedfeatures.gtef.misc.ExtendedAbilities;
import com.gtceuterminal.api.TerminalAPI;
import com.gtceuterminal.common.config.ComponentEntry;
import com.gtceuterminal.common.multiblock.ComponentGroup;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class GTCEuTerminalsPlugin {

    private static final String TX_GROUP = "extendedfeatures_wireless_optical_transmissor";
    private static final String RX_GROUP = "extendedfeatures_wireless_optical_receptor";

    private static final String TX_CATEGORY = "ef_wireless_optical_transmissors";
    private static final String RX_CATEGORY = "ef_wireless_optical_receptors";

    private static final String MOD_ID = "extendedfeatures";

    private GTCEuTerminalsPlugin() {}

    public static void init() {
        TerminalAPI.registerGroup(
                ComponentGroup.builder(TX_GROUP, "Wireless Optical Transmissor")
                        .color(0x00CED1)
                        .dataHandler()
                        .registryCategory(TX_CATEGORY)
                        .detector(ExtendedAbilities.WIRELESS_OPTICAL_TRANSMISSOR::isApplicable)
                        .build(),
                ExtendedAbilities.WIRELESS_OPTICAL_TRANSMISSOR.getName());

        TerminalAPI.registerGroup(
                ComponentGroup.builder(RX_GROUP, "Wireless Optical Receptor")
                        .color(0x00CED1)
                        .dataHandler()
                        .registryCategory(RX_CATEGORY)
                        .detector(ExtendedAbilities.WIRELESS_OPTICAL_RECEPTOR::isApplicable)
                        .build(),
                ExtendedAbilities.WIRELESS_OPTICAL_RECEPTOR.getName());

        int[] tiers = {
                LuV, ZPM, UV
        };
        for (int tier : tiers) {
            String vn = VN[tier];
            String id = vn.toLowerCase();

            TerminalAPI.registerComponent(TX_CATEGORY,
                    ComponentEntry.builder(MOD_ID + ":" + id + "_wireless_data_transmissor",
                            VNF[tier] + " Wireless Optical Transmissor", vn, tier).build());

            TerminalAPI.registerComponent(RX_CATEGORY,
                    ComponentEntry.builder(MOD_ID + ":" + id + "_wireless_data_receptor",
                            VNF[tier] + " Wireless Optical Receptor", vn, tier).build());
        }
    }
}