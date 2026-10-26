package com.extendedfeatures.init.recipes;

import com.extendedfeatures.client.core.EFConfig;
import com.extendedfeatures.init.misc.MultiversalCircuits;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.MULTIVERSAL_PRESS;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class Multiversal {

    public static void init(Consumer<FinishedRecipe> provider) {

        if (!(EFConfig.INSTANCE.MultiversalFeatures || GTCEu.isDataGen())) return;

        circuitBuilder(provider, "lv_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[LV].asStack(),
                GTValues.VA[GTValues.LV],
                GTMaterials.SolderingAlloy.getFluid(L * 2), 1,
                new ItemStack(GTItems.NAND_CHIP_ULV, 1),
                new ItemStack(GTItems.MICROPROCESSOR_LV, 1)
        );

        circuitBuilder(provider, "mv_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[MV].asStack(),
                GTValues.VA[GTValues.MV],
                GTMaterials.SolderingAlloy.getFluid(L * 3), 2,
                new ItemStack(MultiversalCircuits.MULTIVERSAL_CIRCUITS[LV], 1),
                new ItemStack(GTItems.PROCESSOR_MV, 1)
        );

        circuitBuilder(provider, "hv_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[HV].asStack(),
                GTValues.VA[GTValues.HV],
                GTMaterials.SolderingAlloy.getFluid(L * 4), 3,
                new ItemStack(MultiversalCircuits.MULTIVERSAL_CIRCUITS[MV], 1),
                new ItemStack(GTItems.NANO_PROCESSOR_HV, 1)
        );

        circuitBuilder(provider, "ev_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[EV].asStack(),
                GTValues.VA[GTValues.EV],
                GTMaterials.SolderingAlloy.getFluid(L * 5), 4,
                new ItemStack(MultiversalCircuits.MULTIVERSAL_CIRCUITS[HV], 1),
                new ItemStack(GTItems.QUANTUM_PROCESSOR_EV, 1)
        );

        circuitBuilder(provider, "iv_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[IV].asStack(),
                GTValues.VA[GTValues.IV],
                GTMaterials.SolderingAlloy.getFluid(L * 6), 5,
                new ItemStack(MultiversalCircuits.MULTIVERSAL_CIRCUITS[EV], 1),
                new ItemStack(GTItems.CRYSTAL_PROCESSOR_IV, 1)
        );

        circuitBuilder(provider, "luv_multiversal_circuit",
                MultiversalCircuits.MULTIVERSAL_CIRCUITS[LuV].asStack(),
                GTValues.VA[GTValues.LuV],
                GTMaterials.SolderingAlloy.getFluid(L * 7), 6,
                new ItemStack(MultiversalCircuits.MULTIVERSAL_CIRCUITS[IV], 1),
                new ItemStack(GTItems.WETWARE_PROCESSOR_LuV, 1)
        );

        // Past this point, recipes should be handled from kubejs
        /*
        circuitBuilder(provider, "zpm_multiversal_circuit",
                MultiversalCircuits.ZPM_MULTICIRCUIT.asStack(),
                GTValues.VA[GTValues.ZPM],
                GTMaterials.SolderingAlloy.getFluid(L * 8), 7,
                new ItemStack(MultiversalCircuits.LuV_MULTICIRCUIT, 1),
                new ItemStack(YourClass.BEST_ZPM_CIRCUIT, 1)
        );

        circuitBuilder(provider, "uv_multiversal_circuit",
                MultiversalCircuits.UV_MULTICIRCUIT.asStack(),
                GTValues.VA[GTValues.UV],
                GTMaterials.SolderingAlloy.getFluid(L * 9), 8,
                new ItemStack(MultiversalCircuits.ZPM_MULTICIRCUIT, 1),
                new ItemStack(YourClass.BEST_UV_CIRCUIT, 1)
        );
         */
    }

    private static void circuitBuilder(Consumer<FinishedRecipe> provider,
                                       String recipeID,
                                       ItemStack circuitOutput,
                                       int minTier,
                                       FluidStack fluidInput,
                                       int circuitSetting,
                                       ItemStack... circuitInput) {
        var builder = MULTIVERSAL_PRESS.recipeBuilder(recipeID)
                .inputItems(circuitInput)
                .outputItems(circuitOutput)
                .inputFluids(fluidInput)
                .duration(100)
                .EUt(minTier)
                .circuitMeta(circuitSetting)
                .circuitMeta(10);
        builder.save(provider);
    }

}
