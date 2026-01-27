package net.raccoon.will.structura.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.server.command.EnumArgument;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import net.raccoon.will.structura.api.gui.layout.Anchor;
import net.raccoon.will.structura.client.gui.GuiManager;

public class AnchorCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("structura")
                        .then(
                                Commands.literal("element")
                                        .then(
                                                Commands.argument("id", StringArgumentType.word())

                                                        // /structura element <id> anchor <ANCHOR>
                                                        .then(Commands.literal("anchor")
                                                                .then(Commands.argument("anchor",
                                                                                EnumArgument.enumArgument(Anchor.class))
                                                                        .executes(ctx -> {
                                                                            String id = StringArgumentType.getString(ctx, "id");
                                                                            Anchor a = ctx.getArgument(
                                                                                    "anchor", Anchor.class);

                                                                            GuiElement element = GuiManager.get(id);
                                                                            if (element == null) {
                                                                                ctx.getSource().sendFailure(
                                                                                        Component.literal("Unknown element: " + id));
                                                                                return 0;
                                                                            }

                                                                            element.setAnchor(a);
                                                                            ctx.getSource().sendSuccess(
                                                                                    () -> Component.literal("Set anchor of " + id + " to " + a),
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
};
