package net.raccoon.will.structura.api.gui.animation;

import net.raccoon.will.structura.api.gui.element.AbstractElement;

public class ElementAnimations {
    private float deltaTime = 0f;

    public void tick(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    private static float deltaSpeed(float ticks, float deltaTime) {
        return 1f - (float) Math.pow(1f - 0.99f, deltaTime / (ticks / 20f));
    }

    public void scale(AbstractElement element, float scale, float ticks, boolean shouldScale) {
        float current = element.getScale();
        float target = shouldScale ? element.getInitialScale() * scale : element.getInitialScale();
        float time = deltaSpeed(ticks, deltaTime);
        element.setScale(current + (target - current) * time);
    }

    public void fade(AbstractElement element, float baseAlpha, float targetAlpha, float ticks, boolean shouldFade) {
        float current = element.getAlpha();
        float target = shouldFade ? targetAlpha : baseAlpha;
        float time = deltaSpeed(ticks, deltaTime);
        element.setAlpha(current + (target - current) * time);
    }
}
