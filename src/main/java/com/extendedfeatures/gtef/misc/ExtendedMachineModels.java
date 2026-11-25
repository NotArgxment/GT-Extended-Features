package com.extendedfeatures.gtef.misc;

import com.extendedfeatures.ExtendedFeaturesCore;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import net.minecraft.resources.ResourceLocation;

import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.*;

// Credits to puff_official
public class ExtendedMachineModels {

    public static final ResourceLocation CCMHATCH_TAPED_OVERLAY = ExtendedFeaturesCore.id(
            "block/overlay/machine/configurable_cleaning_maintenance_hatch_taped"
    );

    public static MachineBuilder.ModelInitializer createTapedOverlay(ResourceLocation overlayModel) {
        return (ctx, prov, builder) -> {
            builder.forAllStatesModels(state -> {
                var baseModel = prov.models().nested()
                        .parent(prov.models().getExistingFile(overlayModel));
                GTMachineModels.tieredHullTextures(baseModel, builder.getOwner().getTier());
                if (state.getValue(IS_TAPED)) {
                    baseModel.texture("overlay_2", CCMHATCH_TAPED_OVERLAY);
                }
                return baseModel;
            });

            builder.addReplaceableTextures("bottom", "top", "side");
        };
    }
}