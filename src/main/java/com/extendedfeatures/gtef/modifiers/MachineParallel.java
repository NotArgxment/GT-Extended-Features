package com.extendedfeatures.gtef.modifiers;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

public class MachineParallel {

    public static RecipeModifier MACHINE_PARALLEL(int parallels, float durationMultiplier, float EnergyMultiplier) {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;
        return (MetaMachine machine, GTRecipe recipe) -> {
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);
            if (achievable <= 1)
                return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(durationMultiplier)
                    .eutMultiplier(EnergyMultiplier)
                    .parallels(achievable)
                    .build();
        };
    }

    // if i ever think about a new modifier, that would be a new class...
}
