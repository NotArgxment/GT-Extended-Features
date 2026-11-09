package com.extendedfeatures.gtef.modifiers;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

public class ExtendedModifiers {

    public static RecipeModifier MACHINE_PARALLEL(int parallels,
                                                  float durationMultiplier,
                                                  float EnergyMultiplier) {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;
        return (MetaMachine machine, GTRecipe recipe) -> {
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);
            if (achievable <= 1) return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(durationMultiplier)
                    .eutMultiplier(EnergyMultiplier)
                    .parallels(achievable)
                    .build();
        };
    }

    // These should scale linearly
    public static RecipeModifier SCALLING_PARALLELS(int baseParallels,
                                                    int maxParallels,
                                                    int parallelsPerCycle,
                                                    double durationPerExtra) {
        return (MetaMachine machine, GTRecipe recipe) -> {
            if (!(machine instanceof IRecipeLogicMachine repLogMac)) return ModifierFunction.IDENTITY;
            int cycles = repLogMac.getRecipeLogic().getConsecutiveRecipes();
            int target = Math.min(baseParallels, maxParallels + parallelsPerCycle * cycles);
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, target);
            if (achievable <= 1) return ModifierFunction.IDENTITY;
            double durationMult = 1.0 + durationPerExtra * (achievable - 1);
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(durationMult)
                    .parallels(achievable)
                    .build();
        };
    }

}
