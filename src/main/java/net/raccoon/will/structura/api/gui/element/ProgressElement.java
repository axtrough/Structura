package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.raccoon.will.structura.api.gui.layout.Anchor;

/**
 * Element for progressBars or anything you need showing a bar or whatever.
 */

public class ProgressElement extends GuiElement{
    private final ResourceLocation texture;
    private final int texWidth;
    private final int texHeight;
    private final int emptyX, emptyY;
    private final int fullX, fullY;
    private float progress;

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
    public ProgressElement(ResourceLocation texture, int texWidth, int texHeight, int barWidth, int barHeight, int emptyX, int emptyY, int fullX, int fullY,
                           Anchor anchor, int offsetX, int offsetY) {
        super(barWidth, barHeight, anchor, offsetX, offsetY);
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

    @Override
    protected void draw(GuiGraphics graphics) {
        graphics.blit(texture, 0, 0, emptyX, emptyY, width, height, texWidth, texHeight);

        int filledWidth = (int) (width * progress);
        if (filledWidth > 0) {
            //it will only render this bar to the percentage of the full empty bar. will draw over it
            graphics.blit(texture, 0, 0, fullX, fullY, filledWidth, height, texWidth, texHeight);
        }
    }
}
