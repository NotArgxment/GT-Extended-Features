package com.extendedfeatures.init.recipes;

import com.extendedfeatures.client.EFMachineRegistry;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class GasCollector {

    public static void init(Consumer<FinishedRecipe> provider) {

        VanillaRecipeHelper.addShapedRecipe(
                provider, false, "large_gas_collector",
                EFMachineRegistry.LARGE_GAS_COLLECTOR.asStack(),
                "MCM",
                "BXB",
                "PKP",
                'C', CustomTags.IV_CIRCUITS,
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.CobaltBrass),
                'B', GTItems.ELECTRIC_MOTOR_IV.asStack(),
                'M', GTItems.ELECTRIC_PUMP_IV.asStack(),
                'X', GTMachines.GAS_COLLECTOR[IV].asStack(),
                'K', new MaterialEntry(TagPrefix.cableGtSingle, GTMaterials.Platinum));

        GAS_COLLECTOR.recipeBuilder("air")
                .circuitMeta(1)
                .outputFluids(GTMaterials.Air.getFluid(4000))
                .dimension(Level.OVERWORLD.location())
                .duration(100)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        GAS_COLLECTOR.recipeBuilder("nether_air")
                .circuitMeta(2)
                .outputFluids(GTMaterials.NetherAir.getFluid(4000))
                .dimension(Level.NETHER.location())
                .duration(200)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        GAS_COLLECTOR.recipeBuilder("ender_air")
                .circuitMeta(3)
                .outputFluids(GTMaterials.EnderAir.getFluid(4000))
                .dimension(Level.END.location())
                .duration(300)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

    }
}
