package com.extendedfeatures.client.internal.gui.relay;

import com.extendedfeatures.client.internal.logic.multiblock.MatrixDataRelayMachine;
import com.extendedfeatures.client.internal.visual.PacketManager;
import com.extendedfeatures.client.internal.visual.network.RelayActionPacket;
import com.extendedfeatures.client.internal.visual.network.RelayActionPacket.Action;
import com.extendedfeatures.client.internal.visual.network.RelayRequestPacket;

import com.gregtechceu.gtceu.api.GTValues;

import lombok.Getter;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("all")
public class MatrixRelayScreen extends Screen {

    // Panel
    private static final int WIDTH = 416;
    private static final int HEIGHT = 208;

    // Left column (stats)
    private static final int LEFT_X = 18;
    private static final int LEFT_RIGHT_X = 204;
    private static final int ROWS_Y = 40;
    private static final int ROW_HEIGHT = 13;

    // Buttons: under the stats card, same width as it
    private static final int BUTTONS_X = LEFT_X - 8;
    private static final int BUTTONS_WIDTH = LEFT_RIGHT_X - LEFT_X + 16;
    private static final int BUTTONS_Y = 139;
    private static final int BUTTON_HEIGHT = 18;
    private static final int BUTTON_STEP = 21;

    // Right column (receptor list, full height)
    private static final int RIGHT_X = 215;
    private static final int RIGHT_WIDTH = 182;
    private static final int RECEPTOR_HEADER_Y = 41;
    private static final int RECEPTOR_LINE_Y = 52;
    private static final int LIST_TOP = 56;
    private static final int LIST_BOTTOM = 196;
    private static final int LIST_ROW = 11;

    // Palette
    private static final int COLOR_BG = 0xF00D1623;
    private static final int COLOR_BORDER = 0xFF22405E;
    private static final int COLOR_ACCENT = 0xFF2EC4C4;
    private static final int COLOR_TITLE = 0xFFE8F4FF;
    private static final int COLOR_LABEL = 0xFFB4C2D3;
    private static final int COLOR_VALUE = 0xFFFFFFFF;
    private static final int COLOR_GOOD = 0xFF55F08A;
    private static final int COLOR_WARN = 0xFFFFB347;
    private static final int COLOR_BAD = 0xFFFF5A5A;
    private static final int COLOR_TIER = 0xFF3FE0B0;
    private static final int COLOR_MUTED = 0xFF6F8299;
    private static final int COLOR_ROW_ALT = 0x30FFFFFF;
    private static final int COLOR_ACCENT_BRIGHT = 0xFF8CFFFF;
    private static final int COLOR_PANEL_TOP = 0xF0173556;
    private static final int COLOR_PANEL_BOTTOM = 0xF0060C16;

    private static final int POLL_INTERVAL = 10;

    private final MatrixDataRelayMachine machine;
    @Getter
    private final BlockPos relayPos;

    private RelaySnapshot state;
    private int pollTimer = 0;
    private int listScroll = 0;
    private boolean lastLinksLocked = false;
    private Boolean lastPowerTip = null;
    private boolean lastCooldown = false;

    private RelayButton rangeButton;
    private RelayButton linksButton;
    private RelayButton scanButton;
    private PowerButton powerButton;

    private int left;
    private int top;

    public MatrixRelayScreen(MatrixDataRelayMachine machine) {
        super(machine.getBlockState().getBlock().getName());
        this.machine = machine;
        this.relayPos = machine.getPos();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        left = (this.width - WIDTH) / 2;
        top = (this.height - HEIGHT) / 2;

        rangeButton = addRenderableWidget(new RelayButton(left + BUTTONS_X, top + BUTTONS_Y,
                BUTTONS_WIDTH, BUTTON_HEIGHT, () -> send(Action.TOGGLE_RANGE)));
        linksButton = addRenderableWidget(new RelayButton(left + BUTTONS_X, top + BUTTONS_Y + BUTTON_STEP,
                BUTTONS_WIDTH, BUTTON_HEIGHT, () -> send(Action.TOGGLE_LINKS)));
        scanButton = addRenderableWidget(new RelayButton(left + BUTTONS_X, top + BUTTONS_Y + BUTTON_STEP * 2,
                BUTTONS_WIDTH, BUTTON_HEIGHT, () -> send(Action.SCAN_AND_LINK)));
        powerButton = addRenderableWidget(new PowerButton(left + 382, top + 11, 16, () -> send(Action.TOGGLE_POWER)));

        refreshWidgets();
        requestState();
    }

