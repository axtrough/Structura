package net.raccoon.will.structura.api.util;

import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

public class AnchorUtil {
    private Anchor anchor;
    private final ElementAnchor elementAnchor = ElementAnchor.TOP_LEFT;

    private int width, height;
    private int offsetX, offsetY;

    private final float scale = 1.0f;

    public int calculateAnchorX(int screenWidth) {
        int anchorX = switch (anchor) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT -> offsetX;
            case TOP_CENTER, CENTER, BOTTOM_CENTER -> screenWidth / 2 + offsetX;
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT -> screenWidth - offsetX;
        };

        int elementAnchorAdjustmentX = switch (elementAnchor) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT -> 0;
            case TOP_CENTER, CENTER, BOTTOM_CENTER -> (int) (width * scale / 2f);
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT -> (int) (width * scale);
        };

        return anchorX - elementAnchorAdjustmentX;
    }

    public int calculateAnchorY(int screenHeight) {
        int anchorY = switch (anchor) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> offsetY;
            case CENTER_LEFT, CENTER, CENTER_RIGHT -> screenHeight / 2 + offsetY;
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> screenHeight - offsetY;
        };

        int elementAnchorAdjustmentY = switch (elementAnchor) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> 0;
            case CENTER_LEFT, CENTER, CENTER_RIGHT -> (int) (height * scale / 2f);
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> (int) (height * scale);
        };

        return anchorY - elementAnchorAdjustmentY;
    }
}
