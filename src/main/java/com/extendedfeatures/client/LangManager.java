package com.extendedfeatures.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class LangManager {

    public static void init(RegistrateLangProvider provider) {
        Common(provider);
        Tooltips(provider);
    }

    private static void Common(RegistrateLangProvider provider) {

        // Multiblocks
        replace(provider, "block.extendedfeatures.robust_alloy_materializer", "Robust Alloy Materializer [RAM]");
        replace(provider, "block.extendedfeatures.large_cracking_machine", "Large Cracking Machine [LCM]");
        replace(provider, "block.extendedfeatures.synthesis_vessel", "Synthesis Vessel [SyV]");
        replace(provider, "block.extendedfeatures.large_pyrolysis_oven", "Large Pyrolysis Oven [LPO]");
        replace(provider, "block.extendedfeatures.expanded_assembly_line", "Expanded Assembly Line [EAL]");
        replace(provider, "block.extendedfeatures.rock_processing_plant", "Rock Processing Plant [RPP]");
        replace(provider, "block.extendedfeatures.industrial_greenhouse", "Industrial Greenhouse [IG]");
        replace(provider, "block.extendedfeatures.articial_conservatory", "Artificial Conservatory [AC]");
        replace(provider, "block.extendedfeatures.universal_disassembly_machine", "Universal Disassembly Machine [UDA]");
        replace(provider, "block.extendedfeatures.rock_processing_plant", "Rock Processing Plant [RPP]");
        replace(provider, "block.extendedfeatures.large_gas_collector", "Large Gas Collector [LGC]");
        replace(provider, "block.extendedfeatures.matrix_data_relay", "Matrix Data Relay [MDR]");
        replace(provider, "block.extendedfeatures.power_transformer", "Power Transformer [PT]");

        // Expanded Data Hatches
        replace(provider, "block.extendedfeatures.zpm_data_access_hatch", "Elite Data Access Hatch");
        replace(provider, "block.extendedfeatures.uv_data_access_hatch", "Ultimate Data Access Hatch");
        replace(provider, "block.extendedfeatures.uhv_data_access_hatch", "Epic Data Access Hatch");

        // RecipeTypes lang keys
        replace(provider, "extendedfeatures.greenhouse_wood_recipes", "Greenhouse: Trees");
        replace(provider, "extendedfeatures.greenhouse_crop_recipes", "Greenhouse: Crops");
        replace(provider, "extendedfeatures.component_disassembly", "Component Disassembly");
        replace(provider, "extendedfeatures.machine_disassembly", "Machine Disassembly");
        replace(provider, "extendedfeatures.rock_processing_plant", "Rock Processing");
        replace(provider, "extendedfeatures.chemical_skips", "Chemical Reduction");
        replace(provider, "extendedfeatures.gas_collection", "Gas Collection");

        // Configuration lang
        replace(provider, "config.screen.extendedfeatures", "§7Mod Configuration §c(Restart to Apply Changes)");

        replace(provider, "config.extendedfeatures.option.MultiblocksConfig", "§7Multiblocks");
        replace(provider, "config.extendedfeatures.option.MachinesConfig", "§7Machines");
        replace(provider, "config.extendedfeatures.option.UniversalCircuits", "§7Universal Circuits");

        replace(provider, "config.extendedfeatures.option.RobustAlloyMaterializer", "§7Robust Alloy Materializer");
        replace(provider, "config.extendedfeatures.option.LargeCrackingMachine", "§7Large Cracking Machine");
        replace(provider, "config.extendedfeatures.option.SynthesisVessel", "§7Synthesis Vessel");
        replace(provider, "config.extendedfeatures.option.LargePyrolysisOven", "§7Large Pyrolysis Oven");
        replace(provider, "config.extendedfeatures.option.ExpandedAssemblyLine", "§7Expanded Assembly Line");
        replace(provider, "config.extendedfeatures.option.RockProcessingPlant", "§7Rock Processing Plant");
        replace(provider, "config.extendedfeatures.option.IndustrialGreenhouse", "§7Industrial Greenhouse");
        replace(provider, "config.extendedfeatures.option.ArtificialConservatory", "§7Artificial Conservatory");
        replace(provider, "config.extendedfeatures.option.Disassembler", "§7Universal Disassembly Machine");
        replace(provider, "config.extendedfeatures.option.LargeGasCollector", "§7Large Gas Collector");
        replace(provider, "config.extendedfeatures.option.MatrixDataRelay", "§7Matrix Data Relay");
        replace(provider, "config.extendedfeatures.option.PowerTransformer", "§7Power Transformer");
        replace(provider, "config.extendedfeatures.option.DataHatchLinkingBehavior", "§7Restrict Data Hatch Linking Behavior");

        replace(provider, "config.extendedfeatures.option.ExpandedDataAccessHatches", "§7Expanded Data Access Hatches");
        replace(provider, "config.extendedfeatures.option.WirelessOpticalHatches", "§7Wireless Optical Tranmisssors/Receptors");
        replace(provider, "config.extendedfeatures.option.CCMHatch", "§7Configurable Cleaning Maintenance Hatch");

        // Optical
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.range", "§fScan range:§f %s blocks §8(right-click with an empty hand)");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", "§7Max links allowed:§f %s");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.scan", "§7Links to nearby Wireless Receptors and Data Access Hatches across its range");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.receptor", "§7Gets linked automatically when scanned by a Wireless Transmissor of the same tier");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.linked_summary", "Linked %s new receptor(s) and %s new data hatch(es)");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.range_shown", "Displaying current range of connections: %s blocks");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.no_receptors_found", "§cNo new receptors or data hatches were found in range");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.not_formed", "§cThe hatch is not placed on a valid strucure!");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.scan_cooldown", "§cLink scan is on cooldown, try again shortly");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.scan_required", "§cRun a scan first before showing current links");

        // Wireless Hatch GUI
        replace(provider, "gui.extendedfeatures.wireless_hatch.title", "Wireless Optical Transmissor");
        replace(provider, "gui.extendedfeatures.wireless_hatch.show_range", "Show Max Distance:");
        replace(provider, "gui.extendedfeatures.wireless_hatch.show_links", "Show current links:");
        replace(provider, "gui.extendedfeatures.wireless_hatch.scan_link", "Link nearby Receptors & Data Hatches");
        replace(provider, "gui.extendedfeatures.wireless_hatch.state_on", "§aON");
        replace(provider, "gui.extendedfeatures.wireless_hatch.state_off", "§cOFF");
        replace(provider, "gui.extendedfeatures.wireless_hatch.cooldown", "§fAvailable in %ss");
        replace(provider, "gui.extendedfeatures.wireless_hatch.show_links_locked", "§7Run a scan first");

        // ========================
        //     Jade integration
        // ========================

        // Hatches
        replace(provider, "config.jade.plugin_extendedfeatures.wireless_optical_hatch", "Wireless Optical Info");
        replace(provider, "extendedfeatures.jade.wireless_optical_hatch.linked_data_hatches", "§fLinked Data Access Hatches: §6%s");
        replace(provider, "extendedfeatures.jade.wireless_optical_hatch.linked_receptors_header", "§fLinked Wireless Optical Receptors:");
        replace(provider, "extendedfeatures.jade.wireless_optical_hatch.receptors_entry", "    - Receptor %s: %s");
        replace(provider, "extendedfeatures.jade.wireless_optical_hatch.no_receptors", "§c    - No receptors found");

        // MDR
        replace(provider, "config.jade.plugin_extendedfeatures.matrix_data_relay", "Matrix Data Relay Info");
        replace(provider, "extendedfeatures.jade.matrix_data_relay.coolant_usage", "§fPCB Coolant Upkeep: §b%s mB/s");
        replace(provider, "extendedfeatures.jade.matrix_data_relay.coolant_supplied", "§a    - Currently supplying PCB Coolant");
        replace(provider, "extendedfeatures.jade.matrix_data_relay.coolant_starved", "§c    - Insufficient PCB Coolant");
        replace(provider, "extendedfeatures.jade.matrix_data_relay.wireless_hatch_tier", "§fWireless Hatch Tier: §b%s");

    }

    private static void Tooltips(RegistrateLangProvider provider) {

        // This basically prints the "-" 32 times in the same line, using color format 8 (dark gray)
        provider.add("extendedfeatures.separator_line", "§8" + "-".repeat(32));

        // Dynamic string, pass 2 arguments when making tooltips to display them in %s place
        provider.add("extendedfeatures.machine_modes", "§7Available Machine Modes: §f%s, §f%s");

        provider.add("extendedfeatures.limited_energy", "§fAllows §bone §fenergy hatch");

        provider.add("extendedfeatures.robust_alloy_materializer.tooltip.0", "§fHas §6Perfect Overclocks §fand §9Batching §fenabled");

        provider.add("extendedfeatures.large_pyrolysis_oven.tooltip.0", "§fAllows §3Parallel Hatches §fand Has and §9Batching §fenabled");

        provider.add("extendedfeatures.large_cracking_machine.tooltip.0", "§fAllows §3Parallel Hatches §fand Has and §9Batching §fenabled");

        provider.add("extendedfeatures.expanded_assembly_line.tooltip.0", "§7An Assembly Line that takes advantage of §dAE2 Stocking Hatches");
        provider.add("extendedfeatures.expanded_assembly_line.tooltip.1", "§fPerforms recipes without §cOrdered Inputs");
        provider.add("extendedfeatures.expanded_assembly_line.tooltip.2", "§fRequires a §aWireless Optical Receptor§f for Data Reception");
        provider.add("extendedfeatures.expanded_assembly_line.tooltip.4", "§fHas §4Subtick Parallels §fand §9Batching §fenabled");

        provider.add("extendedfeatures.synthesis_vessel.tooltip.0", "§7A §3Chemical Plant §7variant based on the Large Chemical Reactor");
        provider.add("extendedfeatures.synthesis_vessel.tooltip.1", "§7Performs entire chemical processing lines in 1 cycle");
        provider.add("extendedfeatures.synthesis_vessel.tooltip.2", "§fAllows §3Parallel Hatches§f, Has §6Perfect Overclocks §fand §9Batching §fenabled");

        provider.add("extendedfeatures.rock_processing_plant.tooltip.0", "§7All in One Processing Machine!");
        provider.add("extendedfeatures.rock_processing_plant.tooltip.1", "§7Turns the rocks you normally get from the rock breaker into more useful resources");
        provider.add("extendedfeatures.rock_processing_plant.tooltip.2", "§fAllows §3Parallel Hatches §fand has §9Batching §fenabled");

        provider.add("extendedfeatures.greenhouse.tooltip.0", "§7An easier way to obtain natural resources");
        provider.add("extendedfeatures.industrial_greenhouse.tooltip.0", "§fHas §6Perfect Overclocks §fand §9Batching §fenabled");
        provider.add("extendedfeatures.advanced_conservatory.tooltip.0", "§fAllows §3Parallel Hatches §fand has §9Batching §fenabled");

        provider.add("extendedfeatures.large_air_collector.tooltip.0", "§7A Bigger Gas Collector");
        provider.add("extendedfeatures.large_air_collector.tooltip.1", "§fAllows §3Parallel Hatches §fand has §9Batching §fenabled");

        provider.add("extendedfeatures.configurable_cleaning_maintenance_hatch", "§fFor configurable maintenance on multiblocks with Cleaning!");

        provider.add("extendedfeatures.universal_disassembly_machine.tooltip.0", "§7This machine can disassemble a §aMachine §7or §cComponent §7back into their base components");
        provider.add("extendedfeatures.universal_disassembly_machine.tooltip.1", "§7If is set to §fMachine Disassembly§7, every recipe requires the respective §benergy hatch §7of that tier");

        provider.add("extendedfeatures.matrix_data_relay.tooltip.1", "§7Your personal §fWireless Databank");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.2", "§7This structure allows a maximum of 6 Data Access Hatches");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.3", "§7Each tier of Wireless Transmissors has an energy usage of 1A from the next tier:");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.4", "   §dLuV §fWireless Transmissor: §c131.072 §fEU/t");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.5", "   §cZPM §fWireless Transmissor: §3524.288 §fEU/t");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.6", "   §3UV §fWireless Transmissor: §42.097.152 §fEU/t");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.7", "   §7Uses §f1920 EU/t §7per §fData Access Hatch");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.8", "   §7Uses §98192 EU/t §7per §fExpanded Data Access Hatch");
        provider.add("extendedfeatures.matrix_data_relay.tooltip.9", "§7In order to work, a constant supply of §fPCB Coolant §7is required");

        provider.add("extendedfeatures.power_transformer.tooltip.0", "§fAlternative Active Transformer");
        provider.add("extendedfeatures.power_transformer.tooltip.1", "§7Can be extended up to 4 rows");

    }
}
