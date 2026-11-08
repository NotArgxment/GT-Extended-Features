package com.extendedfeatures.gtef.recipes;

import com.extendedfeatures.gtef.misc.UniversalCircuits;
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

        circuitBuilder(provider, "ulv_universal_circuit", CustomTags.ULV_CIRCUITS, GTValues.ULV);
        circuitBuilder(provider, "lv_universal_circuit", CustomTags.LV_CIRCUITS, GTValues.LV);
        circuitBuilder(provider, "mv_universal_circuit", CustomTags.MV_CIRCUITS, GTValues.MV);
        circuitBuilder(provider, "hv_universal_circuit", CustomTags.HV_CIRCUITS, GTValues.HV);
        circuitBuilder(provider, "ev_universal_circuit", CustomTags.EV_CIRCUITS, GTValues.EV);
        circuitBuilder(provider, "iv_universal_circuit", CustomTags.IV_CIRCUITS, GTValues.IV);
        circuitBuilder(provider, "luv_universal_circuit", CustomTags.LuV_CIRCUITS, GTValues.LuV);
        circuitBuilder(provider, "zpm_universal_circuit", CustomTags.ZPM_CIRCUITS, GTValues.ZPM);
        circuitBuilder(provider, "uv_universal_circuit", CustomTags.UV_CIRCUITS, GTValues.UV);

        if (GTCEuAPI.isHighTier()) {
            circuitBuilder(provider, "uhv_universal_circuit", CustomTags.UHV_CIRCUITS, GTValues.UHV);
            circuitBuilder(provider, "uev_universal_circuit", CustomTags.UEV_CIRCUITS, GTValues.UEV);
            circuitBuilder(provider, "uiv_universal_circuit", CustomTags.UIV_CIRCUITS, GTValues.UIV);
            circuitBuilder(provider, "uxv_universal_circuit", CustomTags.UXV_CIRCUITS, GTValues.UXV);
            circuitBuilder(provider, "opv_universal_circuit", CustomTags.OpV_CIRCUITS, GTValues.OpV);
            circuitBuilder(provider, "max_universal_circuit", CustomTags.MAX_CIRCUITS, GTValues.MAX);
        }
    }

    private static void circuitBuilder(Consumer<FinishedRecipe> provider, String recipeName,
                                       TagKey<Item> inputAnyCircuit, int circuitTier) {
        ItemEntry<Item> circuitOutput = UniversalCircuits.UNIVERSAL_CIRCUITS[circuitTier];
        if (circuitOutput == null) return;
        var builder = ASSEMBLER_RECIPES.recipeBuilder(recipeName)
                .inputItems(inputAnyCircuit)
                .outputItems(circuitOutput)
                .duration(10)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(16);
        builder.save(provider);
    }
}
