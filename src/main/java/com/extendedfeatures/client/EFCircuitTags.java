package com.extendedfeatures.client;

import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

@SuppressWarnings("unchecked")
public class EFCircuitTags {

    public static final TagKey<Item>[] LV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.ULV_CIRCUITS,
            CustomTags.LV_CIRCUITS
    };

    public static final TagKey<Item>[] MV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.LV_CIRCUITS,
            CustomTags.MV_CIRCUITS
    };

    public static final TagKey<Item>[] HV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.MV_CIRCUITS,
            CustomTags.HV_CIRCUITS
    };

    public static final TagKey<Item>[] EV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.HV_CIRCUITS,
            CustomTags.EV_CIRCUITS
    };

    public static final TagKey<Item>[] IV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.EV_CIRCUITS,
            CustomTags.IV_CIRCUITS
    };

    public static final TagKey<Item>[] LuV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.EV_CIRCUITS,
            CustomTags.IV_CIRCUITS,
            CustomTags.LuV_CIRCUITS
    };

    public static final TagKey<Item>[] ZPM_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.IV_CIRCUITS,
            CustomTags.LuV_CIRCUITS,
            CustomTags.ZPM_CIRCUITS
    };

    public static final TagKey<Item>[] UV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.LuV_CIRCUITS,
            CustomTags.ZPM_CIRCUITS,
            CustomTags.UV_CIRCUITS
    };

    public static final TagKey<Item>[] UHV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.LuV_CIRCUITS,
            CustomTags.ZPM_CIRCUITS,
            CustomTags.UV_CIRCUITS,
            CustomTags.UHV_CIRCUITS
    };

    public static final TagKey<Item>[] UEV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.ZPM_CIRCUITS,
            CustomTags.UV_CIRCUITS,
            CustomTags.UHV_CIRCUITS,
            CustomTags.UEV_CIRCUITS
    };

    public static final TagKey<Item>[] UIV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.UV_CIRCUITS,
            CustomTags.UHV_CIRCUITS,
            CustomTags.UEV_CIRCUITS,
            CustomTags.UIV_CIRCUITS
    };

    public static final TagKey<Item>[] UXV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.UHV_CIRCUITS,
            CustomTags.UEV_CIRCUITS,
            CustomTags.UIV_CIRCUITS,
            CustomTags.UXV_CIRCUITS
    };

    public static final TagKey<Item>[] OpV_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.UHV_CIRCUITS,
            CustomTags.UEV_CIRCUITS,
            CustomTags.UIV_CIRCUITS,
            CustomTags.UXV_CIRCUITS,
            CustomTags.OpV_CIRCUITS
    };

    public static final TagKey<Item>[] MAX_MULTIVERSAL_GROUP = new TagKey[]{
            CustomTags.UHV_CIRCUITS,
            CustomTags.UEV_CIRCUITS,
            CustomTags.UIV_CIRCUITS,
            CustomTags.UXV_CIRCUITS,
            CustomTags.OpV_CIRCUITS,
            CustomTags.MAX_CIRCUITS
    };

}