package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.client.gui.HudManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHud {
    private final List<GuiElement> elements = new ArrayList<>();
    //youll need to create a construction and thats where youll be adding the elements

    public void add() {
        HudManager.add(this);
    }

    //this just adds the elements to the guiManager.
    public <T extends GuiElement> T addElement(T element) {
        elements.add(element);
        return element;
    }

    public List<GuiElement> getContainingElements () {
        return elements;
    }

    public void hideHud() {
        if (this.elements.isEmpty()) return;
        for (GuiElement element : elements) {
            element.hide();
        }
    }

    protected void showHud() {
        if (this.elements.isEmpty()) return;
        for (GuiElement element : elements) {
            element.show();
        }
    }

    //this is client-side only. no server-sided stuff will work.
    public void update(Player player) {
        for (GuiElement element : elements) {
            element.update();
        }
    }

    public void render(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        for (GuiElement element : elements) {
            element.render(guiGraphics, screenWidth, screenHeight);
        }
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




}
