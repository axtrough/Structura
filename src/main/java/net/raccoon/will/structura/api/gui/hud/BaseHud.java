package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.world.entity.player.Player;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.client.gui.GuiManager;

public abstract class BaseHud {

    protected <T extends GuiElement> T addElement(T element) {
        GuiManager.add(element);
        return element;
    }

    public abstract void update(Player player);
}
