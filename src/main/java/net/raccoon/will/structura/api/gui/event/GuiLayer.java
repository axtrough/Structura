package net.raccoon.will.structura.api.gui.event;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.client.gui.GuiManager;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public final class GuiLayer {

    private GuiLayer() {}

    @SubscribeEvent
    public static void onRender(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        Window window = mc.getWindow();
        GuiGraphics graphics = event.getGuiGraphics();

        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();

        GuiManager.updateAll();
        GuiManager.render(graphics, screenWidth, screenHeight);
    }
}

