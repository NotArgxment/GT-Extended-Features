package com.extendedfeatures.gtef.misc;

import net.minecraft.util.valueproviders.UniformInt;

public class FormatUtil {

    // Hours to ticks
    public static int hours(int hours, int minutes) {
        return (hours * 72000) + (minutes * 1200);
    }

    // Minutes to ticks
    public static int minutes(int minutes, int seconds) {
        return (minutes * 1200) + (seconds * 20);
    }

    public static int minutes(int minutes) {
        return (minutes * 1200);
    }

    // Seconds to ticks
    public static int seconds(int seconds) {
        return seconds * 20;
    }

    // Better way to call ranged values for GTCEu ranged outputs
    public static UniformInt ranged(int min, int max) {
        return UniformInt.of(min, max);
    }

}
