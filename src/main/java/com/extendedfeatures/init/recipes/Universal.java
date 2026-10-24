package com.extendedfeatures.init.recipes;

import com.extendedfeatures.init.misc.UniversalCircuits;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class Universal {

    public static void init(Consumer<FinishedRecipe> provider) {
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
