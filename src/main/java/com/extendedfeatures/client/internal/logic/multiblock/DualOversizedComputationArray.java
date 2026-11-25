package com.extendedfeatures.client.internal.logic.multiblock;

import com.gregtechceu.gtceu.api.capability.IHPCAComponentHatch;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.util.TimedProgressSupplier;
import com.gregtechceu.gtceu.api.gui.widget.ExtendedProgressWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.research.HPCAMachine;

import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

// Makes sure it displays the right multipart overlay inside the controller
public class DualOversizedComputationArray extends HPCAMachine {

    private static final int CENTER_X = 98;
    private static final int TOP_Y = 59;
    private static final int MAX_WIDTH = 170;
    private static final int MAX_HEIGHT = 56;
    private static final int PANEL_GAP = 6;
    private static final int MAX_PITCH = 15;
    private static final int MIN_PITCH = 5;
    private static final int OUTLINE_COLOR = 0xFF1C005F;
    private static final int FILL_COLOR = 0xFF26FFDF;
    private static final int RESYNC_INTERVAL = 100;

    private static final Field HANDLER_FIELD;
    private Thread tickingThread;
    private Boolean lastActive;

    static {
        Field fld = null;
        try {
            fld = HPCAMachine.class.getDeclaredField("hpcaHandler");
            fld.setAccessible(true);
        } catch (ReflectiveOperationException | RuntimeException ignored) {}
        HANDLER_FIELD = fld;
    }

    private final TimedProgressSupplier docaProgress = new TimedProgressSupplier(200, 47, false);

    /**
     * @param side  horizontal offset from the controller (one GUI panel per value)
     * @param depth offset along the front axis (negative = behind the controller)
     * @param row   vertical offset (positive = above the controller)
     */
    private record Cell(int side, int depth, int row, IHPCAComponentHatch hatch) {}

