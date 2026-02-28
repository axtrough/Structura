package net.raccoon.will.structura.api.gui.event;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.client.gui.HudManager;

@EventBusSubscriber(modid = Structura.MODID, value = Dist.CLIENT)
public final class RenderLayer {

    private static long lastFrameTime = System.nanoTime();

    private RenderLayer() {}

    @SubscribeEvent
    public static void onRender(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (mc.player == null) return;

        Window window = mc.getWindow();
        GuiGraphics graphics = event.getGuiGraphics();

        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();

        long now = System.nanoTime();
        float deltaSeconds = (now - lastFrameTime) / 1_000_000_000f;
        lastFrameTime = now;

        deltaSeconds = Math.min(deltaSeconds, 0.05f);

        HudManager.update(player);
        HudManager.render(graphics, screenWidth, screenHeight);
    }
}

