package net.raccoon.will.structura.api.gui.event;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.client.gui.HudManager;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public final class RenderLayer {

    @SubscribeEvent
    public static void onRender(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (mc.player == null) return;

        DeltaTracker delta = event.getPartialTick();
        float deltaTime = delta.getRealtimeDeltaTicks() / 20.0f;

        Window window = mc.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        GuiGraphicsExtractor graphics = event.getGuiGraphics();

        double guiScale = window.getGuiScale();
        int mouseX = (int)(mc.mouseHandler.xpos() / guiScale);
        int mouseY = (int)(mc.mouseHandler.ypos() / guiScale);


        HudManager.update(player, deltaTime);
        HudManager.render(graphics, mouseX, mouseY, screenWidth, screenHeight);
    }
}

