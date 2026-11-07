package com.extendedfeatures.gtef.recipes;

import com.extendedfeatures.gtef.misc.MachineRegistry;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.extendedfeatures.gtef.misc.RecipeTypes.CHEMICAL_REDUCTION;

public class ChemicalSkips {

    public static void init(Consumer<FinishedRecipe> provider) {

        if (MachineRegistry.SYNTHESIS_VESSEL != null) {
            // Platline
            CHEMICAL_REDUCTION.recipeBuilder("platinum_processing_line")
                    .inputItems(TagPrefix.dust, GTMaterials.PlatinumGroupSludge, 12)
                    .inputFluids(GTMaterials.AquaRegia.getFluid(1500))
                    .outputItems(TagPrefix.dust, GTMaterials.Platinum, 4)
                    .outputItems(TagPrefix.dust, GTMaterials.Palladium, 3)
                    .outputItems(TagPrefix.dust, GTMaterials.Ruthenium, 2)
                    .outputItems(TagPrefix.dust, GTMaterials.Rhodium, 3)
                    .outputItems(TagPrefix.dust, GTMaterials.Osmium, 2)
                    .outputItems(TagPrefix.dust, GTMaterials.Iridium, 2)
                    .outputFluids(GTMaterials.NitricAcid.getFluid(2300))
                    .outputFluids(GTMaterials.HydrochloricAcid.getFluid(1500))
                    .EUt(GTValues.VA[GTValues.IV])
                    .duration(400)
                    .save(provider);

            // Naqline
            CHEMICAL_REDUCTION.recipeBuilder("naquadah_processing_line")
                    .inputItems(TagPrefix.dust, GTMaterials.Naquadah, 16)
                    .inputFluids(GTMaterials.FluoroantimonicAcid.getFluid(4400))
                    .outputItems(TagPrefix.dust, GTMaterials.NaquadahEnriched, 4)
                    .outputItems(TagPrefix.dust, GTMaterials.Naquadria, 2)
                    .outputItems(TagPrefix.dust, GTMaterials.Trinium, 3)
                    .outputItems(TagPrefix.dust, GTMaterials.Antimony, 5)
                    .outputItems(TagPrefix.dust, GTMaterials.Indium, 4)
                    .outputFluids(GTMaterials.Hydrogen.getFluid(2300))
                    .outputFluids(GTMaterials.Fluorine.getFluid(8100))
                    .EUt(GTValues.VA[GTValues.LuV])
                    .duration(500)
                    .save(provider);

            /* For 3.2.0, WIP
            Polyethylene (is this really needed?)
            Polyvynil Chloride
            Polytetrafluoroethylene
            Polybenzimidazole
            Polyvynil Butyral
            Polyphenile Sulfide
            Epoxy
            Sodium Persulfate
            Iron III Chloride
            Mutagen (Sterile)
            */

        }
    }
}
