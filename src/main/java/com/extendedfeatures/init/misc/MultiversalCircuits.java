package com.extendedfeatures.init.misc;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.client.EFCircuitTags;

import com.gregtechceu.gtceu.api.GTValues;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;

public class MultiversalCircuits {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.CIRCUITS_TAB);
    }

    public static ItemEntry<Item> LV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("lv_multiversal_circuit", Item::new)
            .lang("§8LV §rMultiversal Circuit")
            .tag(EFCircuitTags.LV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> MV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("mv_multiversal_circuit", Item::new)
            .lang("§BMV §rMultiversal Circuit")
            .tag(EFCircuitTags.MV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> HV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("hv_multiversal_circuit", Item::new)
            .lang("§6HV §rMultiversal Circuit")
            .tag(EFCircuitTags.HV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> EV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("ev_multiversal_circuit", Item::new)
            .lang("§5EV §rMultiversal Circuit")
            .tag(EFCircuitTags.EV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> IV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("iv_multiversal_circuit", Item::new)
            .lang("§9IV §rMultiversal Circuit")
            .tag(EFCircuitTags.IV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> LuV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("luv_multiversal_circuit", Item::new)
            .lang("§dLuV §rMultiversal Circuit")
            .tag(EFCircuitTags.LuV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> ZPM_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("zpm_multiversal_circuit", Item::new)
            .lang("§cZPM §rMultiversal Circuit")
            .tag(EFCircuitTags.ZPM_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> UV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("uv_multiversal_circuit", Item::new)
            .lang("§sUV §rMultiversal Circuit")
            .tag(EFCircuitTags.UV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> UHV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("uhv_multiversal_circuit", Item::new)
            .lang("§4UHV §rMultiversal Circuit")
            .tag(EFCircuitTags.UHV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> UEV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("uev_multiversal_circuit", Item::new)
            .lang("§2UEV §rMultiversal Circuit")
            .tag(EFCircuitTags.UEV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> UIV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("uiv_multiversal_circuit", Item::new)
            .lang("§aUIV §rMultiversal Circuit")
            .tag(EFCircuitTags.UIV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> UXV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("uxv_multiversal_circuit", Item::new)
            .lang("§eUXV §rMultiversal Circuit")
            .tag(EFCircuitTags.UXV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> OpV_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("opv_multiversal_circuit", Item::new)
            .lang("§9§lOpV §rMultiversal Circuit")
            .tag(EFCircuitTags.OpV_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    public static ItemEntry<Item> MAX_MULTICIRCUIT = ExtendedFeaturesRegister
            .item("max_multiversal_circuit", Item::new)
            .lang("§c§lMAX §rMultiversal Circuit")
            .tag(EFCircuitTags.MAX_MULTIVERSAL_GROUP)
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/multiversal/micro_processor")))
            .register();

    // Fixed highTier filter
    public static ItemEntry<Item> getTier(int tier) {
        return switch (tier) {
            case GTValues.LV -> LV_MULTICIRCUIT;
            case GTValues.MV -> MV_MULTICIRCUIT;
            case GTValues.HV -> HV_MULTICIRCUIT;
            case GTValues.EV -> EV_MULTICIRCUIT;
            case GTValues.IV -> IV_MULTICIRCUIT;
            case GTValues.LuV -> LuV_MULTICIRCUIT;
            case GTValues.ZPM -> ZPM_MULTICIRCUIT;
            case GTValues.UV -> UV_MULTICIRCUIT;
            case GTValues.UHV -> UHV_MULTICIRCUIT;
            case GTValues.UEV -> UEV_MULTICIRCUIT;
            case GTValues.UIV -> UIV_MULTICIRCUIT;
            case GTValues.UXV -> UXV_MULTICIRCUIT;
            case GTValues.OpV -> OpV_MULTICIRCUIT;
            case GTValues.MAX -> MAX_MULTICIRCUIT;
            default -> null;
        };
    }

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
            ItemEntry<Item> entry = MultiversalCircuits.getTier(tier);
            if (entry != null && entry.isPresent()) {
                result.add(entry);
            }
        }
        return result;
    }

    public static void register(IEventBus eventBus) {}
}
