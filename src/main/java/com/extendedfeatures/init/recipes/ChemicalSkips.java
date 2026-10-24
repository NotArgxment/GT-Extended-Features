package com.extendedfeatures.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.extendedfeatures.client.EFRecipeTypes.CHEMICAL_REDUCTION;

public class ChemicalSkips {

    public static void init(Consumer<FinishedRecipe> provider) {

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

        /* For 3.1.0, WIP

        // ======================================
        //          Miscellaneous Skips
        // ======================================

        // Polyethylene (is this really needed?)
        CHEMICAL_REDUCTION.recipeBuilder("polyethylene")

                .outputFluids(Polyethylene.getFluid(8000))
                .circuitMeta(9)
                .duration(100)
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        // PVC
        CHEMICAL_REDUCTION.recipeBuilder("polyvinyl_chloride")

                .outputFluids(PolyvinylChloride.getFluid(16000))
                .circuitMeta(11)
                .duration(100)
                .EUt(GTValues.VA[GTValues.HV])
                .save(provider);

        // PTFE
        CHEMICAL_REDUCTION.recipeBuilder("polytetrafluoroethylene")

                .outputFluids(Polytetrafluoroethylene.getFluid(8000))
                .circuitMeta(6)
                .duration(300)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        // PBI
        CHEMICAL_REDUCTION.recipeBuilder("polybenzimidazole")

                .outputFluids(Polybenzimidazole.getFluid(8000))
                .circuitMeta(18)
                .duration(600)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        // PVB
        CHEMICAL_REDUCTION.recipeBuilder("polyvinyl_butyral")

                .outputFluids(PolyvinylButyral.getFluid(8000))
                .circuitMeta(3)
                .duration(250)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        // PPS
        CHEMICAL_REDUCTION.recipeBuilder("polyphenylene_sulfide")

                .outputFluids(PolyphenyleneSulfide.getFluid(6500))
                .circuitMeta(9)
                .duration(300)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        // Epoxy
        CHEMICAL_REDUCTION.recipeBuilder("epoxy")

                .outputFluids(Epoxy.getFluid(7100))
                .circuitMeta(22)
                .duration(500)
                .EUt(GTValues.VA[GTValues.IV])
                .save(provider);

        // Sodium Persulfate


        // Iron III Chloride

        // Mutagen

        */

    }
}
