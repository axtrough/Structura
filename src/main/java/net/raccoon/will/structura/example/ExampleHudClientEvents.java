package net.raccoon.will.structura.example;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.raccoon.will.structura.Structura;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public class ExampleHudClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ExampleHudRegistry.register();
    }
}