    // Data

    public void applySnapshot(RelaySnapshot snapshot) {
        this.state = snapshot;
        clampScroll();
        refreshWidgets();
    }

    private void send(Action action) {
        PacketManager.CHANNEL.sendToServer(new RelayActionPacket(relayPos, action));
    }

    private void requestState() {
        PacketManager.CHANNEL.sendToServer(new RelayRequestPacket(relayPos));
    }

    private boolean isOnCooldown() {
        return state != null && this.minecraft != null && this.minecraft.level != null &&
                this.minecraft.level.getGameTime() < state.cooldownEndGameTime();
    }

    @Override
    public void tick() {
        if (machine.isInValid()) {
            onClose();
            return;
        }
        if (++pollTimer >= POLL_INTERVAL) {
            pollTimer = 0;
            requestState();
        }
        // Cooldown label counts down locally between syncs
        boolean cooldown = isOnCooldown();
        if (cooldown || cooldown != lastCooldown) refreshWidgets();
        lastCooldown = cooldown;
    }

    // Widgets

    private void refreshWidgets() {
        if (rangeButton == null) return;

        boolean ready = state != null;
        boolean formed = ready && state.status() != RelaySnapshot.Status.UNFORMED;
        boolean hasTx = ready && state.hasTransmissor();

        rangeButton.setMessage(toggleLabel("gui.extendedfeatures.matrix_relay.show_range", ready && state.showRange()));
        rangeButton.active = formed && hasTx;

        boolean canLinks = ready && (state.hasScannedOnce() || state.showLinks());
        linksButton.setMessage(toggleLabel("gui.extendedfeatures.matrix_relay.show_links", ready && state.showLinks()));
        linksButton.active = formed && hasTx && canLinks;
        boolean linksLocked = ready && !canLinks;
        if (linksLocked != lastLinksLocked) {
            lastLinksLocked = linksLocked;
            linksButton.setTooltip(linksLocked
                    ? Tooltip.create(Component.translatable("gui.extendedfeatures.matrix_relay.show_links_locked"))
                    : null);
        }

        if (isOnCooldown()) {
            float seconds = Math.max(0f, (state.cooldownEndGameTime() - this.minecraft.level.getGameTime()) / 20f);
            scanButton.setMessage(Component.translatable("gui.extendedfeatures.matrix_relay.cooldown",
                    String.format(Locale.ROOT, "%.1f", seconds)));
        } else {
            scanButton.setMessage(Component.translatable("gui.extendedfeatures.matrix_relay.scan_link"));
        }
        scanButton.active = formed && hasTx && !isOnCooldown();

        powerButton.on = ready && state.workingEnabled();
        powerButton.active = formed;
        // Tooltips are only rebuilt when their text changes, otherwise the hover delay restarts every tick
        if (lastPowerTip == null || lastPowerTip != powerButton.on) {
            lastPowerTip = powerButton.on;
            powerButton.setTooltip(Tooltip.create(Component.translatable(powerButton.on
                    ? "gui.extendedfeatures.matrix_relay.power_off"
                    : "gui.extendedfeatures.matrix_relay.power_on")));
        }
    }

    private Component toggleLabel(String key, boolean enabled) {
        return Component.translatable(key).append(" ").append(Component.translatable(enabled
                ? "gui.extendedfeatures.matrix_relay.state_on"
                : "gui.extendedfeatures.matrix_relay.state_off"));
    }

