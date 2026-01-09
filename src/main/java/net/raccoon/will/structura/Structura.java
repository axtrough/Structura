package net.raccoon.will.structura;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.raccoon.will.structura.api.gui.element.GuiElement;
import org.slf4j.Logger;

@Mod(Structura.MODID)
public class Structura {
    public static final String MODID = "structura";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceKey<Registry<GuiElement>> GUI_KEY = ResourceKey.createRegistryKey(Structura.resLoc("gui_element"));
    public static final Registry<GuiElement> GUI_ELEMENTS = new RegistryBuilder<>(GUI_KEY)
            .sync(true)
            .create();

    public static ResourceLocation resLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(Structura.MODID, name);
    }

    public Structura(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(Structura.GUI_ELEMENTS);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
