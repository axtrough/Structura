package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.raccoon.will.structura.api.gui.layout.Anchor;

/**
 * Simple element to render images and so on
 */

public class BasicElement extends AbstractElement {
    protected Identifier texture;
    protected final Identifier originalTexture;
    protected final int texWidth, texHeight;

    public BasicElement(Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y, builder.scale);
        this.texture = builder.texture;
        this.originalTexture = builder.texture;
        this.texWidth = builder.texWidth;
        this.texHeight = builder.texHeight;
        this.elementAnchor = builder.elementAnchor;
    }


    public void resetTexture() {
        this.texture = originalTexture;
    }

    public void setTexture(Identifier texture) {
        if (!texture.equals(this.texture)) {
            this.texture = texture;
        }
    }

    @Override
    protected void draw(GuiGraphicsExtractor graphics) {
        if (texture == null) return;
        int color = ARGB.color(255,255,255);
        int alphaColor = ARGB.multiplyAlpha(color, alpha);
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, width, height, texWidth, texHeight, alphaColor);
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends AbstractElement.Builder<Builder> {
        private Identifier texture;
        private int texWidth = 64;
        private int texHeight = 64;

        public Builder(String id) {
            super(id);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder texture(Identifier path) {
            this.texture = path;
            return this;
        }

        public Builder texSize(int texWidth, int texHeight) {
            this.texWidth = texWidth;
            this.texHeight = texHeight;
            return this;
        }

        @Override
        public BasicElement build() {
            return new BasicElement(this);
        }
    }
}


