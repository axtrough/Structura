package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class AnimatedElement extends AbstractElement {
    private final Identifier texture;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private final int textureWidth;
    private final int textureHeight;
    private final float secondsPerFrame;

    private int currentFrame = 0;
    private float timeAccumulator = 0f;

    public AnimatedElement(Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y, builder.scale);
        this.texture = builder.texture;
        this.frameCount = builder.frameCount;
        this.frameWidth = builder.frameWidth;
        this.frameHeight = builder.frameHeight;
        this.textureWidth = builder.frameWidth;
        this.textureHeight = builder.frameHeight * builder.frameCount;
        this.secondsPerFrame = 1.0f / builder.fps;
        this.elementAnchor = builder.elementAnchor;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timeAccumulator += deltaTime;
        if (timeAccumulator >= secondsPerFrame) {
            timeAccumulator -= secondsPerFrame;
            currentFrame = (currentFrame + 1) % frameCount;
        }
    }

    @Override
    protected void draw(GuiGraphicsExtractor graphics) {
        int u = 0;
        int v = currentFrame * frameHeight;
        int color = ARGB.color(255, 255, 255);
        int alphaColor = ARGB.multiplyAlpha(color, alpha);
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture,
                0, 0, u, v, width, height, textureWidth, textureHeight, alphaColor);
    }

    public void resetAnimation() {
        currentFrame = 0;
        timeAccumulator = 0f; // was tickAccumulator, fixed
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends AbstractElement.Builder<Builder> {
        private Identifier texture;
        private int frameCount = 1;
        private int frameWidth = 64;
        private int frameHeight = 64;
        private float fps = 8f;

        public Builder(String id) {
            super(id);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder texture(Identifier texture) {
            this.texture = texture;
            return this;
        }

        public Builder frameCount(int frameCount) {
            this.frameCount = frameCount;
            return this;
        }

        public Builder frameSize(int frameWidth, int frameHeight) {
            this.frameWidth = frameWidth;
            this.frameHeight = frameHeight;
            return this;
        }

        public Builder fps(float fps) {
            this.fps = fps;
            return this;
        }

        @Override
        public AnimatedElement build() {
            return new AnimatedElement(this);
        }
    }
}

