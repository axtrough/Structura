package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.client.gui.GuiManager;
import net.raccoon.will.structura.client.gui.HudManager;

public abstract class BaseHud {

    // you make the elements in here dawg
    protected abstract void buildElements();

    protected <T extends GuiElement> T addElement(T element) {
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

    //this is client-side only. no server-sided stuff will work.
    public abstract void update(Player player);
}
