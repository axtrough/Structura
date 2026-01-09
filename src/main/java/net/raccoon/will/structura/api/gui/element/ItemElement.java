package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.raccoon.will.structura.api.gui.layout.Anchor;

public class ItemElement extends GuiElement {
    private ItemStack item;
    private final ItemStack originalItem;

    public ItemElement(ItemStack stack, int width, int height, Anchor anchor, int offsetX, int offsetY) {
        super(width, height, anchor, offsetX, offsetY);
        setItem(stack);
        this.originalItem = stack;
    }

    private void resetItem() {
        this.item = originalItem;
    }

    @Override
    public void resetAll() {
        super.resetAll();
        resetItem();
    }

    public void setItem(ItemStack stack) {
        if (this.item == null || !ItemStack.matches(this.item, stack)) {
            this.item = stack != null ? stack.copy() : ItemStack.EMPTY;
        }
    }

    @Override
    protected void draw(GuiGraphics graphics) {
        if (item != null && !item.isEmpty()) {
            graphics.renderItem(item, 0, 0);
        }
    }
}