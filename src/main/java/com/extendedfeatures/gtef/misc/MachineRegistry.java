package com.extendedfeatures.gtef.misc;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;

public class MachineRegistry {

    // Shortened call to config
    public static final Config.MultiblocksToggles MultiblocksConfig = Config.INSTANCE.MultiblocksConfig;
    public static final Config.MachineToggles MachineConfig = Config.INSTANCE.MachinesConfig;

    // =========================
    //        Multiblocks
    // =========================

    public static MultiblockMachineDefinition
            ROBUST_ALLOY_MATERIALIZER,
            LARGE_CRACKING_MACHINE,
            SYNTHESIS_VESSEL,
            LARGE_PYROLYSE_OVEN,
            EXPANDED_ASSEMBLY_LINE,
            ROCK_PROCESSING_PLANT,
            INDUSTRIAL_GREENHOUSE,
            ARTIFICIAL_CONSERVATORY,
            DISASSEMBLER,
            LARGE_GAS_COLLECTOR,
            MATRIX_DATA_RELAY,
            POWER_TRANSFORMER,
            FISHER;

    // =====================================
    //        Singleblocks/Machines
    // =====================================

    // Configurable Cleaning Maintenance Hatch
    public static MachineDefinition CONFIGURABLE_CLEANING_MAINTENANCE_HATCH = null;

    // Expanded Data Access Hatches
    public static MachineDefinition ZPM_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UV_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UHV_DATA_ACCESS_HATCH = null;

    // Wireless Optical T/R Hatches
    public static MachineDefinition LUV_WIRELESS_TRANSMISSOR = null;
    public static MachineDefinition LUV_WIRELESS_RECEPTOR = null;
    public static MachineDefinition ZPM_WIRELESS_TRANSMISSOR = null;
    public static MachineDefinition ZPM_WIRELESS_RECEPTOR = null;
    public static MachineDefinition UV_WIRELESS_TRANSMISSOR = null;
    public static MachineDefinition UV_WIRELESS_RECEPTOR = null;

}
