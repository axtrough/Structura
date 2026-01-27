package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.client.gui.GuiManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseHud {
    private final Map<String, GuiElement> elements = new LinkedHashMap<>();
    //youll need to create a construction and thats where youll be adding the elements

    //this just adds the elements to the guiManager.
    protected <T extends GuiElement> T addElement(T element) {
        elements.put(element.getId(), element);
        GuiManager.add(element);
        return element;
    }

    // returns the ItemStack of the given item in hand, or EMPTY if not held
    protected static ItemStack findHeldItem(Player player, Item item) {
        for (ItemStack stack : new ItemStack[]{player.getMainHandItem(), player.getOffhandItem()}) {
            if (stack.getItem().equals(item)) return stack;
        }
        return ItemStack.EMPTY;
    }

    // returns true if the player is standing on the given block
    protected static boolean isStandingOnBlock(Player player, Block block) {
        return player.level().getBlockState(player.blockPosition().below())
                .is(block);
    }

    protected List<GuiElement> getContainingElements () {
        return (List<GuiElement>) elements.values();
    }

    public void hideHud() {
        if (this.elements.isEmpty()) return;

        for (GuiElement element : this.elements.values()) {
            element.hide();
        }
    }

    protected void showHud() {
        if (this.elements.isEmpty()) return;

        for (GuiElement element : this.elements.values()) {
            element.show();
        }
    }

    //this is client-side only. no server-sided stuff will work.
    public abstract void update(Player player);
}
