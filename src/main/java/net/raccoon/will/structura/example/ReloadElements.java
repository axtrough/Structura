package net.raccoon.will.structura.example;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.raccoon.will.structura.client.gui.HudManager;

public class ReloadElements implements ResourceManagerReloadListener {

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        HudManager.rebuild();
    }
}
