package net.raccoon.will.structura.example;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.raccoon.will.structura.api.gui.hud.BaseHud;

public class ExampleHud extends BaseHud {

    @Override
    protected void buildElements() {
    }

    @Override
    public void update(Player player) {
        boolean standingOn = BaseHud.isStandingOnBlock(player, Blocks.DIAMOND_BLOCK);

        if(standingOn) {
            player.displayClientMessage(Component.literal("you are standing on a diamond block"), true);
        }
    }
}
