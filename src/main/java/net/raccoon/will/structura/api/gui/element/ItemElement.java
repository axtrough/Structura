package net.raccoon.will.structura.api.gui.element;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Element that allows you to render any item.
 */

public class ItemElement extends AbstractElement {
    private ItemStack itemStack;
    private Item item;
    private final Item originalItem;


    public ItemElement(Builder builder) {
        super(builder.id, builder.width, builder.height, builder.anchor, builder.x, builder.y, builder.scale);
        this.item = builder.item;
        this.originalItem = builder.item;
        this.elementAnchor = builder.elementAnchor;
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
    protected void draw(GuiGraphicsExtractor graphics) {
        if (item != null) {
            if (itemStack == null) itemStack = new ItemStack(item);
            graphics.item(itemStack, 0, 0);
        }
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends AbstractElement.Builder<Builder> {
        private Item item;
        private int width = 16;
        private int height = 16;

        public Builder(String id) {
            super(id);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder item(Item item) {
            this.item = item;
            return this;
        }

        @Override
        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        @Override
        public ItemElement build() {
            return new ItemElement(this);
        }
    }
}