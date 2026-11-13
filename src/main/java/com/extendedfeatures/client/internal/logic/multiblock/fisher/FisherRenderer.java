package com.extendedfeatures.client.internal.logic.multiblock.fisher;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

import static com.extendedfeatures.client.internal.logic.multiblock.fisher.FisherTankRender.Entry;

@SuppressWarnings("all")
public class FisherRenderer {

    private static final RenderPatternControl Pattern = new RenderPatternControl(13, 0, 7);

    public static DynamicRender<?, ?> kelpAndMossRender() {

        var kelp = Blocks.KELP_PLANT.defaultBlockState();
        var kelpHead = Blocks.KELP.defaultBlockState();
        var mossBlock = Blocks.MOSS_BLOCK.defaultBlockState();

        List<Entry> entries = new ArrayList<>();

        // Kelp Pillars
        int[][] kelpHeights = {
                {0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 2, 2, 2, 1, 1, 0},
                {0, 1, 2, 3, 3, 3, 2, 1, 0},
                {1, 2, 3, 3, 4, 3, 3, 2, 1},
                {1, 2, 3, 4, 5, 4, 3, 2, 1},
                {1, 2, 3, 3, 4, 3, 3, 2, 1},
                {0, 1, 2, 3, 3, 3, 2, 1, 0},
                {0, 1, 1, 2, 2, 2, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0}
        };

        for (int fi = 0; fi < 9; fi++) {
            for (int li = 0; li < 9; li++) {
                int height = kelpHeights[fi][li];
                if (height < 1) continue;
                int front = -10 + fi;
                int left = -4 + li;
                entries.add(new Entry(Pattern.region(front + 13, front + 13, 1, height, left + 7, left + 7), kelp, kelpHead));
            }
        }

        // Moss "floor"
        float b1 = 0, b2 = 0.25f, b3 = 0.5f, b4 = 0.75f, b5 = 0.85f;

        float[][] mossHeights = {
                {b1, b1, b1, b2, b2, b2, b1, b1, b1},
                {b1, b2, b2, b2, b3, b2, b2, b2, b1},
                {b1, b2, b2, b3, b3, b3, b2, b2, b1},
                {b2, b2, b3, b3, b4, b3, b3, b2, b2},
                {b2, b3, b3, b4, b5, b4, b3, b3, b2},
                {b2, b2, b3, b3, b4, b3, b3, b2, b2},
                {b1, b2, b2, b3, b3, b3, b2, b2, b1},
                {b1, b2, b2, b2, b3, b2, b2, b2, b1},
                {b1, b1, b1, b2, b2, b2, b1, b1, b1}
        };

        for (int fi = 0; fi < 9; fi++) {
            for (int li = 0; li < 9; li++) {
                float h = mossHeights[fi][li];
                if (h <= 0) continue;
                int base = (int) Math.floor(h);
                float offset = h - base;
                int front = -10 + fi;
                int left = -4 + li;
                entries.add(new Entry(Pattern.region(front + 13, front + 13, base, base, left + 7, left + 7), mossBlock, null, 1.0f, offset, true));
            }
        }

        return new FisherTankRender(entries, false);
    }
}