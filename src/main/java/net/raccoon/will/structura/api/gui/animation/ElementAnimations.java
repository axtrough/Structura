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

    //scales up when hover, scales down when not hover.
    public void scaling(AbstractElement element, float scale, float ticks) {
        float current = element.getScale();
        float target = element.isHovered() ? element.getInitialScale() * scale : element.getInitialScale();
        float time = deltaSpeed(ticks, deltaTime);
        element.setScale(current + (target - current) * time);
    }

    //mostly just helper for fadeIn/fadeOut but can be use separately.
    private void fadeTo(AbstractElement element, float ticks, float target) {
        float current = element.getAlpha();
        float time = deltaSpeed(ticks, deltaTime);
        element.setAlpha(current + (target - current) * time);
    }

    //fades in duhh
    public void fadeIn(AbstractElement element, float ticks) {
        fadeTo(element, ticks, 1.0f);
    }

    //fades out duh
    public void fadeOut(AbstractElement element, float ticks) {
        fadeTo(element, ticks, 0.0f);
    }
}
