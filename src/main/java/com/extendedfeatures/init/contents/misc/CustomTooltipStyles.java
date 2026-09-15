package com.extendedfeatures.init.contents.misc;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.utils.GradientUtil;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.function.UnaryOperator;

public class CustomTooltipStyles {

    private static final int WHITE = 0xFFFFFFFF;

    // Main Colors
    private static final int LV = 0xFFAAAAAA; // Gray
    private static final int MV = 0xFF55FFFF; // Aqua
    private static final int HV = 0xFFFFAA00; // Gold
    private static final int EV = 0xFFAA00AA; // Dark Purple
    private static final int IV = 0xFF5555FF; // Blue
    private static final int LuV = 0xFFFF55FF; // Light Purple
    private static final int ZPM = 0xFFFF5555; // Red
    private static final int UV = 0xFF00AAAA; // Dark Aqua
    private static final int UHV = 0xFFAA0000; // Dark Red
    private static final int UEV = 0xFF55FF55; // Dark Green
    private static final int UIV = 0xFF00AA00; // Green
    private static final int UXV = 0xFFFFFF55; // Yellow

    // Speed modifiers
    private static final double SPEED_SLOW = 0.1;
    private static final double SPEED_MEDIUM = 0.2;
    private static final double SPEED_FAST = 0.3;

    // Similar Tooltip Helper from GTCEu
    public static TextColor movingGradient(double speed, int colorA, int colorB) {
        float t = (float) (Math.sin(GTValues.CLIENT_TIME * speed) * 0.5 + 0.5);
        int blended = GradientUtil.blend(colorA, colorB, t);
        return TextColor.fromRgb(blended & 0xFFFFFF);
    }

    // Some of these may already exist inside GTCEu already

    // Slow
    public static final UnaryOperator<Style> LV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, LV, WHITE));

    public static final UnaryOperator<Style> MV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, MV, WHITE));

    public static final UnaryOperator<Style> HV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, HV, WHITE));

    public static final UnaryOperator<Style> EV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, EV, WHITE));

    public static final UnaryOperator<Style> IV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, IV, WHITE));

    public static final UnaryOperator<Style> LuV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, LuV, WHITE));

    public static final UnaryOperator<Style> ZPM_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, ZPM, WHITE));

    public static final UnaryOperator<Style> UV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, UV, WHITE));

    public static final UnaryOperator<Style> UHV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, UHV, WHITE));

    public static final UnaryOperator<Style> UEV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, UEV, WHITE));

    public static final UnaryOperator<Style> UIV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, UIV, WHITE));

    public static final UnaryOperator<Style> UXV_GRADIENT_SLOW = style -> style
            .withColor(movingGradient(SPEED_SLOW, UXV, WHITE));

    // Medium
    public static final UnaryOperator<Style> LV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, LV, WHITE));

    public static final UnaryOperator<Style> MV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, MV, WHITE));

    public static final UnaryOperator<Style> HV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, HV, WHITE));

    public static final UnaryOperator<Style> EV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, EV, WHITE));

    public static final UnaryOperator<Style> IV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, IV, WHITE));

    public static final UnaryOperator<Style> LuV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, LuV, WHITE));

    public static final UnaryOperator<Style> ZPM_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, ZPM, WHITE));

    public static final UnaryOperator<Style> UV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, UV, WHITE));

    public static final UnaryOperator<Style> UHV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, UHV, WHITE));

    public static final UnaryOperator<Style> UEV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, UEV, WHITE));

    public static final UnaryOperator<Style> UIV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, UIV, WHITE));

    public static final UnaryOperator<Style> UXV_GRADIENT_MED = style -> style
            .withColor(movingGradient(SPEED_MEDIUM, UXV, WHITE));

    // Fast
    public static final UnaryOperator<Style> LV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, LV, WHITE));

    public static final UnaryOperator<Style> MV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, MV, WHITE));

    public static final UnaryOperator<Style> HV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, HV, WHITE));

    public static final UnaryOperator<Style> EV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, EV, WHITE));

    public static final UnaryOperator<Style> IV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, IV, WHITE));

    public static final UnaryOperator<Style> LuV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, LuV, WHITE));

    public static final UnaryOperator<Style> ZPM_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, ZPM, WHITE));

    public static final UnaryOperator<Style> UV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, UV, WHITE));

    public static final UnaryOperator<Style> UHV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, UHV, WHITE));

    public static final UnaryOperator<Style> UEV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, UEV, WHITE));

    public static final UnaryOperator<Style> UIV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, UIV, WHITE));

    public static final UnaryOperator<Style> UXV_GRADIENT_FAST = style -> style
            .withColor(movingGradient(SPEED_FAST, UXV, WHITE));

}
