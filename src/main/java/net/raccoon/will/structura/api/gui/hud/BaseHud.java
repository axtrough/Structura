package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.client.gui.GuiManager;

public abstract class BaseHud {

    protected <T extends GuiElement> T addElement(T element) {
        GuiManager.add(element);
        return element;
    }

    protected static ItemStack findHeldItem(Player player, Item item) {
        for (ItemStack stack : new ItemStack[]{player.getMainHandItem(), player.getOffhandItem()}) {
            if (stack.getItem().equals(item)) return stack;
        }
        return ItemStack.EMPTY;
    }

    public abstract void update(Player player);
}
