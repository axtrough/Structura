package net.raccoon.will.structura.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.server.command.EnumArgument;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.client.gui.GuiManager;

import java.util.Collection;

public class ListElements {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("structura")
                        .then(
                                Commands.literal("element")
                                        .then(
                                                Commands.literal("list")
                                                        .executes(ctx -> listElements(ctx.getSource()))
                                        )
                        )
        );
    }


    private static int listElements(CommandSourceStack source) {
        Collection<GuiElement> elements = GuiManager.getAll();


            if (elements.isEmpty()) {
                source.sendSuccess(
                        () -> Component.literal("No GUI Elements Registered"),
                        false
                );
                return 0;
            }

            source.sendSuccess(
                    () -> Component.literal("GUI Elements:")
                            .withStyle(ChatFormatting.GOLD),
                    false
            );

            for (GuiElement element : elements) {
                source.sendSuccess(
                        () -> Component.literal(" - " + element.getId())
                                .withStyle(ChatFormatting.GRAY),
                        false
                );
            }

            return elements.size();
        }
}
