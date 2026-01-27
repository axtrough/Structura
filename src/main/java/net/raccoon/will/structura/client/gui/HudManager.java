package net.raccoon.will.structura.client.gui;

import net.minecraft.world.entity.player.Player;
import net.raccoon.will.structura.api.gui.hud.BaseHud;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private static final List<BaseHud> HUDS = new ArrayList<>();

    public static void add(BaseHud hud) {
        HUDS.add(hud);
    }

    //updates the actual hud in whole
    public static void update(Player player) {
        for (BaseHud hud : HUDS) {
            hud.update(player);
        }
    }
}
