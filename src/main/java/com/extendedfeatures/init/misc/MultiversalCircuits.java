package com.extendedfeatures.init.misc;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.client.EFCircuitTags;

import com.extendedfeatures.client.core.EFConfig;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;

@SuppressWarnings("unchecked")
public class MultiversalCircuits {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.CIRCUITS_TAB);
    }

    public static final ItemEntry<Item>[] MULTIVERSAL_CIRCUITS = new ItemEntry[GTValues.TIER_COUNT];

    private static final Object[][] MULTIVERSAL_CIRCUITS_DATA = {
            { GTValues.LV, "lv_multiversal_circuit", "§7LV §rMultiversal Circuit", EFCircuitTags.LV_MULTIVERSAL_GROUP },
            { GTValues.MV, "mv_multiversal_circuit", "§bMV §rMultiversal Circuit", EFCircuitTags.MV_MULTIVERSAL_GROUP },
            { GTValues.HV, "hv_multiversal_circuit", "§6HV §rMultiversal Circuit", EFCircuitTags.HV_MULTIVERSAL_GROUP },
            { GTValues.EV, "ev_multiversal_circuit", "§5EV §rMultiversal Circuit", EFCircuitTags.EV_MULTIVERSAL_GROUP },
            { GTValues.IV, "iv_multiversal_circuit", "§9IV §rMultiversal Circuit", EFCircuitTags.IV_MULTIVERSAL_GROUP },
            { GTValues.LuV, "luv_multiversal_circuit", "§dLuV §rMultiversal Circuit", EFCircuitTags.LuV_MULTIVERSAL_GROUP },
            { GTValues.ZPM, "zpm_multiversal_circuit", "§cZPM §rMultiversal Circuit", EFCircuitTags.ZPM_MULTIVERSAL_GROUP },
            { GTValues.UV, "uv_multiversal_circuit", "§3UV §rMultiversal Circuit", EFCircuitTags.UV_MULTIVERSAL_GROUP },
            { GTValues.UHV, "uhv_multiversal_circuit", "§4UHV §rMultiversal Circuit", EFCircuitTags.UHV_MULTIVERSAL_GROUP },
            { GTValues.UEV, "uev_multiversal_circuit", "§2UEV §rMultiversal Circuit", EFCircuitTags.UEV_MULTIVERSAL_GROUP },
            { GTValues.UIV, "uiv_multiversal_circuit", "§aUIV §rMultiversal Circuit", EFCircuitTags.UIV_MULTIVERSAL_GROUP },
            { GTValues.UXV, "uxv_multiversal_circuit", "§eUXV §rMultiversal Circuit", EFCircuitTags.UXV_MULTIVERSAL_GROUP },
            { GTValues.OpV, "opv_multiversal_circuit", "§l§9OpV §rMultiversal Circuit", EFCircuitTags.OpV_MULTIVERSAL_GROUP },
            { GTValues.OpV, "max_multiversal_circuit", "§l§cMAX §rMultiversal Circuit", EFCircuitTags.MAX_MULTIVERSAL_GROUP }
    };

    static {
        if (EFConfig.INSTANCE.MultiversalFeatures || GTCEu.isDataGen()) {

            for (Object[] data : MULTIVERSAL_CIRCUITS_DATA) {
                int tier = (int) data[0];

                String registryName = (String) data[1];
                String displayName = (String) data[2];

                TagKey<Item>[] tags = (TagKey<Item>[]) data[3];

                MULTIVERSAL_CIRCUITS[tier] = ExtendedFeaturesRegister
                        .item(registryName, Item::new)
                        .lang(displayName)
                        .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/" + registryName)))
                        .tag(tags)
                        .register();
            }
        }
    }

    // Fixed highTier filter
    public static List<ItemEntry<Item>> getMultiversalHighTierCircuits() {

        int[] highTiers = {
                GTValues.UHV,
                GTValues.UEV,
                GTValues.UIV,
                GTValues.UXV,
                GTValues.OpV,
                GTValues.MAX
        };

        List<ItemEntry<Item>> result = new ArrayList<>();

        for (int tier : highTiers) {
            ItemEntry<Item> entry = MULTIVERSAL_CIRCUITS[tier];

            if (entry != null) {
                result.add(entry);
            }
        }
        return result;
    }

    public static void register(IEventBus eventBus) {}
}
