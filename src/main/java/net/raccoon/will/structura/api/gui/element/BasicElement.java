package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.raccoon.will.structura.api.gui.layout.Anchor;

/**
 * Simple element to render images and so on
 */

public class BasicElement extends GuiElement {
    protected ResourceLocation texture;
    protected final ResourceLocation originalTexture;
    protected final int texWidth, texHeight;

    public BasicElement(ResourceLocation texture, int width, int height, int texWidth, int texHeight, Anchor anchor, int offsetX, int offsetY) {
        super(width, height, anchor, offsetX, offsetY);
        this.texture = texture;
        this.originalTexture = texture;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    //Constructor if no initial image is wanted. May be used if you assign image later in Update.
    public BasicElement(int width, int height, int texWidth, int texHeight, Anchor anchor, int offsetX, int offsetY) {
        super(width, height, anchor, offsetX, offsetY);
        this.texture = null;
        this.originalTexture = null;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    public void resetTexture() {
        this.texture = originalTexture;
    }

    public void setTexture(ResourceLocation texture) {
        if (!texture.equals(this.texture)) {
            this.texture = texture;
        }
    }

    @Override
    protected void draw(GuiGraphics graphics) {
        if (texture == null) return;
        graphics.blit(texture, 0, 0, 0, 0, width, height, texWidth, texHeight);
    }
}
