package com.extendedfeatures.gtef.misc;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.client.internal.EFConfig;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;

@SuppressWarnings("unchecked")
public class UniversalCircuits {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.CIRCUITS_TAB);
    }

    // Credits to witherschat for allowing me to use the universal circuits textures
    public static final ItemEntry<Item>[] UNIVERSAL_CIRCUITS = new ItemEntry[GTValues.TIER_COUNT];

    static {
        if (EFConfig.INSTANCE.UniversalCircuits || GTCEu.isDataGen()) {
            circuitBuilder("ulv_universal_circuit", "§8ULV §rUniversal Circuit", CustomTags.ULV_CIRCUITS, GTValues.ULV);
            circuitBuilder("lv_universal_circuit", "§7LV §rUniversal Circuit", CustomTags.LV_CIRCUITS, GTValues.LV);
            circuitBuilder("mv_universal_circuit", "§bMV §rUniversal Circuit", CustomTags.MV_CIRCUITS, GTValues.MV);
            circuitBuilder("hv_universal_circuit", "§6HV §rUniversal Circuit", CustomTags.HV_CIRCUITS, GTValues.HV);
            circuitBuilder("ev_universal_circuit", "§5EV §rUniversal Circuit", CustomTags.EV_CIRCUITS, GTValues.EV);
            circuitBuilder("iv_universal_circuit", "§9IV §rUniversal Circuit", CustomTags.IV_CIRCUITS, GTValues.IV);
            circuitBuilder("luv_universal_circuit", "§dLuV §rUniversal Circuit", CustomTags.LuV_CIRCUITS, GTValues.LuV);
            circuitBuilder("zpm_universal_circuit", "§cZPM §rUniversal Circuit", CustomTags.ZPM_CIRCUITS, GTValues.ZPM);
            circuitBuilder("uv_universal_circuit", "§3UV §rUniversal Circuit", CustomTags.UV_CIRCUITS, GTValues.UV);
            circuitBuilder("uhv_universal_circuit", "§4UHV §rUniversal Circuit", CustomTags.UHV_CIRCUITS, GTValues.UHV);
            circuitBuilder("uev_universal_circuit", "§2UEV §rUniversal Circuit", CustomTags.UEV_CIRCUITS, GTValues.UEV);
            circuitBuilder("uiv_universal_circuit", "§aUIV §rUniversal Circuit", CustomTags.UIV_CIRCUITS, GTValues.UIV);
            circuitBuilder("uxv_universal_circuit", "§eUXV §rUniversal Circuit", CustomTags.UXV_CIRCUITS, GTValues.UXV);
            circuitBuilder("opv_universal_circuit", "§9OpV §rUniversal Circuit", CustomTags.OpV_CIRCUITS, GTValues.OpV);
        }
    }

    private static void circuitBuilder(String registryName,
                                       String displayName,
                                       TagKey<Item> tag,
                                       int tier) {
        UNIVERSAL_CIRCUITS[tier] = ExtendedFeaturesRegister
                .item(registryName, Item::new)
                .lang(displayName)
                .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/" + registryName)))
                .tag(tag)
                .register();
    }

    // "isHighTier" filter
    public static List<ItemEntry<Item>> getUniversalHighTierCircuits() {
        List<ItemEntry<Item>> result = new ArrayList<>();
        for (int tier : new int[] {
                GTValues.UHV,
                GTValues.UEV,
                GTValues.UIV,
                GTValues.UXV,
                GTValues.OpV }
        ) {
            ItemEntry<Item> entry = UNIVERSAL_CIRCUITS[tier];
            if (entry != null) result.add(entry);
        }
        return result;
    }

    public static void register(IEventBus eventBus) {}
}