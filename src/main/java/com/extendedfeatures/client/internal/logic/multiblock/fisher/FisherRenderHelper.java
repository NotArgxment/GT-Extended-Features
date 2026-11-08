package com.extendedfeatures.client.internal.logic.multiblock.fisher;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

import static com.extendedfeatures.client.internal.logic.multiblock.fisher.FisherKelpRenderer.Entry;

public class FisherRenderHelper {

    private static final RenderPatternControl Pattern = new RenderPatternControl(8, 0, 4);

    public static DynamicRender<?, ?> kelpTankRender() {
        var kelp = Blocks.KELP_PLANT.defaultBlockState();
        var kelpHead = Blocks.KELP.defaultBlockState();

        int[][] heights = {
                { 3, 2, 4 },
                { 6, 7, 3 },
                { 5, 4, 6 }
        };

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                entries.add(new Entry(Pattern.region(3 + i, 3 + i, 1, heights[i][j], 3 + j, 3 + j), kelp, kelpHead));
            }
        }
        return new FisherKelpRenderer(entries, false);
    }
}