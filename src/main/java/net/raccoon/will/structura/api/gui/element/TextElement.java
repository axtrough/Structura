package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.raccoon.will.structura.api.util.ColorUitl;

/**
 * Pretty self-explanatory.
 * Make a widget with text
 */

public class TextElement extends AbstractElement {
    private final Component originalText;
    private Component text;

    private final int originalColor;
    private boolean shadow;
    private int color;

    protected boolean dirty = true;
    protected int cachedHeight;
    protected int cachedWidth;

    public TextElement(TextElement.Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y);
        this.text = builder.text;
        this.originalText = builder.text;
        this.color = builder.color;
        this.originalColor = builder.color;
        this.shadow = builder.shadow;
        this.elementAnchor = builder.elementAnchor;
    }

    public void resetText() {
        setText(originalText);
    }
    public void resetColor() {
        this.color = originalColor;
    }

    @Override
    public void resetAll() {
        super.resetAll();
        resetText();
        resetColor();
    }

    public void setText(Component text) {
        if (this.text == null || !this.text.equals(text)) {
            this.text = text != null ? text : Component.empty();
            dirty = true;
        }
    }

    public void setColor(int color) {
        if (this.color != color) {
            this.color = color;
        }
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    @Override
    public void updateSize() {
        if (dirty) {
            cachedWidth = Minecraft.getInstance().font.width(text);
            cachedHeight = Minecraft.getInstance().font.lineHeight;
            width = cachedWidth;
            height = cachedHeight;
            dirty = false;
        }
    }

    @Override
    protected void draw(GuiGraphicsExtractor graphics) {
        if (text == null || text.getString().isEmpty()) return;
        graphics.text(Minecraft.getInstance().font, text, 0, 0, color, shadow);
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }
    public static class Builder extends AbstractElement.Builder<Builder> {
        private Component text;
        private boolean shadow = false;
        private int color;

        public Builder(String id) {
            super(id);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder text(Component text) {
            this.text = text;
            return this;
        }

        public Builder color(int R, int G, int B) {
            this.color = ColorUitl.rgb(R, G, B);
            return this;
        }

        public Builder color(int A, int R, int G, int B) {
            this.color = ColorUitl.argb(A, R, G, B);
            return this;
        }

        public Builder color(int hex) {
            this.color = hex;
            return this;
        }



        public Builder shadow(boolean shadow) {
            this.shadow = shadow;
            return this;
        }

        @Override
        public TextElement build() {
            return new TextElement(this);
        }
    }
}