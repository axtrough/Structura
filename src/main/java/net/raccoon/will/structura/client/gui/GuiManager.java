package net.raccoon.will.structura.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.raccoon.will.structura.api.gui.element.GuiElement;

import java.util.*;

public class GuiManager {
    private static final Map<String, GuiElement> ELEMENTS = new LinkedHashMap<>();
    public static boolean DEBUG_RENDER_TIME = false;

    public static void add(GuiElement element) {
        if (ELEMENTS.containsKey(element.getId())) {
            throw new IllegalStateException("Duplicate Element id: " + element.getId());
        }
        ELEMENTS.put(element.getId(), element);
    }

    public static void remove(String id) {
        ELEMENTS.remove(id);
    }

    public static void clear() {
        ELEMENTS.clear();
    }

    //updates all the elements ofc
    public static void updateAll() {
        for (GuiElement element : ELEMENTS.values()) {
            element.update();
        }
    }

    public static Collection<GuiElement> getAll() {
        return ELEMENTS.values();
    }

    public static Set<String> getIds() {
        return ELEMENTS.keySet();
    }

    public static GuiElement get(String id) {
        return ELEMENTS.get(id);
    }

    // renders all the elements. also for debug things
    public static void render(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        for (GuiElement element : ELEMENTS.values()) {
            element.render(guiGraphics, screenWidth, screenHeight);
        }

        long start = 0;
        if (DEBUG_RENDER_TIME) start = System.nanoTime();

        if (DEBUG_RENDER_TIME) {
            long end = System.nanoTime();
            System.out.println("Render took: " + (end - start) / 1_000_000.0 + " ms");
        }
    }
}
