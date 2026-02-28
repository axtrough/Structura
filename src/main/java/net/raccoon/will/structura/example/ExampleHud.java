package net.raccoon.will.structura.example;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.api.gui.element.ItemElement;
import net.raccoon.will.structura.api.gui.element.BasicElement;
import net.raccoon.will.structura.api.gui.element.TextElement;
import net.raccoon.will.structura.api.gui.hud.BaseHud;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;

public class ExampleHud extends BaseHud {
    private final ItemElement itemElement;
    private final TextElement textElement;
    private final BasicElement basicElement;
    private final Minecraft mc = Minecraft.getInstance();

//    private final ProgressElement progressElement;

    public ExampleHud() {

        basicElement = addElement(
                new BasicElement("basic_element", Structura.texLoc("gui/basic_element.png"),
                        128, 128, 128, 128, Anchor.BOTTOM_RIGHT, 8, 8));

        itemElement = addElement(
                new ItemElement("item_element", (Items.DIAMOND),
                        16, 16, 5, 5));

        textElement = addElement(
                new TextElement("text_element", Component.literal("This is a text element"),
                        0xeb34e5, false, Anchor.CENTER, 0, 0));

//        progressElement = addElement(
//                new ProgressElement("progress_element", Structura.texLoc("gui/progress_example.png"),
//                        128, 128, 64, 16, 0, 0, 0, 16, Anchor.TOP_RIGHT, 16, 16));

        basicElement.setElementAnchor(ElementAnchor.BOTTOM_RIGHT);
        basicElement.setScale(0.5f);
        textElement.setElementAnchor(ElementAnchor.CENTER);
        itemElement.setElementAnchor(ElementAnchor.CENTER);
    }

    @Override
    public void update(Player player) {
        super.update(player);
    }
}
