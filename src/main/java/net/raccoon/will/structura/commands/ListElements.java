package net.raccoon.will.structura.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.raccoon.will.structura.api.gui.element.AbstractElement;
import net.raccoon.will.structura.api.gui.hud.BaseHud;
import net.raccoon.will.structura.client.gui.HudManager;

import java.util.List;

public class ListElements {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("structura")
                        .then(
                                Commands.literal("elements")
                                        .executes(ctx -> listAllElements(ctx.getSource()))
                        )
        );
    }

    private static int listAllElements(CommandSourceStack source) {
        List<BaseHud> allHuds = HudManager.getAllHuds();

        if (allHuds.isEmpty()) {
            source.sendSuccess(
                    () -> Component.literal("no huds are registered").withStyle(ChatFormatting.RED),
                    false
            );
            return 0;
        }

        int totalElements = 0;

        for (BaseHud hud : allHuds) {
            List<AbstractElement> elements = hud.getElements();

            source.sendSuccess(
                    () -> Component.literal("HUD: " + hud.getId())
                            .withStyle(ChatFormatting.GOLD),
                    false
            );

            if (elements.isEmpty()) {
                source.sendSuccess(
                        () -> Component.literal("  (no elements)").withStyle(ChatFormatting.GRAY),
                        false
                );
            } else {
                for (AbstractElement element : elements) {
                    source.sendSuccess(
                            () -> Component.literal(" - " + element.getId())
                                    .withStyle(ChatFormatting.GRAY),
                            false
                    );
                    totalElements++;
                }
            }
        }

        return totalElements;
    }
}
