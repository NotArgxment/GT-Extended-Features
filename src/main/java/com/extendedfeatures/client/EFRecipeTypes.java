package com.extendedfeatures.client;

import com.extendedfeatures.ExtendedFeaturesCore;
import com.extendedfeatures.client.core.logic.multiblock.disassembler.DisassemblerRecipeLogic;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static net.minecraft.core.registries.BuiltInRegistries.*;

@SuppressWarnings("deprecated")
public class EFRecipeTypes {

    public static GTRecipeType ROCK_PROCESSING_RECIPES = RecipeTypeRegistry("rock_processing_plant", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 6, 1, 3)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MACERATOR);

    public static GTRecipeType GREENHOUSE_WOOD = RecipeTypeRegistry("greenhouse_wood_recipes", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(3, 3, 1, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MIXER);

    public static GTRecipeType GREENHOUSE_CROPS = RecipeTypeRegistry("greenhouse_crop_recipes", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(3, 3, 1, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MIXER);

    public static GTRecipeType DISASSEMBLER_MACHINES = RecipeTypeRegistry("machine_disassembly", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 9, 0, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.ASSEMBLER)
                .addCustomRecipeLogic(DisassemblerRecipeLogic.INSTANCE);

    public static GTRecipeType DISASSEMBER_COMPONENTS = RecipeTypeRegistry("component_disassembly", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 9, 0, 1)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.ASSEMBLER);

    public static GTRecipeType CHEMICAL_REDUCTION = RecipeTypeRegistry("chemical_skips", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(6, 6, 6, 6)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.CHEMICAL);

    public static GTRecipeType AIR_COLLECTOR = RecipeTypeRegistry("air_collection", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 0, 0, 1)
                .setProgressBar(GuiTextures.CIRCUIT_OVERLAY, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.COOLING);

    // Addon's own RecipeType - otherwise they RecipeTypeRegistry under GTCEu Namespace
    public static GTRecipeType RecipeTypeRegistry(String name, String group, RecipeType<?>... proxyRecipes) {
        ResourceLocation id = ExtendedFeaturesCore.id(name);

        var recipeType = new GTRecipeType(id, group, proxyRecipes);
        GTRegistries.register(RECIPE_TYPE, id, recipeType);
        GTRegistries.register(RECIPE_SERIALIZER, id, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(id, recipeType);

        return recipeType;
    }

    public static void init(){
        // yawn
    }

}
