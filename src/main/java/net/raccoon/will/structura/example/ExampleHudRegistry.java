package net.raccoon.will.structura.example;

import net.raccoon.will.structura.client.gui.HudManager;

public class ExampleHudRegistry {

    public static void register() {
        HudManager.add(new ExampleHud());
    }
}
