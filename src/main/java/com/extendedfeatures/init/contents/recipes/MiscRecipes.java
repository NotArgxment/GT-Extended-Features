package com.extendedfeatures.init.contents.recipes;

import com.extendedfeatures.client.EFMachineRegistry;
import com.extendedfeatures.init.contents.misc.UniversalCircuits;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class MiscRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {

        // ===============
        // Rock Processing
        // ===============

        final int rpfTime = 1200;
        final int rpfEnergy = GTValues.VA[GTValues.EV];

        ROCK_PROCESSING_RECIPES.recipeBuilder("deepslate_processing")
                .inputItems(new ItemStack(Blocks.DEEPSLATE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Potassium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Aluminium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Fluorine.getFluid(L * 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(L * 4))
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("andesite_processing")
                .inputItems(new ItemStack(Blocks.ANDESITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Hydrogen.getFluid(L * 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(L * 4))
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("diorite_processing")
                .inputItems(new ItemStack(Blocks.DIORITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .outputItems(TagPrefix.dust, Sodium, 1)
                .outputItems(TagPrefix.dust, Sulfur, 1)
                .outputFluids(GTMaterials.Water.getFluid(L * 4))
                .outputFluids(GTMaterials.Oxygen.getFluid(L * 2))
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("granite_processing")
                .inputItems(new ItemStack(Blocks.GRANITE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .outputItems(TagPrefix.dust, GTMaterials.SiliconDioxide, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Calcite, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Flint, 1)
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("end_stone_processing")
                .inputItems(new ItemStack(Blocks.END_STONE), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .chancedOutput(new ItemStack(Blocks.SAND), 8000, 5)
                .chancedOutput(TagPrefix.dust, GTMaterials.Tungstate, 1, 5000, 5)
                .chancedOutput(TagPrefix.dust, GTMaterials.Platinum, 1, 2500, 5)
                .outputFluids(GTMaterials.Helium.getFluid(L))
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("netherrack_processing")
                .inputItems(new ItemStack(Blocks.NETHERRACK), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .chancedOutput(TagPrefix.dust, GTMaterials.Coal, 1, 6500, 25)
                .chancedOutput(TagPrefix.dust, GTMaterials.Sulfur, 1, 8000, 50)
                .chancedOutput(TagPrefix.dust, GTMaterials.Redstone, 1, 5000, 50)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 1, 2000, 10)
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        ROCK_PROCESSING_RECIPES.recipeBuilder("obsidian_processing")
                .inputItems(new ItemStack(Blocks.OBSIDIAN), 1)
                .inputFluids(GTMaterials.Lubricant.getFluid(L * 4))
                .outputItems(TagPrefix.dust, GTMaterials.Magnesium, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Iron, 1)
                .outputItems(TagPrefix.dust, GTMaterials.Silicon, 1)
                .outputFluids(GTMaterials.Oxygen.getFluid(L * 3))
                .duration(rpfTime)
                .EUt(rpfEnergy)
                .save(provider);

        // ===================
        // Large Gas Collector
        // ===================

        VanillaRecipeHelper.addShapedRecipe(
                provider, false, "large_gas_collector",
                EFMachineRegistry.LARGE_GAS_COLLECTOR.asStack(),
                "MCM", 
                "BXB",
                "PKP",
                'C', CustomTags.IV_CIRCUITS,
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.CobaltBrass),
                'B', GTItems.ELECTRIC_MOTOR_IV.asStack(),
                'M', GTItems.ELECTRIC_PUMP_IV.asStack(),
                'X', GTMachines.GAS_COLLECTOR[IV].asStack(),
                'K', new MaterialEntry(TagPrefix.cableGtSingle, GTMaterials.Platinum));

        GAS_COLLECTOR.recipeBuilder("air")
                .circuitMeta(1)
                .outputFluids(GTMaterials.Air.getFluid(4000))
                .dimension(Level.OVERWORLD.location())
                .duration(100)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        GAS_COLLECTOR.recipeBuilder("nether_air")
                .circuitMeta(2)
                .outputFluids(GTMaterials.NetherAir.getFluid(4000))
                .dimension(Level.NETHER.location())
                .duration(200)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        GAS_COLLECTOR.recipeBuilder("ender_air")
                .circuitMeta(3)
                .outputFluids(GTMaterials.EnderAir.getFluid(4000))
                .dimension(Level.END.location())
                .duration(300)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        // ===================
        // Universal Circuits
        // ===================
        circuitRecipe(provider, "ulv_universal_circuit", CustomTags.ULV_CIRCUITS, GTValues.ULV);
        circuitRecipe(provider, "lv_universal_circuit", CustomTags.LV_CIRCUITS, GTValues.LV);
        circuitRecipe(provider, "mv_universal_circuit", CustomTags.MV_CIRCUITS, GTValues.MV);
        circuitRecipe(provider, "hv_universal_circuit", CustomTags.HV_CIRCUITS, GTValues.HV);
        circuitRecipe(provider, "ev_universal_circuit", CustomTags.EV_CIRCUITS, GTValues.EV);
        circuitRecipe(provider, "iv_universal_circuit", CustomTags.IV_CIRCUITS, GTValues.IV);
        circuitRecipe(provider, "luv_universal_circuit", CustomTags.LuV_CIRCUITS, GTValues.LuV);
        circuitRecipe(provider, "zpm_universal_circuit", CustomTags.ZPM_CIRCUITS, GTValues.ZPM);
        circuitRecipe(provider, "uv_universal_circuit", CustomTags.UV_CIRCUITS, GTValues.UV);

        if (GTCEuAPI.isHighTier()) {
            circuitRecipe(provider, "uhv_universal_circuit", CustomTags.UHV_CIRCUITS, GTValues.UHV);
            circuitRecipe(provider, "uev_universal_circuit", CustomTags.UEV_CIRCUITS, GTValues.UEV);
            circuitRecipe(provider, "uiv_universal_circuit", CustomTags.UIV_CIRCUITS, GTValues.UIV);
            circuitRecipe(provider, "uxv_universal_circuit", CustomTags.UXV_CIRCUITS, GTValues.UXV);
            circuitRecipe(provider, "opv_universal_circuit", CustomTags.OpV_CIRCUITS, GTValues.OpV);
        }
    }

    private static void circuitRecipe(Consumer<FinishedRecipe> provider,
                                      String recipeName,
                                      TagKey<Item> inputTag,
                                      int tier) {
        ItemEntry<Item> output = UniversalCircuits.UNIVERSAL_CIRCUITS[tier];
        if (output == null) return;

        ASSEMBLER_RECIPES.recipeBuilder(recipeName)
                .inputItems(inputTag)
                .outputItems(output)
                .duration(10)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(16)
                .save(provider);
    }

}
