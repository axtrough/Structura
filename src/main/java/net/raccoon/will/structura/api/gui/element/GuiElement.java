package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

//made by will >:3

public abstract class GuiElement {
    private final String id;

    public int width, height;
    protected int offsetX, offsetY;

    protected boolean debug = false;
    protected boolean visible = true;
    protected float scale = 1.0f;

    protected Anchor anchor;
    protected ElementAnchor elementAnchor = ElementAnchor.TOP_LEFT;

    protected final float defaultScale;
    protected final int defaultWidth, defaultHeight, defaultOffsetX, defaultOffsetY;

    public GuiElement(String id, int width, int height, Anchor anchor, int offsetX, int offsetY) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.anchor = anchor;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.defaultWidth = width;
        this.defaultHeight = height;
        this.defaultOffsetX = offsetX;
        this.defaultOffsetY = offsetY;
        this.defaultScale = scale;
    }

    public String getId() {
        return id;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setElementAnchor (ElementAnchor elementAnchor) {
        this.elementAnchor = elementAnchor;
    }

    public ElementAnchor getElementAnchor() {
        return elementAnchor;
    }

    public void debug (boolean debug) {
        this.debug = debug;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public int getDefaultOffsetX() {
        return defaultOffsetX;
    }

    public int getDefaultOffsetY() {
        return defaultOffsetY;
    }

    public void offHandOffset() {
        this.setOffsetX(this.getDefaultOffsetX() + 29);
    }

    public void follow(GuiElement element, int distanceX, int distanceY) {
        this.setOffsetX(element.getOffsetX() + distanceX);
        this.setOffsetY(element.getOffsetY() + distanceY);
    }

    public void resetScale() {
        this.scale = defaultScale;
    }

    public void resetSize() {
        this.width = defaultWidth;
        this.height = defaultHeight;
    }

    public void resetOffset() {
        this.offsetX = defaultOffsetX;
        this.offsetY = defaultOffsetY;
    }

    public void resetAll() {
        resetOffset();
        resetScale();
        resetSize();
    }

    public void updateSize() {}

    public void update() {
        updateSize();
    }

    public void render(GuiGraphics graphics, int screenWidth, int screenHeight) {
        if (!visible) return;

        int x = calculateAnchorX(screenWidth);
        int y = calculateAnchorY(screenHeight);

        graphics.pose().pushMatrix();
        graphics.pose().translate(x, y);
        graphics.pose().scale(scale, scale);

        draw(graphics);

        if (debug) {
            graphics.fill(0, 0, width, height, 0x40FF0000);
            graphics.renderOutline(0, 0, width, height, 0xFFFF0000);
        }

        graphics.pose().popMatrix();
    }

    protected abstract void draw(GuiGraphics graphics);

    protected int calculateAnchorX(int screenWidth) {
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

    protected int calculateAnchorY(int screenHeight) {
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
