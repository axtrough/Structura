package net.raccoon.will.structura.example;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.api.feature.Hitscan;
import net.raccoon.will.structura.api.gui.element.ItemElement;
import net.raccoon.will.structura.api.gui.element.BasicElement;
import net.raccoon.will.structura.api.gui.element.ProgressElement;
import net.raccoon.will.structura.api.gui.element.TextElement;
import net.raccoon.will.structura.api.gui.hud.BaseHud;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;


public class ExampleHud extends BaseHud {
    private TextElement textElement;
    private BasicElement basicElement;
    private ItemElement itemElement;
    private ProgressElement progressElement;

    public ExampleHud() {
        super("example_hud");
    }

    @Override
    protected void init() {
        basicElement = addElement(BasicElement.builder("basic_element")
                .texture(Structura.texLoc("gui/basic_element.png"))
                .texSize(128, 128)
                .anchor(Anchor.BOTTOM_RIGHT)
                .elementAnchor(ElementAnchor.BOTTOM_RIGHT)
                .offset(8, 8)
                .scale(0.25f)
                .build());

        itemElement = addElement(ItemElement.builder("item_element")
                .item(Items.DIAMOND)
                .offset(8, -4)
                .build());

        textElement = addElement(TextElement.builder("text_element")
                .text(Component.literal("This is a text element"))
                .color(255, 255, 255) //use either rgb, hex or argb
                .shadow(true)
                .offset(8, -24)
                .build());

//        progressElement = addElement(ProgressElement.builder("progress_element")
//                .texSize(128, 128)
//                .emptyBar(1, 1)
//                .fullBar(1, 1)
//                .anchor(Anchor.CENTER_RIGHT)
//                .build());

        }

    @Override
    protected void onUpdate(Player player) {
        whatBlock(player);

    }

    protected void whatBlock(Player player) {
        textElement.setVisible(true);
        itemElement.setVisible(true);

        Hitscan.HitscanResult result = Hitscan.performHitscan(player, 4);
        if (result.type() == Hitscan.HitscanResult.HitType.BLOCK) {
            BlockHitResult hit = result.blockHit();

            BlockPos pos = hit.getBlockPos();
            BlockState state = player.level().getBlockState(pos);

            textElement.setText(Component.literal(state.getBlock().getName().getString()));
            itemElement.setItem(state.getBlock().asItem());

        }
        else {
            textElement.setVisible(false);
            itemElement.setVisible(false);
        }
    }
}
