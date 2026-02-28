package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

import net.raccoon.will.structura.api.util.AnchorUtil;

//made by will >:3

public abstract class GuiElement {
    private final String id;
    private AnchorUtil util;

    public int width, height;
    protected int offsetX, offsetY;

    protected float scale = 1.0f;
    protected boolean debug = false;
    protected boolean visible = true;

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

    protected abstract void draw(GuiGraphics graphics);
    public void render(GuiGraphics graphics, int screenWidth, int screenHeight) {
        if (!visible) return;

        int x = util.calculateAnchorX(screenWidth);
        int y = util.calculateAnchorY(screenHeight);

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

    //* Misc
    public void debug (boolean debug) {
        this.debug = debug;
    }
    public void show() {
        this.visible = true;
    }
    public void hide() {
        this.visible = false;
    }
    public String getId() {
        return id;
    }

    //* Getters
    public float getScale() {
        return scale;
    }
    public int getOffsetX() {
        return offsetX;
    }
    public int getOffsetY() {
        return offsetY;
    }
    public Anchor getAnchor() {
        return anchor;
    }
    public int getDefaultOffsetX() {
        return defaultOffsetX;
    }
    public int getDefaultOffsetY() {
        return defaultOffsetY;
    }
    public ElementAnchor getElementAnchor() {
        return elementAnchor;
    }

    //* Setters
    public void setScale(float scale) {
        this.scale = scale;
    }
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }
    public void setElementAnchor (ElementAnchor elementAnchor) {
        this.elementAnchor = elementAnchor;
    }

    //* Helpers
    public void follow(GuiElement element, int distanceX, int distanceY) {
        this.setOffsetX(element.getOffsetX() + distanceX);
        this.setOffsetY(element.getOffsetY() + distanceY);
    }
    public void offHandOffset() {
        this.setOffsetX(this.getDefaultOffsetX() + 29);
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
}
