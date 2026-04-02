package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

//made by will >:3

@EventBusSubscriber(value = Dist.CLIENT)
public abstract class AbstractElement {
    private final String id;

    protected int width, height;
    protected int x, y;

    protected float scale = 1.0f;
    protected boolean debug = false;
    protected boolean visible = true;
    protected boolean active = true;
    protected boolean isHovered;

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

    protected abstract void draw(GuiGraphicsExtractor graphics);
    public void render(GuiGraphicsExtractor graphics, int screenWidth, int screenHeight) {
        if (!visible) return;

        int x = getRenderX(screenWidth);
        int y = getRenderY(screenHeight);

        graphics.pose().pushMatrix();
        graphics.pose().translate(x, y);
        graphics.pose().scale(scale, scale);
        draw(graphics);

        if (debug) {
            graphics.fill(0, 0, width, height, 0x40FF0000);
            graphics.outline(0, 0, width, height, 0xFFFF0000);
        }

        graphics.pose().popMatrix();
    }

    public boolean isActive() {
        return this.visible && this.active;
    }



    public int getRight() {
        return this.getX() + this.width;
    }

    public int getBottom() {
        return this.getY() + this.height;
    }

    private boolean areCoordinatesInRectangle(double x, double y) {
        return x >= (double)this.getX() && y >= (double)this.getY() && x < (double)this.getRight() && y < (double)this.getBottom();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.isActive() && this.areCoordinatesInRectangle(mouseX, mouseY);
    }

    public boolean isHovered() {
        return this.isHovered;
    }

    //* Misc
    public void debug (boolean debug) {
        this.debug = debug;
    }

    public String getId() {
        return id;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible () {
        return visible;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public void setX(int posX) {
        this.x = posX;
    }

    public int getX() {
        return x;
    }

    public void setY(int posY) {
        this.y = posY;
    }

    public int getY() {
        return y;
    }

    public int getInitialY() {
        return initialY;
    }

    public int getInitialX() {
        return initialX;
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

    //* Helpers

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


    public static abstract class Builder<T extends Builder<T>> {
        protected final String id;
        protected int width = 128, height = 128;
        protected int x, y;
        protected Anchor anchor = Anchor.TOP_LEFT;
        protected ElementAnchor elementAnchor = ElementAnchor.TOP_LEFT;
        protected float scale = 1.0F;

        protected Builder(String id) {
            this.id = id;
        }

        protected abstract T self();

        public T size(int width, int height) {
            this.width = width;
            this.height = height;
            return self();
        }

        public T offset(int x, int y) {
            this.x = x;
            this.y = y;
            return self();
        }

        public T anchor(Anchor anchor) {
            this.anchor = anchor;
            return self();
        }

        public T elementAnchor(ElementAnchor elementAnchor) {
            this.elementAnchor = elementAnchor;
            return self();
        }

        public T scale(float scale) {
            this.scale = scale;
            return self();
        }

        public abstract AbstractElement build();
    }

    protected int getRenderX(int screenWidth) {
        int anchorX = switch (anchor) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT -> x;
            case TOP_CENTER, CENTER, BOTTOM_CENTER -> screenWidth / 2 + x;
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT -> screenWidth - x;
        };

        int adjustmentX = switch (elementAnchor) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT -> 0;
            case TOP_CENTER, CENTER, BOTTOM_CENTER -> (int) (width * scale / 2f);
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT -> (int) (width * scale);
        };

        return anchorX - adjustmentX;
    }

    protected int getRenderY(int screenHeight) {
        int anchorY = switch (anchor) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> -y;
            case CENTER_LEFT, CENTER, CENTER_RIGHT -> screenHeight / 2 - y;
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> screenHeight - y;
        };

        int adjustmentY = switch (elementAnchor) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> 0;
            case CENTER_LEFT, CENTER, CENTER_RIGHT -> (int) (height * scale / 2f);
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> (int) (height * scale);
        };

        return anchorY - adjustmentY;
    }
}
