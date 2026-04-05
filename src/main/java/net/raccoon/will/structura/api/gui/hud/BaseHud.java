package net.raccoon.will.structura.api.gui.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.structura.api.gui.element.AbstractElement;
import net.raccoon.will.structura.client.gui.HudManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHud {
    private final List<AbstractElement> elements = new ArrayList<>();
    private final String id;

    protected BaseHud(String id) {
        this.id = id;
        init();
    };

    public String getId() {
        return id;
    }

    public void add() {
        HudManager.add(this);
    }

    //define widgets inside this
    protected abstract void init();

    //this just adds the widgets to the hud.
    public <T extends AbstractElement> T addElement(T element) {
        elements.add(element);
        return element;
    }

    public List<AbstractElement> getElements() {
        return elements;
    }

    public void hideHud() {
        if (this.elements.isEmpty()) return;
        for (AbstractElement element : elements) {
            element.setVisible(false);
        }
    }

    protected void showHud() {
        if (this.elements.isEmpty()) return;
        for (AbstractElement element : elements) {
            element.setVisible(true);
        }
    }

    public final void update(Player player, float deltaTime) {
        for (AbstractElement element : elements) {
            element.update(deltaTime);
        }

        onUpdate(player);
    }

    //this is client-side only. no server-sided stuff will work.
    protected abstract void onUpdate(Player player);

    public void render(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int screenWidth, int screenHeight) {
        for (AbstractElement element : elements) {
            element.render(guiGraphics, mouseX, mouseY, screenWidth, screenHeight);
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
