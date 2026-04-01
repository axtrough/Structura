package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.raccoon.will.structura.api.gui.layout.Anchor;

/**
 * Widget for progressBars or anything you need showing a bar or whatever.
 */

public class ProgressElement extends AbstractElement {
    private final Identifier texture;
    private final int texWidth;
    private final int texHeight;
    private final int emptyX, emptyY;
    private final int fullX, fullY;
    private float progress;

    public ProgressElement(Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y);
        this.texture = builder.texture;
        this.texWidth = builder.texWidth;
        this.texHeight = builder.texHeight;
        this.fullX = builder.fullX;
        this.fullY = builder.fullY;
        this.emptyX = builder.emptyX;
        this.emptyY = builder.emptyY;
        this.elementAnchor = builder.elementAnchor;
    }

    /** <P>
     * Creates a progress bar GUI element rendered from a single texture.
     *</P>
     *
     * <p>
     * Texture coordinates ({@code emptyX}, {@code emptyY}, {@code fullX}, {@code fullY})
     * refer to the <b>top-left corner</b> of each bar region within the texture.
     * </p>
     *
     * @param texture the texture containing both bars (empty and full)
     * @param texWidth  the total width of the texture
     * @param texHeight the total height of the texture
     * @param barWidth  the width of the progress bar
     * @param barHeight the height of the progress bar
     */

    public ProgressElement(String id, Identifier texture, int texWidth, int texHeight, int barWidth, int barHeight,
                           int emptyX, int emptyY, int fullX, int fullY, Anchor anchor, int offsetX, int offsetY) {
        super(id, barWidth, barHeight, anchor, offsetX, offsetY);
        this.texture = texture;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.emptyX = emptyX;
        this.emptyY = emptyY;
        this.fullX = fullX;
        this.fullY = fullY;
    }

    public void setProgress(float progress) {
        this.progress = Math.min(1.0F, Math.max(0.0F, progress));
    }
    public float getProgress() {
        return progress;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends AbstractElement.Builder<Builder> {
        private Identifier texture;
        private int texWidth = 128;
        private int texHeight = 128;
        private int fullX, fullY;
        private int emptyX, emptyY;

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

        public Builder fullBar(int width, int height) {
            this.fullX = width;
            this.fullY = height;
            return this;
        }

        public Builder emptyBar(int width, int height) {
            this.emptyX = width;
            this.emptyY = height;
            return this;
        }

        @Override
        public ProgressElement build() {
            return new ProgressElement(this);
        }
    }

    @Override
    protected void draw(GuiGraphicsExtractor graphics) {
        graphics.blit(texture, 0, 0, emptyX, emptyY, width, height, texWidth, texHeight);

        int filledWidth = (int) (width * progress);
        if (filledWidth > 0) {
            //it will only render this bar to the percentage of the full empty bar. will draw over it
            graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, fullX, fullY, filledWidth, height, texWidth, texHeight);
        }
    }
}
