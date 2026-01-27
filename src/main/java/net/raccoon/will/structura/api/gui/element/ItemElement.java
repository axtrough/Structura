package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.raccoon.will.structura.api.gui.layout.Anchor;
/**
 * Element that allows you to render any item.
 */

public class ItemElement extends GuiElement {
    private Item item;
    private ItemStack itemStack;
    private final Item originalItem;

    public ItemElement(String id, Item item, int width, int height, Anchor anchor, int offsetX, int offsetY) {
        super(id, width, height, anchor, offsetX, offsetY);
        this.item = item;
        this.originalItem = item;
    }

    public ItemElement(String id, int width, int height, Anchor anchor, int offsetX, int offsetY) {
        this(id, null, width, height, anchor, offsetX, offsetY);
    }

    public void setItem(Item item) {
        if (this.item != item) {
            this.item = item;
            this.itemStack = null; // reset lazy stack
        }
    }

    public void resetItem() {
        this.item = originalItem;
        this.itemStack = null;
    }

    @Override
    public void resetAll() {
        super.resetAll();
        resetItem();
    }

    @Override
    protected void draw(GuiGraphics graphics) {
        if (item != null) {
            if (itemStack == null) itemStack = new ItemStack(item);
            graphics.renderItem(itemStack, 0, 0);
        }
    }
}