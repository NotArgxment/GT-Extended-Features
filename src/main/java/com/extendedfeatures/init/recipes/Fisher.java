package com.extendedfeatures.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.FISHER;

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
public class Fisher {

    public static void init(Consumer<FinishedRecipe> provider) {

        FISHER.recipeBuilder("mince_meat_c1")
                .inputItems(TagPrefix.dustSmall, GTMaterials.Meat, 2)
                .inputItems(Items.STRING, 32)
                .inputFluids(GTMaterials.Biomass.getFluid(144))
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(1, 8))
                .outputItemsRanged(Items.PUFFERFISH, UniformInt.of(1, 8))
                .outputItemsRanged(Items.SALMON, UniformInt.of(1, 12))
                .outputItemsRanged(Items.COD, UniformInt.of(1, 6))
                .circuitMeta(1)
                .duration(1500)
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        FISHER.recipeBuilder("mince_meat_c2")
                .inputItems(TagPrefix.dustSmall, GTMaterials.Meat, 2)
                .inputItems(Items.STRING, 32)
                .inputFluids(GTMaterials.FermentedBiomass.getFluid(144))
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(1, 16))
                .outputItemsRanged(Items.PUFFERFISH, UniformInt.of(1, 12))
                .outputItemsRanged(Items.SALMON, UniformInt.of(1, 24))
                .outputItemsRanged(Items.COD, UniformInt.of(1, 8))
                .circuitMeta(2)
                .duration(1200)
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        FISHER.recipeBuilder("collagen")
                .inputItems(TagPrefix.dustSmall, GTMaterials.Collagen, 2)
                .inputItems(Items.STRING, 32)
                .inputFluids(GTMaterials.SeedOil.getFluid(288))
                .outputItemsRanged(Items.TROPICAL_FISH, UniformInt.of(4, 24))
                .outputItemsRanged(Items.PUFFERFISH, UniformInt.of(4, 16))
                .outputItemsRanged(Items.SALMON, UniformInt.of(4, 32))
                .outputItemsRanged(Items.COD, UniformInt.of(4, 48))
                .circuitMeta(3)
                .duration(1000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

    }

}
