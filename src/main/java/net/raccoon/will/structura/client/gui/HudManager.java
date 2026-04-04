package net.raccoon.will.structura.client.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.raccoon.will.structura.api.gui.hud.BaseHud;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class HudManager {
    private static final List<BaseHud> HUDS = new ArrayList<>();
    private static final List<Supplier<BaseHud>> HUD_FACTORIES = new ArrayList<>();

    public static void add(BaseHud hud) {
        HUDS.add(hud);
    }
    public static void clear() {
        HUDS.clear();
    }

    //updates the hud for the player
    public static void update(Player player) {
        for (BaseHud hud : HUDS) {
            hud.update(player);
        }
    }

    public static void register(Supplier<BaseHud> factory) {
        HUD_FACTORIES.add(factory);
    }

    //rebuild all huds for F3-T reloading
    public static void rebuild() {
        HUDS.clear();
        for (Supplier<BaseHud> factory : HUD_FACTORIES) {
            HUDS.add(factory.get());
        }
    }

    public static List<BaseHud> getAllHuds() {
        return HUDS;
    }

    public static void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int screenWidth, int screenHeight) {
        for (BaseHud hud : HUDS) {
            hud.render(graphics, mouseX, mouseY, screenWidth, screenHeight);
        }
    }
}
