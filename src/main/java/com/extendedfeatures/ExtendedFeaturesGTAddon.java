package com.extendedfeatures;

import com.extendedfeatures.client.EFLangManager;
import com.extendedfeatures.init.recipes.*;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import com.tterrag.registrate.providers.ProviderType;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class ExtendedFeaturesGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return ExtendedFeaturesCore.ExtendedFeaturesRegister;
    }

    @Override
    public void initializeAddon() {
        ExtendedFeaturesCore.ExtendedFeaturesRegister.addDataGenerator(
                ProviderType.LANG, EFLangManager::init
        );
    }

    @Override
    public String addonModId() {
        return ExtendedFeaturesCore.MOD_ID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        Greenhouse.init(provider);
        ChemicalSkips.init(provider);
        Disassembler.init(provider);
        GasCollector.init(provider);
        RockProcessing.init(provider);
        Fisher.init(provider);
        Multiversal.init(provider);
        Universal.init(provider);
    }

}
