package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

import net.raccoon.will.structura.api.util.AnchorUtil;

//made by will >:3

@OnlyIn(Dist.CLIENT)
public abstract class AbstractElement {
    private final String id;
    private AnchorUtil util;

    public int width, height;
    protected int x, y;

    protected float scale = 1.0f;
    protected boolean debug = false;
    protected boolean visible = true;

    protected Anchor anchor;
    protected ElementAnchor elementAnchor = ElementAnchor.TOP_LEFT;

    protected final float initialScale;
    protected final int initialWidth, initialHeight, initialX, initialY;

    public AbstractElement(String id, int width, int height, Anchor anchor, int posX, int posY) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.anchor = anchor;
        this.x = posX;
        this.y = posY;

        this.initialWidth = width;
        this.initialHeight = height;
        this.initialX = posX;
        this.initialY = posY;
        this.initialScale = scale;
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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Anchor getAnchor() {
        return anchor;
    }
    public int getInitialX() {
        return initialX;
    }
    public int getInitialY() {
        return initialY;
    }
    public ElementAnchor getElementAnchor() {
        return elementAnchor;
    }

    //* Setters
    public void setScale(float scale) {
        this.scale = scale;
    }
    public void setX(int posX) {
        this.x = posX;
    }
    public void setY(int posY) {
        this.y = posY;
    }
    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }
    public void setElementAnchor (ElementAnchor elementAnchor) {
        this.elementAnchor = elementAnchor;
    }

    //* Helpers
    public void follow(AbstractElement element, int distanceX, int distanceY) {
        this.setX(element.getX() + distanceX);
        this.setY(element.getY() + distanceY);
    }

    public void offHandOffset() {
        this.setX(this.getInitialX() + 29);
    }

    public void resetScale() {
        this.scale = initialScale;
    }
    public void resetSize() {
        this.width = initialWidth;
        this.height = initialHeight;
    }
    public void resetOffset() {
        this.x = initialX;
        this.y = initialY;
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
