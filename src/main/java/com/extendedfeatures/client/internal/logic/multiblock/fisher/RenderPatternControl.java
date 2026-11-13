package com.extendedfeatures.client.internal.logic.multiblock.fisher;

import static com.extendedfeatures.client.internal.logic.multiblock.fisher.FisherTankRender.Region;

public class RenderPatternControl {

    private final int ctrlAisle, ctrlString, ctrlChar;

    public RenderPatternControl(int ctrlAisle, int ctrlString, int ctrlChar) {
        this.ctrlAisle = ctrlAisle;
        this.ctrlString = ctrlString;
        this.ctrlChar = ctrlChar;
    }

    public Region region(int aisleA, int aisleB, int stringA, int stringB, int charA, int charB) {
        int f1 = aisleA - ctrlAisle, f2 = aisleB - ctrlAisle;
        int u1 = stringA - ctrlString, u2 = stringB - ctrlString;
        int l1 = charA - ctrlChar, l2 = charB - ctrlChar;
        return new Region(Math.min(f1, f2), Math.max(f1, f2),
                Math.min(l1, l2), Math.max(l1, l2),
                Math.min(u1, u2), Math.max(u1, u2));
    }

    public Region cell(int aisle, int string, int ch) {
        return region(aisle, aisle, string, string, ch, ch);
    }
}