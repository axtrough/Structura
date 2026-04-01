package net.raccoon.will.structura;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.raccoon.will.structura.commands.ListElements;
import net.raccoon.will.structura.example.ExampleHud;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ListElements.register(event.getDispatcher());
    }
}
