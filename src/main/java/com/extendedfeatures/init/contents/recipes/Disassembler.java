package com.extendedfeatures.init.contents.recipes;

import com.extendedfeatures.init.contents.misc.UniversalCircuits;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.DISASSEMBLER_COMPONENTS;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class Disassembler {

    public static void init(Consumer<FinishedRecipe> provider) {

        // MK1 Casing
        DISASSEMBLER_COMPONENTS.recipeBuilder("mk1_casing")
                .inputItems(GTBlocks.FUSION_CASING, 2)
                .outputItems(GTBlocks.MACHINE_CASING_LuV)
                .outputItems(GTBlocks.SUPERCONDUCTING_COIL)
                .outputItems(GTItems.NEUTRON_REFLECTOR)
                .outputItems(GTItems.ELECTRIC_PUMP_LuV)
                .outputItems(TagPrefix.plate, GTMaterials.TungstenSteel, 6)
                .EUt(VA[LuV])
                .duration(200)
                .save(provider);

       // MK2 Casing
        DISASSEMBLER_COMPONENTS.recipeBuilder("mk2_casing")
                .inputItems(GTBlocks.FUSION_CASING_MK2, 2)
                .outputItems(GTBlocks.MACHINE_CASING_ZPM)
                .outputItems(GTBlocks.FUSION_COIL, 2)
                .outputItems(GTItems.VOLTAGE_COIL_ZPM)
                .outputItems(GTItems.FIELD_GENERATOR_LuV)
                .outputItems(TagPrefix.plate, GTMaterials.Europium, 6)
                .EUt(VA[ZPM])
                .duration(200)
                .save(provider);

        // MK3 Casing
        DISASSEMBLER_COMPONENTS.recipeBuilder("mk3_casing")
                .inputItems(GTBlocks.FUSION_CASING_MK3, 2)
                .outputItems(GTBlocks.MACHINE_CASING_UV)
                .outputItems(GTBlocks.FUSION_COIL, 2)
                .outputItems(GTItems.VOLTAGE_COIL_UV)
                .outputItems(GTItems.FIELD_GENERATOR_ZPM)
                .outputItems(TagPrefix.plate, GTMaterials.Americium, 6)
                .EUt(VA[UV])
                .duration(200)
                .save(provider);

        // Fusion Coil
        DISASSEMBLER_COMPONENTS.recipeBuilder("fusion_coil")
                .inputItems(GTBlocks.FUSION_COIL)
                .outputItems(GTBlocks.SUPERCONDUCTING_COIL.asStack())
                .outputItems(GTItems.FIELD_GENERATOR_IV.asStack(2))
                .outputItems(GTItems.ELECTRIC_PUMP_IV)
                .outputItems(GTItems.NEUTRON_REFLECTOR.asStack(2))
                .outputItems(UniversalCircuits.UNIVERSAL_CIRCUITS[LuV], 4)
                .outputItems(TagPrefix.pipeSmallFluid, GTMaterials.Naquadah, 4)
                .outputItems(TagPrefix.plate, GTMaterials.Europium, 4)
                .EUt(VA[UV])
                .duration(200)
                .save(provider);

    }

}
