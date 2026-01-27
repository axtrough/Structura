package net.raccoon.will.structura.example;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.api.gui.element.ItemElement;
import net.raccoon.will.structura.api.gui.element.BasicElement;
import net.raccoon.will.structura.api.gui.element.ProgressElement;
import net.raccoon.will.structura.api.gui.element.TextElement;
import net.raccoon.will.structura.api.gui.hud.BaseHud;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;
import net.raccoon.will.structura.client.gui.HudManager;

public class ExampleHud extends BaseHud {
    private final ItemElement itemElement;
    private final TextElement textElement;
    private final BasicElement basicElement;
//    private final ProgressElement progressElement;

    public ExampleHud() {
        itemElement = addElement(
                new ItemElement("item_element", (Items.DIAMOND),
                        16, 16, Anchor.TOP_LEFT, 5, 5));

        textElement = addElement(
                new TextElement("text_element", Component.literal("This is a text element"),
                        0xeb34e5, false, Anchor.CENTER, 0, 0));

        basicElement = addElement(
                new BasicElement("basic_element", Structura.texLoc("gui/basic_element.png"),
                        128, 128, 128, 128, Anchor.BOTTOM_RIGHT, 8, 8));

//        progressElement = addElement(
//                new ProgressElement("progress_element", Structura.texLoc("gui/progress_example.png"),
//                        128, 128, 64, 16, 0, 0, 0, 16, Anchor.TOP_RIGHT, 16, 16));
    }

    @Override
    public void update(Player player) {
        boolean standingOn = BaseHud.isStandingOnBlock(player, Blocks.DIAMOND_BLOCK);
        basicElement.setElementAnchor(ElementAnchor.BOTTOM_RIGHT);
        textElement.setElementAnchor(ElementAnchor.CENTER);
        itemElement.resetItem();
        basicElement.setScale(0.5f);
        this.hideHud();

        if (standingOn) {
            this.showHud();
            itemElement.setItem(Items.DRAGON_EGG);
        }
    }
}
