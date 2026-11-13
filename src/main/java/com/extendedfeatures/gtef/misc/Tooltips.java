package com.extendedfeatures.gtef.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;

import static com.gregtechceu.gtceu.client.util.TooltipHelper.RAINBOW_HSL_SLOW;

public class Tooltips {

    // Regular Tooltips
    public static final List<Component> RobustAlloyMaterializer = List.of(
            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.robust_alloy_materializer.tooltip.0")
    );

    public static final List<Component> LargeCrackingMachine = List.of(
            Component.translatable("gtceu.machine.cracker.tooltip"),
            Component.translatable("gtceu.machine.cracker.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.large_cracking_machine.tooltip.0")
    );

    public static final List<Component> SynthesisVessel = List.of(
            Component.translatable("extendedfeatures.synthesis_vessel.tooltip.0"),
            Component.translatable("extendedfeatures.synthesis_vessel.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.synthesis_vessel.tooltip.2")
    );

    public static final List<Component> LargePyrolysisOven = List.of(
            Component.translatable("gtceu.machine.pyrolyse_oven.tooltip"),
            Component.translatable("gtceu.machine.pyrolyse_oven.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.large_pyrolysis_oven.tooltip.0")
    );

    public static final List<Component> ExpandedAssemblyLine = List.of(
            Component.translatable("extendedfeatures.expanded_assembly_line.tooltip.0"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.expanded_assembly_line.tooltip.1"),
            Component.translatable("extendedfeatures.expanded_assembly_line.tooltip.2"),
            Component.translatable("extendedfeatures.limited_energy"),
            Component.translatable("extendedfeatures.expanded_assembly_line.tooltip.4")
    );

    public static final List<Component> RockProcessingPlant = List.of(
            Component.translatable("extendedfeatures.rock_processing_plant.tooltip.0"),
            Component.translatable("extendedfeatures.rock_processing_plant.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.rock_processing_plant.tooltip.2")
    );

    public static final List<Component> IndustrialGreenhouse = List.of(
            Component.translatable("extendedfeatures.machine_modes", "Trees", "Crops"),
            Component.translatable("extendedfeatures.greenhouse.tooltip.0"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.industrial_greenhouse.tooltip.0")
    );

    public static final List<Component> ArtificialConservatory = List.of(
            Component.translatable("extendedfeatures.machine_modes", "Trees", "Crops"),
            Component.translatable("extendedfeatures.greenhouse.tooltip.0"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.advanced_conservatory.tooltip.0")
    );

    public static final List<Component> UniversalDisassemblyMachine = List.of(
            Component.translatable("extendedfeatures.machine_modes", "Machine Disassembly", "Component Disassembly"),
            Component.translatable("extendedfeatures.separator_line", "-".repeat(10)),
            Component.translatable("extendedfeatures.universal_disassembly_machine.tooltip.0"),
            Component.translatable("extendedfeatures.universal_disassembly_machine.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.limited_energy")
    );

    public static final List<Component> LargeGasCollector = List.of(
            Component.translatable("extendedfeatures.large_air_collector.tooltip.0"),
            Component.translatable("extendedfeatures.large_air_collector.tooltip.1")
    );

    public static final List<Component> MatrixDataRelay = List.of(
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.1"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.2"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.3"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.4"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.5"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.6"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.7"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.8"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("extendedfeatures.matrix_data_relay.tooltip.9")
    );

    public static final List<Component> PowerTooltip = List.of(
            Component.translatable("extendedfeatures.power_transformer.tooltip.0"),
            Component.translatable("extendedfeatures.power_transformer.tooltip.1"),
            Component.translatable("extendedfeatures.separator_line"),
            Component.translatable("gtceu.machine.active_transformer.tooltip.1")
    );

    public static final List<Component> LargeFisher = List.of(
            Component.translatable("extendedfeatures.large_fisher.tooltip.0"),
            Component.translatable("extendedfeatures.large_fisher.tooltip.1"),
            Component.translatable("extendedfeatures.large_fisher.tooltip.2")
    );

    public static final BiConsumer<ItemStack, List<Component>> ActiveTransformer =
            (stack, list) -> list.add(
                    Component.translatable("gtceu.machine.active_transformer.tooltip.2")
                            .append(Component.translatable("gtceu.machine.active_transformer.tooltip.3")
                                    .withStyle(RAINBOW_HSL_SLOW))
    );

}