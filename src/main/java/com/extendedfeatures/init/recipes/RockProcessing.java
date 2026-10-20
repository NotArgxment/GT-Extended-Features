package com.extendedfeatures.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.ROCK_PROCESSING_RECIPES;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class RockProcessing {

    public static void init(Consumer<FinishedRecipe> provider){

        ROCK_PROCESSING_RECIPES.recipeBuilder("deepslate_processing")
                .inputItems(new ItemStack(Blocks.DEEPSLATE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Potassium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Aluminium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Fluorine.getFluid(144 * 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(144 * 4))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("andesite_processing")
                .inputItems(new ItemStack(Blocks.ANDESITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Hydrogen.getFluid(144 * 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(144 * 4))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("diorite_processing")
                .inputItems(new ItemStack(Blocks.DIORITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Sodium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Sulfur, 1)
                .outputFluids(GTMaterials.Water.getFluid(144 * 4))
                .outputFluids(GTMaterials.Oxygen.getFluid(144 * 2))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("granite_processing")
                .inputItems(new ItemStack(Blocks.GRANITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .outputItems(TagPrefix.dust, GTMaterials.SiliconDioxide, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Calcite, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Flint, 1)
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("end_stone_processing")
                .inputItems(new ItemStack(Blocks.END_STONE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .chancedOutput(new ItemStack(Blocks.SAND), 8000, 5)
                .chancedOutput(TagPrefix.dust, GTMaterials.Tungstate, 1, 5000, 5)
                .chancedOutput(TagPrefix.dust, GTMaterials.Platinum, 1, 2500, 5)
                .outputFluids(GTMaterials.Helium.getFluid(L))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("netherrack_processing")
                .inputItems(new ItemStack(Blocks.NETHERRACK), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .chancedOutput(TagPrefix.dust, GTMaterials.Coal, 1, 6500, 25)
                .chancedOutput(TagPrefix.dust, GTMaterials.Sulfur, 1, 8000, 50)
                .chancedOutput(TagPrefix.dust, GTMaterials.Redstone, 1, 5000, 50)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 1, 2000, 10)
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("obsidian_processing")
                .inputItems(new ItemStack(Blocks.OBSIDIAN), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Iron, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Oxygen.getFluid(144 * 3))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

    }

}
