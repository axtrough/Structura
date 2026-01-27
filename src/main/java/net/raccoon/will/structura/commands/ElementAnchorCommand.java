package net.raccoon.will.structura.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.server.command.EnumArgument;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;
import net.raccoon.will.structura.client.gui.GuiManager;

public class ElementAnchorCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("structura")
                        .then(
                                Commands.literal("element")
                                        .then(
                                                Commands.argument("id", StringArgumentType.word())
                                                        .then(Commands.literal("elementAnchor")
                                                                .then(Commands.argument("elementAnchor",
                                                                                EnumArgument.enumArgument(ElementAnchor.class))
                                                                        .executes(ctx -> {
                                                                            String id = StringArgumentType.getString(ctx, "id");
                                                                            ElementAnchor ea = ctx.getArgument(
                                                                                    "elementAnchor", ElementAnchor.class);

                                                                            GuiElement element = GuiManager.get(id);
                                                                            if (element == null) {
                                                                                ctx.getSource().sendFailure(
                                                                                        Component.literal("Unknown element: " + id));
                                                                                return 0;
                                                                            }

                                                                            element.setElementAnchor(ea);
                                                                            ctx.getSource().sendSuccess(
                                                                                    () -> Component.literal(
                                                                                            "Set elementAnchor of " + id + " to " + ea),
                                                                                    false
                                                                            );
                                                                            return 1;
                                                                        })
                                                                )
                                                        )
                                        )
                        )
        );
    }
}
