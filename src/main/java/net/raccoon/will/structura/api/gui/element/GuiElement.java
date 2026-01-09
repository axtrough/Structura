package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;
import net.raccoon.will.structura.api.gui.layout.GuiContainer;

//made by will >:3

public abstract class GuiElement {
    public int width, height;
    protected int offsetX, offsetY;
    protected final Anchor anchor;
    protected float alpha, scale = 1.0f;
    protected boolean visible = true;
    protected ElementAnchor elementAnchor = ElementAnchor.TOP_LEFT;
    public GuiContainer parent = null;
    protected boolean debug = false;

    protected final int originalWidth, originalHeight, originalOffsetX, originalOffsetY;
    protected final float originalScale;

    public GuiElement(int width, int height, Anchor anchor, int offsetX, int offsetY) {
        this.width = width;
        this.height = height;
        this.anchor = anchor;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.originalWidth = width;
        this.originalHeight = height;
        this.originalOffsetX = offsetX;
        this.originalOffsetY = offsetY;
        this.originalScale = scale;
    }

    public void setScale(float scale) {
        this.scale = scale; }

    public void setDebug (boolean debug) {
        this.debug = debug; }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX; }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY; }

    public void setVisible(boolean visible) {
        this.visible = visible; }

    public void setAlpha(float alpha) {
        this.alpha = Math.min(1.0f, Math.max(0.0f, alpha)); }

    public void setElementAnchor (ElementAnchor elementAnchor) {
        this.elementAnchor = elementAnchor; }

    public boolean isVisible() { return visible; }
    public void updateSize() {}

    public boolean isChildElement() {
        return parent != null;
    }

    public float getAlpha() {
        return alpha;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getOriginalOffsetX() {
        return originalOffsetX;
    }

    public int getOriginalOffsetY() {
        return originalOffsetY;
    }

    public void resetScale() {
        this.scale = originalScale;
    }

    public void resetSize() {
        this.width = originalWidth; this.height = originalHeight;
    }

    public void resetOffset() {
        this.offsetX = originalOffsetX; this.offsetY = originalOffsetY;
    }

    public void resetAll() {
        resetOffset(); resetScale(); resetSize();
    }

    public void update() {
        updateSize();
    }

    public void render(GuiGraphics graphics, int screenWidth, int screenHeight) {
        if (!visible) return;

        int x = calculateTopLeftX(screenWidth);
        int y = calculateTopLeftY(screenHeight);

        graphics.setColor(1.0f, 1.0f, 1.0f, alpha);
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 0);
        graphics.pose().scale(scale, scale, 1);

        draw(graphics);

        if (debug) {
            graphics.fill(0, 0, width, height, 0x40FF0000);
            graphics.renderOutline(0, 0, width, height, 0xFFFF0000);
        }

        graphics.pose().popPose();
    }

    protected abstract void draw(GuiGraphics graphics);

    protected int calculateTopLeftX(int screenWidth) {
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

    protected int calculateTopLeftY(int screenHeight) {
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
