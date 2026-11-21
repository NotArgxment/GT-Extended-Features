package com.extendedfeatures.gtef.recipes;

import com.extendedfeatures.gtef.misc.MachineRegistry;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.extendedfeatures.gtef.misc.FormatUtil.*;
import static com.extendedfeatures.gtef.misc.RecipeTypes.FISHER;

public class Fisher {

    // Powered by P-Corp Fishing Cards
    public static void init(Consumer<FinishedRecipe> provider) {

        if (MachineRegistry.FISHER != null) {
            FISHER.recipeBuilder("tiny_pile_meat")
                    .chancedInput(new ItemStack(Items.FISHING_ROD), 7500, 0)
                    .inputItems(TagPrefix.dustTiny, GTMaterials.Meat, 2)
                    .outputItemsRanged(Items.TROPICAL_FISH, ranged(1, 8))
                    .outputItemsRanged(Items.PUFFERFISH, ranged(1, 8))
                    .outputItemsRanged(Items.SALMON, ranged(1, 8))
                    .outputItemsRanged(Items.COD, ranged(1, 8))
                    .circuitMeta(1)
                    .duration(seconds(50))
                    .EUt(GTValues.VA[GTValues.HV])
                    .save(provider);

            FISHER.recipeBuilder("small_pile_meat")
                    .chancedInput(new ItemStack(Items.FISHING_ROD), 5000, 0)
                    .inputItems(TagPrefix.dustSmall, GTMaterials.Meat, 4)
                    .outputItemsRanged(Items.TROPICAL_FISH, ranged(4, 16))
                    .outputItemsRanged(Items.PUFFERFISH, ranged(4, 16))
                    .outputItemsRanged(Items.SALMON, ranged(4, 16))
                    .outputItemsRanged(Items.COD, ranged(4, 16))
                    .circuitMeta(2)
                    .duration(minutes(3,50))
                    .EUt(GTValues.VA[GTValues.IV])
                    .save(provider);

            FISHER.recipeBuilder("meat_pile")
                    .chancedInput(new ItemStack(Items.FISHING_ROD), 3500, 0)
                    .inputItems(TagPrefix.dust, GTMaterials.Meat, 8)
                    .outputItemsRanged(Items.TROPICAL_FISH, ranged(8, 32))
                    .outputItemsRanged(Items.PUFFERFISH, ranged(8, 32))
                    .outputItemsRanged(Items.SALMON, ranged(8, 32))
                    .outputItemsRanged(Items.COD, ranged(8, 32))
                    .circuitMeta(3)
                    .duration(minutes(6,50))
                    .EUt(GTValues.VA[GTValues.ZPM])
                    .save(provider);
        }

    }
}
