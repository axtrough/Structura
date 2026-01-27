package net.raccoon.will.structura.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.server.command.EnumArgument;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.api.gui.layout.ElementAnchor;
import net.raccoon.will.structura.client.gui.GuiManager;

public class OffsetCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("structura")
                        .then(
                                Commands.literal("element")
                                        .then(
                                                Commands.argument("id", StringArgumentType.word())

                                        // /structura element <id> offset <x> <y>
                                        .then(Commands.literal("offset")
                                                .then(Commands.argument("x", IntegerArgumentType.integer())
                                                        .then(Commands.argument("y", IntegerArgumentType.integer())
                                                                .executes(ctx -> {
                                                                    String id = StringArgumentType.getString(ctx, "id");
                                                                    int x = IntegerArgumentType.getInteger(ctx, "x");
                                                                    int y = IntegerArgumentType.getInteger(ctx, "y");

                                                                    GuiElement element = GuiManager.get(id);
                                                                    if (element == null) {
                                                                        ctx.getSource().sendFailure(
                                                                                Component.literal("Unknown element: " + id)
                                                                        );
                                                                        return 0;
                                                                    }

                                                                    element.setOffsetX(x);
                                                                    element.setOffsetY(y);
                                                                    ctx.getSource().sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Set offset of " + id + " to " + x + ", " + y),
                                                                            false
                                                                    );
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                )
                        )
        );
    }
}
