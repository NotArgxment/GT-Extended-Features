package com.extendedfeatures;

import com.extendedfeatures.client.internal.logic.multiblock.fisher.FisherTankRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

public class ClientRenders {

    public static void init() {
        DynamicRenderManager.register(ExtendedFeaturesCore.id("region_block"), FisherTankRender.TYPE);
    }

}