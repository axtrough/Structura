package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.raccoon.will.structura.api.gui.layout.Anchor;
/**
 * Element that allows you to render any item.
 */

public class ItemElement extends GuiElement {
    private ItemStack item;
    private final ItemStack originalItem;

    public ItemElement(ItemStack stack, int width, int height, Anchor anchor, int offsetX, int offsetY) {
        super(width, height, anchor, offsetX, offsetY);
        ItemStack safe = stack == null ? ItemStack.EMPTY : stack.copy();
        this.item = safe;
        this.originalItem = safe.copy();
    }

    //Constructor if no initial item is wanted. May be used if you assign item later in Update.
    public ItemElement(int width, int height, Anchor anchor, int offsetX, int offsetY) {
        this(ItemStack.EMPTY, width, height, anchor, offsetX, offsetY);
    }

    public void setItem(ItemStack stack) {
        ItemStack safe = stack == null ? ItemStack.EMPTY : stack.copy();

        if (!ItemStack.matches(this.item, safe)) {
            this.item = safe.copy();
        }
    }

    private void resetItem() {
        this.item = originalItem.copy();
    }

    @Override
    public void resetAll() {
        super.resetAll();
        resetItem();
    }

    @Override
    protected void draw(GuiGraphics graphics) {
        if (!item.isEmpty()) {
            graphics.renderItem(item, 0, 0);
        }
    }
}