package com.extendedfeatures.client.internal.logic.multiblock;

import com.gregtechceu.gtceu.api.capability.IHPCAComponentHatch;
import com.gregtechceu.gtceu.api.gui.widget.ExtendedProgressWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;

import com.gregtechceu.gtceu.common.machine.multiblock.electric.research.HPCAMachine;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;

public class DOCAMachine extends HPCAMachine {

    private static final int CENTER_X = 100;
    private static final int TOP_Y = 60;
    private static final int MAX_WIDTH = 170;
    private static final int MAX_HEIGHT = 55;
    private static final int PANEL_GAP = 5;
    private static final int MAX_PITCH = 15;
    private static final int MIN_PITCH = 5;

    private record Cell(int side, int depth, int row, IHPCAComponentHatch hatch) {
        // wuh
    }

    public DOCAMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public @NotNull Widget createUIWidget() {
        WidgetGroup group = (WidgetGroup) super.createUIWidget();

        ExtendedProgressWidget outline = null;
        for (Widget wid : new ArrayList<>(group.widgets)) {
            if (wid instanceof ExtendedProgressWidget progress) {
                outline = progress;
            } else if (wid instanceof ImageWidget) {
                group.removeWidget(wid);
            }
        }

        List<Cell> cells = gatherCells();
        TreeSet<Integer> sides = new TreeSet<>();
        int minDepth = Integer.MAX_VALUE, maxDepth = Integer.MIN_VALUE;
        int minRow = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE;
        for (Cell cl : cells) {
            sides.add(cl.side());
            minDepth = Math.min(minDepth, cl.depth());
            maxDepth = Math.max(maxDepth, cl.depth());
            minRow = Math.min(minRow, cl.row());
            maxRow = Math.max(maxRow, cl.row());
        }
        int sideCount = Math.max(1, sides.size());
        int cols = cells.isEmpty() ? 3 : maxDepth - minDepth + 1;
        int rows = cells.isEmpty() ? 3 : maxRow - minRow + 1;

        int pitch = MAX_PITCH;
        while (pitch > MIN_PITCH &&
                (sideCount * cols * pitch + (sideCount - 1) * PANEL_GAP > MAX_WIDTH || rows * pitch > MAX_HEIGHT)) {
            pitch--;
        }
        int icon = pitch - 2;
        int panelWidth = cols * pitch;
        int totalWidth = sideCount * panelWidth + (sideCount - 1) * PANEL_GAP;
        int startX = CENTER_X - totalWidth / 2;

        if (outline != null) {
            outline.setSelfPosition(startX - 2, TOP_Y - 2);
            outline.setSize(totalWidth + 2, rows * pitch + 2);
        }

        List<Integer> sideList = new ArrayList<>(sides);
        for (Cell cl : cells) {
            int panel = sideList.indexOf(cl.side());
            int col = cl.depth() - minDepth;
            int row = maxRow - cl.row();
            IHPCAComponentHatch hatch = cl.hatch();
            Supplier<IGuiTexture> texture = hatch::getComponentIcon;
            group.addWidget(
                    new ImageWidget(
                            startX + panel * (panelWidth + PANEL_GAP) + col * pitch, TOP_Y + row * pitch, icon, icon, texture
                    )
            );
        }
        return group;
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