    // Rendering

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partial) {
        renderBackground(g);
        renderPanel(g);

        g.drawCenteredString(this.font, this.title, left + WIDTH / 2, top + 11, COLOR_TITLE);

        if (state == null) {
            g.drawCenteredString(this.font, Component.translatable("gui.extendedfeatures.matrix_relay.syncing"),
                    left + WIDTH / 2, top + HEIGHT / 2, COLOR_MUTED);
        } else {
            renderStats(g);
            renderReceptors(g, mouseY, mouseX);
        }

        super.render(g, mouseX, mouseY, partial);
    }

    // Background: gradient panel, faint grid, header band with a light sweeping the accent line,
    // pulsing glow, corner brackets and a card behind each column.
    private void renderPanel(GuiGraphics g) {
        int right = left + WIDTH;
        int bottom = top + HEIGHT;
        long time = Util.getMillis();
        float pulse = 0.5f + 0.5f * (float) Math.sin(time / 900.0);

        // Glow outside the border
        g.renderOutline(left - 1, top - 1, WIDTH + 2, HEIGHT + 2, ((0x30 + (int) (0x30 * pulse)) << 24) | 0x2EC4C4);
        g.renderOutline(left - 2, top - 2, WIDTH + 4, HEIGHT + 4, ((0x10 + (int) (0x18 * pulse)) << 24) | 0x2EC4C4);

        g.fillGradient(left, top, right, bottom, COLOR_PANEL_TOP, COLOR_PANEL_BOTTOM);

        // Grid
        for (int x = left + 8; x < right - 8; x += 16) g.fill(x, top + 31, x + 1, bottom - 6, 0x0AFFFFFF);
        for (int y = top + 31; y < bottom - 6; y += 16) g.fill(left + 8, y, right - 8, y + 1, 0x0AFFFFFF);

        // Header band + accent line with a moving light
        g.fillGradient(left + 1, top + 1, right - 1, top + 29, 0x682EC4C4, 0x102EC4C4);
        g.fill(left + 1, top + 29, right - 1, top + 30, 0xFF1B6F80);
        int segment = 90;
        int sweep = (int) ((time / 12) % (WIDTH + segment)) - segment;
        int x1 = Math.max(left + 1, left + sweep);
        int x2 = Math.min(right - 1, left + sweep + segment);
        if (x2 > x1) {
            g.fill(x1, top + 29, x2, top + 30, COLOR_ACCENT);
            int cx1 = Math.max(x1, left + sweep + segment / 3);
            int cx2 = Math.min(x2, left + sweep + segment * 2 / 3);
            if (cx2 > cx1) g.fill(cx1, top + 29, cx2, top + 30, COLOR_ACCENT_BRIGHT);
        }

        // Cards behind each column
        int statsBottom = top + ROWS_Y + 7 * ROW_HEIGHT + 2;
        g.fill(left + LEFT_X - 8, top + 36, left + LEFT_RIGHT_X + 8, statsBottom, 0x66000000);
        g.renderOutline(left + LEFT_X - 8, top + 36, LEFT_RIGHT_X - LEFT_X + 16, statsBottom - (top + 36),
                0x402EC4C4);
        g.fill(left + RIGHT_X - 3, top + RECEPTOR_HEADER_Y - 5, left + RIGHT_X + RIGHT_WIDTH + 3,
                top + LIST_BOTTOM + 3, 0x66000000);
        g.renderOutline(left + RIGHT_X - 3, top + RECEPTOR_HEADER_Y - 5, RIGHT_WIDTH + 6,
                LIST_BOTTOM + 3 - RECEPTOR_HEADER_Y + 5, 0x402EC4C4);

        // Border + corner brackets
        g.renderOutline(left, top, WIDTH, HEIGHT, 0xFF2A5F8A);
        int c = COLOR_ACCENT;
        g.fill(left, top, left + 10, top + 2, c);
        g.fill(left, top, left + 2, top + 10, c);
        g.fill(right - 10, top, right, top + 2, c);
        g.fill(right - 2, top, right, top + 10, c);
        g.fill(left, bottom - 2, left + 10, bottom, c);
        g.fill(left, bottom - 10, left + 2, bottom, c);
        g.fill(right - 10, bottom - 2, right, bottom, c);
        g.fill(right - 2, bottom - 10, right, bottom, c);
    }

    private void renderStats(GuiGraphics g) {
        int y = top + ROWS_Y;

        row(g, y, "status", statusText(), statusColor());
        y += ROW_HEIGHT;

        int problems = state.maintenanceProblems();
        row(g, y, "maintenance",
                problems == 0 ? Component.translatable("gui.extendedfeatures.matrix_relay.maintenance_fine")
                        : Component.translatable("gui.extendedfeatures.matrix_relay.maintenance_problems", problems),
                problems == 0 ? COLOR_GOOD : COLOR_WARN);
        y += ROW_HEIGHT;

        row(g, y, "consuming", Component.literal(String.format(Locale.ROOT, "%,d EU/t", state.consumingEUt())),
                COLOR_VALUE);
        y += ROW_HEIGHT;

        row(g, y, "coolant", Component.literal(state.coolantPerSecond() + " mB/s"),
                state.status() == RelaySnapshot.Status.NO_PCB_COOLANT ? COLOR_BAD : COLOR_VALUE);
        y += ROW_HEIGHT;

        row(g, y, "active_hatches", Component.literal(String.valueOf(state.activeDataHatches())), COLOR_VALUE);
        y += ROW_HEIGHT;

        row(g, y, "transmissor",
                state.hasTransmissor()
                        ? Component.literal(GTValues.VN[state.transmissorTier()].toUpperCase(Locale.ROOT))
                        : Component.translatable("gui.extendedfeatures.matrix_relay.no_transmissor"),
                state.hasTransmissor() ? COLOR_TIER : COLOR_BAD);
        y += ROW_HEIGHT;

        row(g, y, "linked_hatches", Component.literal(String.valueOf(state.linkedDataHatches())), COLOR_VALUE);

    }

    private void row(GuiGraphics g, int y, String key, Component value, int valueColor) {
        if (((y - (top + ROWS_Y)) / ROW_HEIGHT) % 2 == 0) {
            g.fill(left + LEFT_X - 6, y - 2, left + LEFT_RIGHT_X + 6, y + ROW_HEIGHT - 2, 0x14FFFFFF);
        }
        String label = Component.translatable("gui.extendedfeatures.matrix_relay." + key).getString()
                .toUpperCase(Locale.ROOT);
        g.drawString(this.font, label, left + LEFT_X, y, COLOR_LABEL, false);
        String text = value.getString().toUpperCase(Locale.ROOT);
        g.drawString(this.font, text, left + LEFT_RIGHT_X - this.font.width(text), y, valueColor, false);
    }

    private Component statusText() {
        return Component.translatable("gui.extendedfeatures.matrix_relay.status." +
                state.status().name().toLowerCase(Locale.ROOT));
    }

    private int statusColor() {
        return switch (state.status()) {
            case ACTIVE -> COLOR_GOOD;
            case IDLE -> COLOR_VALUE;
            case DISABLED -> COLOR_MUTED;
            case NO_POWER -> COLOR_WARN;
            case NO_PCB_COOLANT, UNFORMED -> COLOR_BAD;
        };
    }

    private void renderReceptors(GuiGraphics g, int mouseY, int mouseX) {
        int x = left + RIGHT_X;

        g.drawString(this.font, Component.translatable("gui.extendedfeatures.matrix_relay.linked_receptors")
                .getString().toUpperCase(Locale.ROOT), x, top + RECEPTOR_HEADER_Y, COLOR_LABEL, false);
        String count = state.receptors().size() + "/" + state.maxConnections();
        g.drawString(this.font, count, x + RIGHT_WIDTH - this.font.width(count), top + RECEPTOR_HEADER_Y,
                COLOR_VALUE, false);
        g.fill(x, top + RECEPTOR_LINE_Y, x + RIGHT_WIDTH, top + RECEPTOR_LINE_Y + 1, COLOR_ACCENT);

        if (state.receptors().isEmpty()) {
            g.drawCenteredString(this.font,
                    Component.translatable("gui.extendedfeatures.matrix_relay.no_receptors").getString()
                            .toUpperCase(Locale.ROOT),
                    x + RIGHT_WIDTH / 2, top + (LIST_TOP + LIST_BOTTOM) / 2 - 4, COLOR_MUTED);
            return;
        }

        g.enableScissor(x, top + LIST_TOP, x + RIGHT_WIDTH, top + LIST_BOTTOM);
        int visible = visibleRows();
        for (int i = 0; i < visible + 1; i++) {
            int index = listScroll + i;
            if (index >= state.receptors().size()) break;

            BlockPos pos = state.receptors().get(index);
            int rowY = top + LIST_TOP + i * LIST_ROW;
            if (index % 2 == 0) g.fill(x, rowY, x + RIGHT_WIDTH, rowY + LIST_ROW, COLOR_ROW_ALT);

            g.drawString(this.font, String.format(Locale.ROOT, "#%02d", index + 1), x + 3, rowY + 2, COLOR_MUTED,
                    false);
            g.drawString(this.font, pos.getX() + "  " + pos.getY() + "  " + pos.getZ(), x + 26, rowY + 2,
                    COLOR_TIER, false);
        }
        g.disableScissor();

        // Scroll bar, only when the list overflows
        int total = state.receptors().size();
        if (total > visible) {
            int trackH = LIST_BOTTOM - LIST_TOP;
            int barH = Math.max(8, trackH * visible / total);
            int barY = top + LIST_TOP + (trackH - barH) * listScroll / (total - visible);
            g.fill(x + RIGHT_WIDTH - 2, barY, x + RIGHT_WIDTH, barY + barH, COLOR_ACCENT);
        }
    }

    private int visibleRows() {
        return (LIST_BOTTOM - LIST_TOP) / LIST_ROW;
    }

    private void clampScroll() {
        int max = state == null ? 0 : Math.max(0, state.receptors().size() - visibleRows());
        listScroll = Math.max(0, Math.min(listScroll, max));
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (state != null && mouseX >= left + RIGHT_X && mouseX <= left + RIGHT_X + RIGHT_WIDTH &&
                mouseY >= top + LIST_TOP && mouseY <= top + LIST_BOTTOM) {
            listScroll -= (int) Math.signum(delta);
            clampScroll();
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    private static class RelayButton extends AbstractWidget {

        private final Runnable onPress;

        RelayButton(int x, int y, int width, int height, Runnable onPress) {
            super(x, y, width, height, Component.empty());
            this.onPress = onPress;
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
            onPress.run();
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partial) {
            boolean hover = active && isHoveredOrFocused();

            int fillTop = !active ? 0xFF141C27 : hover ? 0xFF2D5578 : 0xFF1B3550;
            int fillBottom = !active ? 0xFF0F151E : hover ? 0xFF1B3D5C : 0xFF11243A;
            g.fillGradient(getX(), getY(), getX() + width, getY() + height, fillTop, fillBottom);
            g.renderOutline(getX(), getY(), width, height, !active ? 0xFF222E3C : hover ? COLOR_ACCENT : 0xFF2F5A80);
            g.fill(getX(), getY(), getX() + 2, getY() + height, !active ? 0xFF2A3644 : COLOR_ACCENT);
            if (hover) g.fill(getX() + 2, getY() + height - 1, getX() + width - 1, getY() + height, COLOR_ACCENT);

            Font font = Minecraft.getInstance().font;
            // Disabled buttons drop the ON/OFF colours so they read as inactive
            Component text = active ? getMessage()
                    : Component.literal(ChatFormatting.stripFormatting(getMessage().getString()));
            g.drawCenteredString(font, text, getX() + width / 2 + 1, getY() + (height - 8) / 2,
                    active ? 0xFFFFFFFF : 0xFF5F7084);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput output) {
            defaultButtonNarrationText(output);
        }
    }

    // Power button
    private static class PowerButton extends AbstractWidget {

        private static final int ON_COLOR = 0xFF3B82D6;
        private static final int OFF_COLOR = 0xFF39414C;

        private final Runnable onPress;
        private boolean on;

        PowerButton(int x, int y, int size, Runnable onPress) {
            super(x, y, size, size, Component.empty());
            this.onPress = onPress;
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
            onPress.run();
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partial) {
            int bg = !active ? 0xFF22262C : on ? ON_COLOR : OFF_COLOR;
            if (active && isHoveredOrFocused()) bg = on ? 0xFF5599EA : 0xFF4D5766;

            g.fill(getX(), getY(), getX() + width, getY() + height, 0xFF0A121C);
            g.fill(getX() + 1, getY() + 1, getX() + width - 1, getY() + height - 1, bg);

            if (active && on) g.renderOutline(getX() - 1, getY() - 1, width + 2, height + 2, 0xA02EC4C4);

            // Power icon: ring with a gap on top and a bar through the gap
            int cx = getX() + width / 2;
            int cy = getY() + height / 2 + 1;
            int icon = active ? 0xFFFFFFFF : 0xFF77808C;
            for (int dx = -5; dx <= 5; dx++) {
                for (int dy = -5; dy <= 5; dy++) {
                    double d = Math.sqrt(dx * dx + dy * dy);
                    boolean ring = d >= 3.2 && d <= 4.6;
                    boolean gap = dy < 0 && Math.abs(dx) <= 1;
                    if (ring && !gap) g.fill(cx + dx, cy + dy, cx + dx + 1, cy + dy + 1, icon);
                }
            }
            g.fill(cx, cy - 6, cx + 1, cy, icon);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput output) {
            defaultButtonNarrationText(output);
        }
    }
}