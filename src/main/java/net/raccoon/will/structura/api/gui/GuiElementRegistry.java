package net.raccoon.will.structura.api.gui;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.structura.Structura;
import net.raccoon.will.structura.api.gui.elements.GuiElement;
public class GuiElementRegistry {
    public static final DeferredRegister<GuiElement> RECIPES = DeferredRegister.create(Structura.GUI_ELEMENT_REGISTRY, Structura.MODID);
}
