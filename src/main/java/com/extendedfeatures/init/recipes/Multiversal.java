package com.extendedfeatures.init.recipes;

import com.extendedfeatures.init.misc.MultiversalCircuits;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.MULTIVERSAL_PRESS_BASIC;
import static com.gregtechceu.gtceu.api.GTValues.L;

public class Multiversal {

    public static void init(Consumer<FinishedRecipe> provider) {

        assemblerCircuitBuilder(
                provider, "lv_multiversal_circuit",
                MultiversalCircuits.LV_MULTICIRCUIT.asStack(),
                GTValues.VA[GTValues.LV],
                GTMaterials.SolderingAlloy.getFluid(L * 2),
                new ItemStack(GTItems.NAND_CHIP_ULV, 1),
                new ItemStack(GTItems.MICROPROCESSOR_LV, 1)
        );

    }

    private static void assemblerCircuitBuilder(Consumer<FinishedRecipe> provider,
                                                String recipeID,
                                                ItemStack circuitOutput,
                                                int minTier,
                                                FluidStack fluidInput,
                                                ItemStack... circuitInput) {
        var builder = MULTIVERSAL_PRESS_BASIC.recipeBuilder(recipeID)
                .inputItems(circuitInput)
                .outputItems(circuitOutput)
                .inputFluids(fluidInput)
                .duration(100)
                .EUt(minTier)
                .circuitMeta(10);
        builder.save(provider);
    }

}