    public DualOversizedComputationArray(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public List<IMultiPart> getParts() {
        return Thread.currentThread() == tickingThread ? List.of() : super.getParts();
    }

    @Override
    public void tick() {
        tickingThread = Thread.currentThread();
        try {
            super.tick();
        } finally {
            tickingThread = null;
        }
        boolean active = getEnergyContainer().getEnergyStored() > 0;
        if (lastActive == null || lastActive != active || getOffsetTimer() % RESYNC_INTERVAL == 0) {
            lastActive = active;
            pushActive(active);
        }
    }

    private void pushActive(boolean active) {
        for (IMultiPart part : getParts()) {
            if (!(part instanceof IHPCAComponentHatch hatch)) continue;
            var state = part.self().getRenderState();
            if (state.hasProperty(GTMachineModelProperties.IS_ACTIVE) &&
                    state.getValue(GTMachineModelProperties.IS_ACTIVE) == active) {
                continue;
            }
            hatch.setActive(active);
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        lastActive = null;
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = (WidgetGroup) super.createUIWidget();
        for (Widget w : new ArrayList<>(group.widgets)) {
            if (w instanceof ExtendedProgressWidget || w instanceof ImageWidget) {
                group.removeWidget(w);
            }
        }
        List<Cell> cells = gatherCells();
        TreeSet<Integer> sides = new TreeSet<>();
        int minDepth = Integer.MAX_VALUE, maxDepth = Integer.MIN_VALUE;
        int minRow = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE;
        for (Cell c : cells) {
            sides.add(c.side());
            minDepth = Math.min(minDepth, c.depth());
            maxDepth = Math.max(maxDepth, c.depth());
            minRow = Math.min(minRow, c.row());
            maxRow = Math.max(maxRow, c.row());
        }
        int sideCount = Math.max(1, sides.size());
        int cols = cells.isEmpty() ? 3 : maxDepth - minDepth + 1;
        int rows = cells.isEmpty() ? 3 : maxRow - minRow + 1;

        int pitch = MAX_PITCH;
        while (pitch > MIN_PITCH && (sideCount * cols * pitch + (sideCount - 1) * PANEL_GAP > MAX_WIDTH || rows * pitch > MAX_HEIGHT)) {
            pitch--;
        }
        int icon = pitch - 2;
        int panelWidth = cols * pitch;
        int totalWidth = sideCount * panelWidth + (sideCount - 1) * PANEL_GAP;
        int startX = CENTER_X - totalWidth / 2;

        HPCAMachine.HPCAGridHandler handler = getHandler();
        for (int panel = 0; panel < sideCount; panel++) {
            boolean mirrored = panel % 2 == 1;
            GridOutline outline = new GridOutline(
                    () -> handler != null && handler.getAllocatedCWUt() > 0 ? docaProgress.getAsDouble() : 0,
                    startX + panel * (panelWidth + PANEL_GAP) - 2, TOP_Y - 2,
                    panelWidth + 2, rows * pitch + 2, cols, rows, pitch, mirrored);
            if (handler != null) outline.setServerTooltipSupplier(handler::addInfo);
            group.addWidget(outline);
        }

        List<Integer> sideList = new ArrayList<>(sides);
        for (Cell c : cells) {
            int panel = sideList.indexOf(c.side());
            int col = c.depth() - minDepth;
            if (panel % 2 == 1) col = cols - 1 - col;
            int row = maxRow - c.row();
            IHPCAComponentHatch hatch = c.hatch();
            Supplier<IGuiTexture> texture = hatch::getComponentIcon;
            group.addWidget(new ImageWidget(
                    startX + panel * (panelWidth + PANEL_GAP) + col * pitch,
                    TOP_Y + row * pitch, icon, icon, texture));
        }
        return group;
    }

    private HPCAMachine.HPCAGridHandler getHandler() {
        if (HANDLER_FIELD == null) return null;
        try {
            return (HPCAMachine.HPCAGridHandler) HANDLER_FIELD.get(this);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static class GridOutline extends ExtendedProgressWidget {

        private final DoubleSupplier progress;
        private final int cols, rows, pitch;
        private final boolean mirrored;

        GridOutline(DoubleSupplier progress, int x, int y, int w, int h, int cols, int rows, int pitch,
                    boolean mirrored) {
            super(progress, x, y, w, h, GuiTextures.HPCA_COMPONENT_OUTLINE);
            this.progress = progress;
            this.cols = cols;
            this.rows = rows;
            this.pitch = pitch;
            this.mirrored = mirrored;
        }

        @Override
        public void drawInBackground(@NotNull GuiGraphics g, int mouseX, int mouseY, float partialTicks) {
            int x = getPosition().x, y = getPosition().y;
            int w = getSize().width, h = getSize().height;
            drawFrames(g, x, y, w, h, OUTLINE_COLOR);

            double p = Math.max(0, Math.min(1, progress.getAsDouble()));
            if (p > 0) {
                int fillW = (int) Math.ceil(w * p);
                int x0 = mirrored ? x + w - fillW : x;
                g.enableScissor(x0, y, x0 + fillW, y + h);
                drawFrames(g, x, y, w, h, FILL_COLOR);
                g.disableScissor();
            }
        }

        private void drawFrames(GuiGraphics g, int x, int y, int w, int h, int color) {
            g.renderOutline(x, y, w, h, color);
            int icon = pitch - 2;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    g.renderOutline(x + 2 + c * pitch, y + 2 + r * pitch, icon, icon, color);
                }
            }
        }
    }

    private List<Cell> gatherCells() {
        List<Cell> cells = new ArrayList<>();
        if (!isFormed()) return cells;
        Direction front = getFrontFacing();
        Direction up = RelativeDirection.UP.getRelative(front, getUpwardsFacing(), isFlipped);
        Direction left = RelativeDirection.LEFT.getRelative(front, getUpwardsFacing(), isFlipped);
        BlockPos origin = getPos();
        for (IMultiPart part : getParts()) {
            if (part instanceof IHPCAComponentHatch hatch) {
                BlockPos d = part.self().getPos().subtract(origin);
                cells.add(new Cell(dot(left, d), dot(front, d), dot(up, d), hatch));
            }
        }
        return cells;
    }

    private static int dot(Direction dir, BlockPos d) {
        return dir.getStepX() * d.getX() + dir.getStepY() * d.getY() + dir.getStepZ() * d.getZ();
    }
}