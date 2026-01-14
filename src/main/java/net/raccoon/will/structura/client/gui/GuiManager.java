package net.raccoon.will.structura.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.raccoon.will.structura.api.gui.element.GuiElement;

import java.util.ArrayList;
import java.util.List;

public class GuiManager {
    private static final List<GuiElement> ELEMENTS = new ArrayList<>();
    public static boolean DEBUG_RENDER_TIME = false;

    public static void add(GuiElement element) {
        ELEMENTS.add(element);
    }

    public static void remove(GuiElement element) {
        ELEMENTS.remove(element);
    }

    public static void clear() {
        ELEMENTS.clear();
    }

    //updates all the elements ofc
    public static void updateAll() {
        for (GuiElement element : ELEMENTS) {
            element.update();
        }
    }

    // renders all the elements. also for debug things
    public static void render(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        long start = 0;
        if (DEBUG_RENDER_TIME) start = System.nanoTime();

        for (GuiElement element : ELEMENTS) {
            if (!element.isChildElement()) {
                element.render(guiGraphics, screenWidth, screenHeight);
            }
        }

        if (DEBUG_RENDER_TIME) {
            long end = System.nanoTime();
            System.out.println("Render took: " + (end - start) / 1_000_000.0 + " ms");
        }
    }
}
