package net.raccoon.will.structura;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.raccoon.will.structura.commands.AnchorCommand;
import net.raccoon.will.structura.commands.ElementAnchorCommand;
import net.raccoon.will.structura.commands.OffsetCommand;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        OffsetCommand.register(event.getDispatcher());
        AnchorCommand.register(event.getDispatcher());
        ElementAnchorCommand.register(event.getDispatcher());
    }
}
