package net.raccoon.will.structura.example;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.fml.ModList;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.api.feature.Hitscan;
import net.raccoon.will.structura.api.gui.animation.ElementAnimations;
import net.raccoon.will.structura.api.gui.element.*;
import net.raccoon.will.structura.api.gui.hud.BaseHud;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;


public class ExampleHud extends BaseHud {
    private TextElement textElement;
    private TextElement textElement2;
    private BasicElement basicElement;
    private ItemElement itemElement;
    private ProgressElement progressElement;
    private AnimatedElement animatedElement;
    public ExampleHud() {
        super("example_hud");
    }

    @Override
    protected void init() {
        basicElement = addElement(BasicElement.builder("basic_element")
                .texture(Structura.texLoc("gui/basic_element.png"))
                .textureSize(128, 128)
                .size(128, 128)
                .anchor(Anchor.BOTTOM_RIGHT)
                .elementAnchor(ElementAnchor.BOTTOM_RIGHT)
                .offset(8, 8)
                .scale(1)
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

        textElement2 = addElement(TextElement.builder("mod_element")
                .text(Component.literal("This is a text element"))
                .color(125, 125, 125) //use either rgb, hex or argb (if you use hex, make sure to include alpha in the color)
                .shadow(true)
                .offset(8, -36)
                .scale(1.25F)
                .build());

        animatedElement = addElement(AnimatedElement.builder("animated_element")
                .texture(Structura.texLoc("gui/tadpole_bucket.png"))
                .frameSize(16, 16)
                .frameCount(12)
                .fps(12)
                .size(16, 16)
                .scale(2) //initial scale * number
                .offset(0, -8)
                .anchor(Anchor.CENTER)
                .elementAnchor(ElementAnchor.TOP_CENTER)
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

//        for (AbstractElement element : this.getElements()) {
//            new Actions().scaling(0.02F, element);
//        };

        animation().scaling(textElement, 2, 10);
        animation().scaling(textElement2, 2, 10);
        animation().scaling(animatedElement, 2, 5);

        if (basicElement.isHovered()) {
            animation().fadeIn(basicElement, 10);
        } else {
            animation().fadeOut(basicElement, 10);
        }

    }

    protected void whatBlock(Player player) {
        textElement.setVisible(true);
        textElement2.setVisible(true);
        itemElement.setVisible(true);

        Hitscan.HitscanResult result = Hitscan.performHitscan(player, 4);
        if (result.type() == Hitscan.HitscanResult.HitType.BLOCK) {
            BlockHitResult hit = result.blockHit();

            BlockPos pos = hit.getBlockPos();
            BlockState state = player.level().getBlockState(pos);
            Block block = state.getBlock();
            String modId = BuiltInRegistries.BLOCK.getKey(block).getNamespace();
            String modName = ModList.get().getModContainerById(modId).get().getModInfo().getDisplayName();

            textElement.setText(Component.literal(block.getName().getString()));
            textElement2.setText(Component.literal(modName));
            itemElement.setItem(state.getBlock().asItem());

        }
        else {
            textElement.setVisible(false);
            textElement2.setVisible(false);
            itemElement.setVisible(false);
        }
    }


}
