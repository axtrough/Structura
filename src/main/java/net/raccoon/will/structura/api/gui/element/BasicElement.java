package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

/**
 * Simple element to render images and so on
 */

public class BasicElement extends AbstractElement {
    protected Identifier texture;
    protected final Identifier originalTexture;
    protected final int texWidth, texHeight;

    public BasicElement(Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y);
        this.texture = builder.texture;
        this.originalTexture = builder.texture;
        this.texWidth = builder.texWidth;
        this.texHeight = builder.texHeight;
        this.elementAnchor = builder.elementAnchor;
    }

    //fallback if no texture is provided
    public BasicElement(String id, int width, int height, int texWidth, int texHeight, Anchor anchor, int offsetX, int offsetY) {
        super(id, width, height, anchor, offsetX, offsetY);
        this.texture = null;
        this.originalTexture = null;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
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
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, width, height, texWidth, texHeight);
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends AbstractElement.Builder<Builder> {
        private Identifier texture;
        private int texWidth = 128;
        private int texHeight = 128;

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


