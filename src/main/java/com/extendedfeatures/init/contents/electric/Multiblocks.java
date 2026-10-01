package com.extendedfeatures.init.contents.electric;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.ExtendedFeaturesCore;
import com.extendedfeatures.client.EFDisplayHelper;
import com.extendedfeatures.client.EFRecipeTypes;
import com.extendedfeatures.client.EFTooltipHelper;
import com.extendedfeatures.client.core.logic.multiblock.ExpandedAssemblyLineMachine;
import com.extendedfeatures.client.core.logic.multiblock.MatrixDataRelayMachine;
import com.extendedfeatures.client.EFShapeInfosHelper;
import com.extendedfeatures.init.contents.misc.ExtendedAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.ActiveTransformerMachine;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;
import static com.extendedfeatures.client.EFMachineRegistry.*;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterialItems.MATERIAL_ITEMS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Ash;

public class Multiblocks {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.MULTIBLOCKS_TAB);
    }

    static {
        if (MultiblocksConfig.RobustAlloyMaterializer || GTCEu.isDataGen()) {
            ROBUST_ALLOY_MATERIALIZER = ExtendedFeaturesRegister
                    .multiblock("robust_alloy_materializer", CoilWorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.RAMTooltip)
                    .tooltipBuilder(EFTooltipHelper.RAMTooltipExtra)
                    .rotationState(RotationState.ALL)
                    .recipeType(GCYMRecipeTypes.ALLOY_BLAST_RECIPES)
                    .recipeModifiers(
                            GTRecipeModifiers.OC_PERFECT,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers::ebfOverclock)
                    .appearanceBlock(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("   CCC   ", "   XXX   ", "   XXX   ", "   EEE   ", "   XXX   ", "   XXX   ", "   CCC   ")
                            .aisle(" BBBCBBB ", " XXTTTXX ", " XXTTTXX ", " EETFTEE ", " XXTTTXX ", " XXTTTXX ", " BBBCBBB ")
                            .aisle(" BBBCBBB ", " XETTTEX ", " XETTTEX ", " EETFTEE ", " XETTTEX ", " XETTTEX ", " BEECEEB ")
                            .aisle("CBBCCCBBC", "XTTTTTTTX", "XTTTTTTTX", "ETTTFTTTE", "XTTTTTTTX", "XTTTTTTTX", "CBECCCEBC")
                            .aisle("CCCCECCCC", "XTTTFTTTX", "XTTTFTTTX", "EFFFFFFFE", "XTTTFTTTX", "XTTTFTTTX", "CCCCLCCCC")
                            .aisle("CBBCCCBBC", "XTTTTTTTX", "XTTTTTTTX", "ETTTFTTTE", "XTTTTTTTX", "XTTTTTTTX", "CBECCCEBC")
                            .aisle(" BBBCBBB ", " XETTTEX ", " XETTTEX ", " EETFTEE ", " XETTTEX ", " XETTTEX ", " BEECEEB ")
                            .aisle(" BBBCBBB ", " XXTTTXX ", " XXTTTXX ", " EETFTEE ", " XXTTTXX ", " XXTTTXX ", " BBBCBBB ")
                            .aisle("   C@C   ", "   XXX   ", "   XXX   ", "   EEE   ", "   XXX   ", "   XXX   ", "   CCC   ")
                            .where('@', controller(blocks(definition.get())))
                            .where('X', heatingCoils())
                            .where('T', air())
                            .where(' ', any())
                            .where('C', blocks(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.get()).setMinGlobalLimited(40)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(1))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .where('B', Predicates.blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()))
                            .where('F', Predicates.blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('E', Predicates.blocks(GCYMBlocks.HEAT_VENT.get()))
                            .where('L', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[]{
                            MATERIAL_ITEMS.get(dustTiny, Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/gcym/blast_alloy_smelter")
                    )
                    .additionalDisplay(EFDisplayHelper.EBFDisplay)
                    .shapeInfos(EFShapeInfosHelper::RobustAlloyMaterializer)
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.LargeCrackingMachine || GTCEu.isDataGen()) {
            LARGE_CRACKING_MACHINE = ExtendedFeaturesRegister
                    .multiblock("large_cracking_machine", CoilWorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.LCMTooltip)
                    .tooltipBuilder(EFTooltipHelper.ParallelHatch)
                    .rotationState(RotationState.ALL)
                    .recipeType(GTRecipeTypes.CRACKING_RECIPES)
                    .recipeModifiers(
                            GTRecipeModifiers.PARALLEL_HATCH,
                            GTRecipeModifiers.OC_NON_PERFECT,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers::crackerOverclock)
                    .appearanceBlock(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("FIIFIIF", "FIIFIIF", "FFFYFFF", "FIIFIIF", "FIIFIIF")
                            .aisle("FIIFIIF", "FKKFKKF", "FKKDKKF", "FKKFKKF", "FIIFIIF")
                            .aisle("FIIFIIF", "FKKDKKF", "D##D##D", "FKKDKKF", "FIIFIIF")
                            .aisle("FIIFIIF", "FKKFKKF", "FKKDKKF", "FKKFKKF", "FIIFIIF")
                            .aisle("FIIFIIF", "FIIFIIF", "FFF@FFF", "FIIFIIF", "FIIFIIF")
                            .where('@', controller(blocks(definition.get())))
                            .where('K', heatingCoils())
                            .where('#', air())
                            .where(' ', any())
                            .where('I', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('D', blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('F', blocks(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.get()).setMinGlobalLimited(40)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[]{
                            MATERIAL_ITEMS.get(dustTiny, Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            ExtendedFeaturesCore.id("block/multiblock/large_cracking_machine")
                    )
                    .additionalDisplay(EFDisplayHelper.CrackerDisplay)
                    .shapeInfos(EFShapeInfosHelper::LargeCrackingMachine)
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.SynthesisVessel || GTCEu.isDataGen()) {
            SYNTHESIS_VESSEL = ExtendedFeaturesRegister
                    .multiblock("synthesis_vessel", WorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.SVTooltip)
                    .rotationState(RotationState.ALL)
                    .recipeTypes(EFRecipeTypes.CHEMICAL_REDUCTION)
                    .recipeModifiers(
                            GTRecipeModifiers.OC_PERFECT,
                            GTRecipeModifiers.BATCH_MODE)
                    .appearanceBlock(GTBlocks.CASING_PTFE_INERT)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle(" FDDDF ", " FDDDF ", " FDDDF ")
                            .aisle("FNDDDNF", "FCK#KCF", "FNDDDNF")
                            .aisle("DDDDDDD", "DKK#KKD", "DDDDDDD")
                            .aisle("DDDDDDD", "D##K##D", "DDDDDDD")
                            .aisle("DDDDDDD", "DKK#KKD", "DDDDDDD")
                            .aisle("FNDDDNF", "FCK#KCF", "FNDDDNF")
                            .aisle(" FDDDF ", " FD@DF ", " FDDDF ")
                            .where('@', controller(blocks(definition.get())))
                            .where('#', air())
                            .where(' ', any())
                            .where('F', frames(GTMaterials.Polytetrafluoroethylene))
                            .where('K', blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                            .where('C', heatingCoils())
                            .where('D', blocks(GTBlocks.CASING_PTFE_INERT.get()).setMinGlobalLimited(65)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .where('N', blocks(GCYMBlocks.HEAT_VENT.get()))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                            GTCEu.id("block/multiblock/large_chemical_reactor"))
                    .shapeInfos(EFShapeInfosHelper::SynthesisVessel)
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.LargePyrolysisOven || GTCEu.isDataGen()) {
            LARGE_PYROLYSE_OVEN = ExtendedFeaturesRegister
                    .multiblock("large_pyrolysis_oven", CoilWorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.LPOTooltip)
                    .tooltipBuilder(EFTooltipHelper.ParallelHatch)
                    .rotationState(RotationState.ALL)
                    .recipeType(GTRecipeTypes.PYROLYSE_RECIPES)
                    .recipeModifiers(
                            GTRecipeModifiers.PARALLEL_HATCH,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers.OC_NON_PERFECT,
                            GTRecipeModifiers::pyrolyseOvenOverclock)
                    .appearanceBlock(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("  EEE  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                            .aisle(" FGGGF ", " GKKKG ", " GKKKG ", " GKKKG ", " FGGGF ")
                            .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                            .aisle("EGEEEGE", "HK#O#KH", "HK#O#KH", "HK#O#KH", "EGEYEGE")
                            .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                            .aisle(" FGGGF ", " GKKKG ", " GKKKG ", " GKKKG ", " FGGGF ")
                            .aisle("  E@E  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                            .where('@', controller(blocks(definition.get())))
                            .where('#', air())
                            .where(' ', any())
                            .where('K', heatingCoils())
                            .where('G', blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()))
                            .where('F', blocks(GCYMBlocks.HEAT_VENT.get()))
                            .where('O', blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('H', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('E', blocks(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.get()).setMinGlobalLimited(30)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[]{
                            MATERIAL_ITEMS.get(dustTiny, Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/machines/alloy_smelter")
                    )
                    .additionalDisplay(EFDisplayHelper.PyroDisplay)
                    .shapeInfos(EFShapeInfosHelper::LargePyrolysisOven)
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.ExpandedAssemblyLine || GTCEu.isDataGen()) {
            EXPANDED_ASSEMBLY_LINE = ExtendedFeaturesRegister
                    .multiblock("expanded_assembly_line", ExpandedAssemblyLineMachine::new)
                    .rotationState(RotationState.ALL)
                    .tooltips(EFTooltipHelper.EALTooltip)
                    .recipeType(GTRecipeTypes.ASSEMBLY_LINE_RECIPES)
                    .recipeModifiers(
                            GTRecipeModifiers.OC_NON_PERFECT_SUBTICK,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers.OC_NON_PERFECT)
                    .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
                    // RIGHT, UP and BACK are required to allow the terminal item to build the multiblock in the correct way
                    .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.BACK)
                            .aisle("EE@EE", "RLKLR", "HHEHH")
                            .aisle("EEDEE", "RLKLR", "HHEHH").setRepeatable(4, 16)
                            .aisle("EENEE", "RLKLR", "HHEHH")
                            .where('@', controller(blocks(definition.get())))
                            .where('E', blocks(GTBlocks.CASING_STEEL_SOLID.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(ExtendedAbilities.WIRELESS_OPTICAL_RECEPTOR).setExactLimit(1)))
                            .where('L', blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                            .where('K', blocks(GTBlocks.CASING_ASSEMBLY_LINE.get()))
                            .where('H', blocks(GTBlocks.CASING_GRATE.get()))
                            .where('R', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('D', Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .where('N', Predicates.abilities(PartAbility.EXPORT_ITEMS))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                            GTCEu.id("block/multiblock/assembly_line"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.RockProcessingPlant || GTCEu.isDataGen()) {
            ROCK_PROCESSING_PLANT = ExtendedFeaturesRegister
                    .multiblock("rock_processing_plant", WorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.RPPTooltip)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeTypes(EFRecipeTypes.ROCK_PROCESSING_RECIPES)
                    .recipeModifiers(
                            GTRecipeModifiers.PARALLEL_HATCH,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers.OC_NON_PERFECT)
                    .appearanceBlock(GCYMBlocks.CASING_SECURE_MACERATION)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("DDDDDDD", "DDDDDDD", "DDDDDDD", "DDDDDDD", "       ")
                            .aisle("DDDDDDD", "DJDJDJD", "DJDJDJD", "DJDJDJD", "       ")
                            .aisle("DDDDDDD", "DJDJDJD", "DJDJDJD", "DJDJDJD", "       ")
                            .aisle("DDDDDDD", "DJDJDJD", "DJDJDJD", "DJDJDJD", "       ")
                            .aisle("DDDDDDD", "DDDIDDD", "DDDDDDD", "DDDDDDD", "       ")
                            .aisle("       ", "   I   ", "       ", "       ", "       ")
                            .aisle("  CCC  ", "  CIC  ", "  CCC  ", "  CCC  ", "  CCC  ")
                            .aisle(" CCCCC ", " C#E#C ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle(" CCCCC ", " CEEEC ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle("CCCCCCC", "C##E##C", "CFFFFFC", "C#####C", "CGGGGGC")
                            .aisle("CCCCCCC", "CEEEEEC", "CFFFFFC", "C#####C", "CGGGGGC")
                            .aisle("CCCCCCC", "C##E##C", "CFFFFFC", "C#####C", "CGGGGGC")
                            .aisle(" CCCCC ", " CEEEC ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle(" CCCCC ", " C#E#C ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle("  CCC  ", "  C@C  ", "  CCC  ", "  CCC  ", "  CCC  ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('E', blocks(GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX.get()))
                            .where('I', blocks(GTBlocks.LD_ITEM_PIPE.get()))
                            .where('D', blocks(GCYMBlocks.CASING_NONCONDUCTING.get()))
                            .where('J', blocks(GCYMBlocks.ELECTROLYTIC_CELL.get()))
                            .where('F', blocks(GCYMBlocks.CRUSHING_WHEELS.get()))
                            .where('G', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('H', abilities(PartAbility.ROTOR_HOLDER))
                            .where('C', blocks(GCYMBlocks.CASING_SECURE_MACERATION.get()).setMinGlobalLimited(1101)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/gcym/secure_maceration_casing"),
                            GTCEu.id("block/multiblock/gcym/large_maceration_tower"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.IndustrialGreenhouse || GTCEu.isDataGen()) {
            INDUSTRIAL_GREENHOUSE = ExtendedFeaturesRegister
                    .multiblock("industrial_greenhouse", WorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.IGTooltip)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeTypes(
                            EFRecipeTypes.GREENHOUSE_CROPS,
                            EFRecipeTypes.GREENHOUSE_WOOD)
                    .recipeModifiers(
                            GTRecipeModifiers.OC_PERFECT,
                            GTRecipeModifiers.BATCH_MODE)
                    .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("    DDD    ", "    DDD    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "D###III###D", "D###III###D", "D####I####D", "D###HIH###D", "D##HHIHH##D", "D##HHIHH##D", "D##HHIHH##D", " D##HHH##D ", " D#######D ", "  DDDDDDD  ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle("    DDD    ", "    D@D    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('C', blocks(Blocks.GRASS_BLOCK))
                            .where('G', blocks(Blocks.ROOTED_DIRT))
                            .where('I', blocks(Blocks.OAK_WOOD))
                            .where('H', blocks(Blocks.OAK_LEAVES))
                            .where('B', blocks(GTBlocks.FIREBOX_STEEL.get()))
                            .where('E', blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
                            .where('F', frames(GTMaterials.Steel))
                            .where('D', blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(190)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                            GTCEu.id("block/multiblock/gcym/large_cutter"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.ArtificialConservatory || GTCEu.isDataGen()) {
            ARTIFICIAL_CONSERVATORY = ExtendedFeaturesRegister
                    .multiblock("advanced_conservatory", WorkableElectricMultiblockMachine::new)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .tooltips(EFTooltipHelper.ArCtTooltip)
                    .tooltipBuilder(EFTooltipHelper.ParallelHatch)
                    .recipeTypes(
                            EFRecipeTypes.GREENHOUSE_CROPS,
                            EFRecipeTypes.GREENHOUSE_WOOD)
                    .recipeModifiers(
                            GTRecipeModifiers.PARALLEL_HATCH,
                            GTRecipeModifiers.OC_NON_PERFECT,
                            GTRecipeModifiers.BATCH_MODE)
                    .appearanceBlock(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("    DDD    ", "    DDD    ", "    EDE    ", "    EDE    ", "    EKE    ", "    EKE    ", "    EKE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "D###III###D", "D###III###D", "K####I####K", "K###HIH###K", "K##HHIHH##K", "D##HHIHH##D", "D##HHIHH##D", " D##HHH##D ", " D#######D ", "  DDDDDDD  ")
                            .aisle("DDDDDDDDDDD", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle("    DDD    ", "    D@D    ", "    EDE    ", "    EDE    ", "    EKE    ", "    EKE    ", "    EKE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('C', blocks(Blocks.GRASS_BLOCK))
                            .where('G', blocks(Blocks.ROOTED_DIRT))
                            .where('I', blocks(Blocks.OAK_WOOD))
                            .where('H', blocks(Blocks.OAK_LEAVES))
                            .where('K', blocks(GTBlocks.CASING_EXTREME_ENGINE_INTAKE.get()))
                            .where('B', blocks(GTBlocks.FIREBOX_TUNGSTENSTEEL.get()))
                            .where('E', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('F', frames(GTMaterials.TungstenSteel))
                            .where('D', blocks(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.get()).setMinGlobalLimited(190)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/gcym/large_cutter"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.Disassembler || GTCEu.isDataGen()) {
            DISASSEMBLER = ExtendedFeaturesRegister
                    .multiblock("universal_disassembly_machine", WorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.UDMTooltip)
                    .rotationState(RotationState.ALL)
                    .recipeTypes(
                            EFRecipeTypes.DISASSEMBLER_MACHINES,
                            EFRecipeTypes.DISASSEMBER_COMPONENTS)
                    .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT)
                    .appearanceBlock(GCYMBlocks.CASING_LARGE_SCALE_ASSEMBLING)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("OOOOOOO", "OOOOOOO", "OOOOOOO")
                            .aisle("OOOOOOO", "OKOKOKO", "ODODODO")
                            .aisle("OOOOOOO", "OKOKOKO", "ODODODO")
                            .aisle("OOOOOOO", "OGOKOGO", "OOODOOO")
                            .aisle("  OOO  ", "  O@O  ", "  OOO  ")
                            .where('@', controller(blocks(definition.get())))
                            .where('#', air())
                            .where('D', blocks(GTBlocks.CASING_GRATE.get()))
                            .where('G', blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
                            .where('K', blocks(GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX.get()))
                            .where('O', blocks(GCYMBlocks.CASING_LARGE_SCALE_ASSEMBLING.get()).setMinGlobalLimited(50)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/gcym/large_scale_assembling_casing"),
                            GTCEu.id("block/multiblock/gcym/large_assembler"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.LargeGasCollector || GTCEu.isDataGen()) {
            LARGE_GAS_COLLECTOR = ExtendedFeaturesRegister
                    .multiblock("large_gas_collector", WorkableElectricMultiblockMachine::new)
                    .tooltips(EFTooltipHelper.LGCTooltip)
                    .tooltipBuilder(EFTooltipHelper.ParallelHatch)
                    .rotationState(RotationState.ALL)
                    .recipeType(EFRecipeTypes.AIR_COLLECTOR)
                    .recipeModifiers(
                            GTRecipeModifiers.PARALLEL_HATCH,
                            GTRecipeModifiers.BATCH_MODE,
                            GTRecipeModifiers.OC_NON_PERFECT)
                    .appearanceBlock(GCYMBlocks.CASING_CORROSION_PROOF)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("ARRRA", " RBR ", "ARRRA")
                            .aisle("AAAAA", "ADEDA", "AFXFA")
                            .aisle("AAAAA", "BDEDB", "AXXXA")
                            .aisle("AAAAA", "ADEDA", "AFXFA")
                            .aisle("AAAAA", " A@A ", "AAAAA")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('A', blocks(GCYMBlocks.CASING_CORROSION_PROOF.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('B', blocks(GCYMBlocks.MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                            .where('X', blocks(GTBlocks.CASING_GRATE.get()))
                            .where('E', blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('D', blocks(GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX.get()))
                            .where('F', blocks(GTBlocks.CASING_EXTREME_ENGINE_INTAKE.get()))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/gcym/corrosion_proof_casing"),
                            GTCEu.id("block/multiblock/gcym/large_brewer"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.MatrixDataRelay || GTCEu.isDataGen()) {
            MATRIX_DATA_RELAY = ExtendedFeaturesRegister
                    .multiblock("matrix_data_relay", MatrixDataRelayMachine::new)
                    .tooltips(EFTooltipHelper.MDRTooltip)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.DUMMY_RECIPES)
                    .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("    CCCCC    ", "    C   C    ", "    C   C    ", "    C   C    ", "    CCCCC    ")
                            .aisle("  DDDDDDDDD  ", "  EEC   CEE  ", "  EEC   CEE  ", "  EEC   CEE  ", "  DDDDDDDDD  ")
                            .aisle(" DDCFFFFFCDD ", " EDEF F FEDE ", " EDEF F FEDE ", " EDEF F FEDE ", " DDCFFFFFCDD ")
                            .aisle(" DCCCCFCCCCD ", " EE       EE ", " EE       EE ", " EE       EE ", " DCCCCFCCCCD ")
                            .aisle("CDFCCCFCCCFDC", "CCF       FCC", "CCX       XCC", "CCF       FCC", "CDFCCCFCCCFDC")
                            .aisle("CDFCCCFCCCFDC", "             ", "             ", "             ", "CDFCCCFCCCFDC")
                            .aisle("CDFFFFFFFFFDC", "  F   F   F  ", "  X   H   X  ", "  F   F   F  ", "CDFFFFFFFFFDC")
                            .aisle("CDFCCCFCCCFDC", "             ", "             ", "             ", "CDFCCCFCCCFDC")
                            .aisle("CDFCCCFCCCFDC", "CCF       FCC", "CCX       XCC", "CCF       FCC", "CDFCCCFCCCFDC")
                            .aisle(" DCCCCFCCCCD ", " EE       EE ", " EE       EE ", " EE       EE ", " DCCCCFCCCCD ")
                            .aisle(" DDCFFFFFCDD ", " EDEF F FEDE ", " EDEF @ FEDE ", " EDEF F FEDE ", " DDCFFFFFCDD ")
                            .aisle("  DDDDDDDDD  ", "  EEC   CEE  ", "  EEC   CEE  ", "  EEC   CEE  ", "  DDDDDDDDD  ")
                            .aisle("    CCCCC    ", "    C   C    ", "    C   C    ", "    C   C    ", "    CCCCC    ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('D', blocks(GTBlocks.ADVANCED_COMPUTER_CASING.get()))
                            .where('C', blocks(GTBlocks.COMPUTER_CASING.get()))
                            .where('E', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                            .where('F', blocks(GTBlocks.HIGH_POWER_CASING.get()).setMinGlobalLimited(80)
                                    .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(1))
                                    .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2)))
                            .where('H', abilities(ExtendedAbilities.WIRELESS_OPTICAL_TRANSMISSOR).setExactLimit(1))
                            .where('X', blocks(GTBlocks.HIGH_POWER_CASING.get())
                                    .or(abilities(PartAbility.DATA_ACCESS).setMaxGlobalLimited(6)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/hpca/high_power_casing"),
                            ExtendedFeaturesCore.id("block/multiblock/matrix_data_relay"))
                    .register();
        }
    }

    static {
        if (MultiblocksConfig.PowerTransformer || GTCEu.isDataGen()) {
            POWER_TRANSFORMER = ExtendedFeaturesRegister
                    .multiblock("power_transformer", ActiveTransformerMachine::new)
                    .rotationState(RotationState.ALL)
                    .tooltips(EFTooltipHelper.PwTfTooltip)
                    .recipeType(GTRecipeTypes.DUMMY_RECIPES)
                    .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
                    // RIGHT, UP and BACK are required to allow terminal to build the multiblock in the correct way
                    .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.BACK)
                            .aisle("EEE", "E@E", "EEE")
                            .aisle("EEE", "EFE", "EEE").setRepeatable(1, 4)
                            .aisle("EEE", "EEE", "EEE")
                            .where('@', controller(blocks(definition.get())))
                            .where('E', blocks(GTBlocks.HIGH_POWER_CASING.get()).setMinGlobalLimited(8)
                                    .or(ActiveTransformerMachine.getHatchPredicates()))
                            .where('F', blocks(GTBlocks.SUPERCONDUCTING_COIL.get()))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/hpca/high_power_casing"),
                            GTCEu.id("block/multiblock/hpca"))
                    .register();
        }
    }

    public static void init() {
        // 3.0.0 took long enough huh
        // Anyway, hello stranger
        // I hope you are good past the screen :)
        // Eternal hell for mods made in kotlin. EMBRACE JAVA!
        // Go try Mindustry :)
    }
}