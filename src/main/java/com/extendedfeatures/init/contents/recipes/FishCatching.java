package com.extendedfeatures.init.contents.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.FISH_CATCHING;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Coal;

//            FISHKISSFISHKIS
//        SFISHKISSFISHKISSFISH            F
//     ISHK   ISSFISHKISSFISHKISS         FI
//   SHKISS   FISHKISSFISHKISSFISS       FIS
// HKISSFISHKISSFISHKISSFISHKISSFISH    KISS
//   FISHKISSFISHKISSFISHKISSFISHKISS  FISHK
//       SSFISHKISSFISHKISSFISHKISSFISHKISSF
//   ISHKISSFISHKISSFISHKISSFISHKISSF  ISHKI
// SSFISHKISSFISHKISSFISHKISSFISHKIS    SFIS
//   HKISSFISHKISSFISHKISSFISHKISS       FIS
//     HKISSFISHKISSFISHKISSFISHK         IS
//        SFISHKISSFISHKISSFISH            K
//          ISSFISHKISSFISHK
public class FishCatching {

    public static void init(Consumer<FinishedRecipe> provider) {

        FISH_CATCHING.recipeBuilder("mince_meat_tiny_pile")
                .inputItems(TagPrefix.dustTiny, GTMaterials.Meat, 4)
                .inputItems(Items.STRING, 8)
                .outputItems(Items.TROPICAL_FISH, 3)
                .outputItems(Items.PUFFERFISH, 4)
                .outputItems(Items.TROPICAL_FISH, 1)
                .circuitMeta(1)
                .duration(500)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        FISH_CATCHING.recipeBuilder("mince_meat_small_pile")
                .inputItems(TagPrefix.dustSmall, GTMaterials.Meat, 2)
                .inputItems(Items.STRING, 8)
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(2, 8))
                .outputItemsRanged(Items.PUFFERFISH, UniformInt.of(1, 4))
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(4, 12))
                .circuitMeta(2)
                .duration(500)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        FISH_CATCHING.recipeBuilder("mince_meat")
                .inputItems(TagPrefix.dust, GTMaterials.Meat, 1)
                .inputItems(Items.STRING, 8)
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(2, 8))
                .outputItemsRanged(Items.PUFFERFISH, UniformInt.of(1, 4))
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(4, 12))
                .circuitMeta(3)
                .duration(500)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

    }

}
