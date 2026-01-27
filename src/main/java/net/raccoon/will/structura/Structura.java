package net.raccoon.will.structura;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Structura.MODID)
public class Structura {
    public static final String MODID = "structura";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier resLoc(String name) {
        return Identifier.fromNamespaceAndPath(Structura.MODID, name);
    }

    public static Identifier texLoc(String name) {
        return Identifier.fromNamespaceAndPath(Structura.MODID, "textures/" + name);
    }

    public Structura(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


}
