package com.extendedfeatures;

import com.extendedfeatures.client.internal.logic.multiblock.fisher.FisherKelpRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

public class ClientRenders {

    public static void init() {
        DynamicRenderManager.register(ExtendedFeaturesCore.id("region_block"), FisherKelpRenderer.TYPE);
    }

}