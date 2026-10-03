package com.extendedfeatures.client;

import com.extendedfeatures.client.core.EFConfig;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;

public class EFMachineRegistry {

    // Shortened call to config
    public static final EFConfig.MultiblocksToggles MultiblocksConfig = EFConfig.INSTANCE.MultiblocksConfig;
    public static final EFConfig.MachineToggles MachineConfig = EFConfig.INSTANCE.MachinesConfig;

    // =========================
    //        Multiblocks
    // =========================
    
    public static MultiblockMachineDefinition ROBUST_ALLOY_MATERIALIZER = null;
    public static MultiblockMachineDefinition LARGE_CRACKING_MACHINE = null;
    public static MultiblockMachineDefinition SYNTHESIS_VESSEL = null;
    public static MultiblockMachineDefinition LARGE_PYROLYSE_OVEN = null;
    public static MultiblockMachineDefinition EXPANDED_ASSEMBLY_LINE = null;
    public static MultiblockMachineDefinition ROCK_PROCESSING_PLANT = null;
    public static MultiblockMachineDefinition INDUSTRIAL_GREENHOUSE = null;
    public static MultiblockMachineDefinition ARTIFICIAL_CONSERVATORY = null;
    public static MultiblockMachineDefinition DISASSEMBLER = null;
    public static MultiblockMachineDefinition LARGE_GAS_COLLECTOR = null;
    public static MultiblockMachineDefinition MATRIX_DATA_RELAY = null;
    public static MultiblockMachineDefinition POWER_TRANSFORMER = null;

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
