package com.extendedfeatures.gtef.recipes;

import com.extendedfeatures.gtef.misc.MachineRegistry;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.extendedfeatures.gtef.misc.RecipeTypes.ROCK_PROCESSING;

public class RockProcessing {

    public static void init(Consumer<FinishedRecipe> provider) {

        if (MachineRegistry.ROCK_PROCESSING_PLANT != null) {

            ROCK_PROCESSING.recipeBuilder("deepslate_processing")
                    .inputItems(new ItemStack(Blocks.DEEPSLATE))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .outputItems(
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Potassium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Magnesium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Aluminium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Silicon, 1)
                    )
                    .outputFluids(
                            GTMaterials.Fluorine.getFluid(144 * 2),
                            GTMaterials.Oxygen.getFluid(144 * 4)
                    )
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.EV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("andesite_processing")
                    .inputItems(new ItemStack(Blocks.ANDESITE))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .outputItems(
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Magnesium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Silicon, 1)
                    )
                    .outputFluids(
                            GTMaterials.Hydrogen.getFluid(144 * 2),
                            GTMaterials.Oxygen.getFluid(144 * 4)
                    )
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.MV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("diorite_processing")
                    .inputItems(
                            new ItemStack(Blocks.DIORITE)
                    )
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .outputItems(
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Sodium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Sulfur, 1)
                    )
                    .outputFluids(
                            GTMaterials.Fluorine.getFluid(144 * 2),
                            GTMaterials.Oxygen.getFluid(144 * 4)
                    )
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.MV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("granite_processing")
                    .inputItems(new ItemStack(Blocks.GRANITE))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .outputItems(
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.SiliconDioxide, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Calcite, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Flint, 1)
                    )
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.MV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("end_stone_processing")
                    .inputItems(new ItemStack(Blocks.END_STONE))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .chancedOutput(new ItemStack(Blocks.SAND), 8000, 5)
                    .chancedOutput(TagPrefix.dust, GTMaterials.Tungstate, 1, 5000, 5)
                    .chancedOutput(TagPrefix.dust, GTMaterials.Platinum, 1, 2500, 5)
                    .outputFluids(GTMaterials.Helium.getFluid(144))
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.LuV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("netherrack_processing")
                    .inputItems(new ItemStack(Blocks.NETHERRACK))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .chancedOutput(TagPrefix.dust, GTMaterials.Coal, 1, 6500, 25)
                    .chancedOutput(TagPrefix.dust, GTMaterials.Sulfur, 1, 8000, 50)
                    .chancedOutput(TagPrefix.dust, GTMaterials.Redstone, 1, 5000, 50)
                    .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 1, 2000, 10)
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.IV])
                    .save(provider);

            ROCK_PROCESSING.recipeBuilder("obsidian_processing")
                    .inputItems(new ItemStack(Blocks.OBSIDIAN))
                    .inputFluids(GTMaterials.Lubricant.getFluid(144 * 4))
                    .outputItems(
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Magnesium, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Iron, 1),
                            ChemicalHelper.get(TagPrefix.dust, GTMaterials.Silicon, 1)
                    )
                    .outputFluids(GTMaterials.Oxygen.getFluid(144 * 3))
                    .duration(1000)
                    .EUt(GTValues.VA[GTValues.HV])
                    .save(provider);
        }
    }
}