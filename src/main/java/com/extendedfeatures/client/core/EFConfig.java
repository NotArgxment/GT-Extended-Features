package com.extendedfeatures.client.core;

import com.extendedfeatures.ExtendedFeaturesCore;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = ExtendedFeaturesCore.MOD_ID)
public class EFConfig {

    public static EFConfig INSTANCE;
    public static ConfigHolder<EFConfig> CONFIG_HOLDER;

    public static void init() {
        CONFIG_HOLDER = Configuration.registerConfig(EFConfig.class, ConfigFormats.yaml());
        INSTANCE = CONFIG_HOLDER.getConfigInstance();
    }

    @Configurable
    @Configurable.Comment("Configuration Toggles for MultiblocksConfig")
    public MultiblocksToggles MultiblocksConfig = new MultiblocksToggles();

    @Configurable
    @Configurable.Comment("Configuration Toggles for Normal Machines")
    public MachineToggles MachinesConfig = new MachineToggles();

    public static class MultiblocksToggles {

        @Configurable
        @Configurable.Comment({
                "Whether the Robust Alloy Materializer is Enabled",
                "Default = True"
        })
        public boolean RobustAlloyMaterializer = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Cracking Machine is Enabled",
                "Default = True"
        })
        public boolean LargeCrackingMachine = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Synthesis Vessel is Enabled",
                "Default = True"
        })
        public boolean SynthesisVessel = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Pyrolysis Oven is Enabled",
                "Default = True"
        })
        public boolean LargePyrolysisOven = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Expanded Assembly Line is Enabled",
                "Default = True"
        })
        public boolean ExpandedAssemblyLine = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Rock Processing Plant is Enabled",
                "§8Tip: Controller recipe should have a large macerator, large centrifuge and large electrolyzer",
                "Default = True"
        })
        public boolean RockProcessingPlant = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Industrial Greenhouse is Enabled",
                "Default = True"
        })
        public boolean IndustrialGreenhouse = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Artifical Conservatory (IV Greenhouse) is Enabled",
                "Default = True"
        })
        public boolean ArtificialConservatory = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Disassembler is enabled",
                "§cUniversal Circuits MUST be enabled",
                "Default = True"
        })
        public boolean Disassembler = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Air Collector is Enabled",
                "Default = True"
        })
        public boolean LargeGasCollector = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Matrix Data Relay is Enabled",
                "§cRequires Wireless Optical Hatches enabled",
                "Default = True"
        })
        public boolean MatrixDataRelay = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Energy Distribution Center is Enabled",
                "Default = True"
        })
        public boolean PowerTransformer = true;

    }

    public static class MachineToggles {

        @Configurable
        @Configurable.Comment({
                "Whether the Expanded Data Access Hatches are Enabled",
                "Default = True"
        })
        public boolean ExpandedDataAccessHatches = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Wireless Optical Hatches are Enabled",
                "Default = True"
        })
        public boolean WirelessOpticalHatches = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Configurable Cleaning Maintenance Hatch is Enabled",
                "Default = True"
        })
        public boolean CCMHatch = true;

    }

    @Configurable
    @Configurable.Comment({ "Whether the Universal Circuits are Enabled" })
    public boolean UniversalCircuits = true;

    @Configurable
    @Configurable.Comment({
            "Whether Wireless Optical Hatches must link only to Data Access Hatches that are part of a Matrix Data Relay multiblock",
            "If false, the linking behavior is reverted to connect to all and any Data Access Hatch in range (e.g. Data Banks & Variants)",
            "Default = True"
    })
    public boolean DataHatchLinkingBehavior = true;

}
