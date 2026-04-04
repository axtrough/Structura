package net.raccoon.will.structura.api.gui.animation;

import net.raccoon.will.structura.api.gui.element.AbstractElement;

public class Happenings {

    public static float easeInSine(float t) {
        return (float) (1.0 - Math.cos((t * Math.PI) / 2.0));
    }

    public void scaling(float speed, AbstractElement element) {
        float current = element.getScale();
        float target = element.isHovered()
                ? element.getInitialScale() * 2
                : element.getInitialScale();

        element.setScale(current + (target - current) * speed);
    }

    public void fading(float speed, AbstractElement element) {
        float current = element.getAlpha();
        float target = element.isHovered() ? 1.0f : 0.0f;

        float eased = easeInSine(speed);
        element.setAlpha(current + (target - current) * eased);
    }
}
