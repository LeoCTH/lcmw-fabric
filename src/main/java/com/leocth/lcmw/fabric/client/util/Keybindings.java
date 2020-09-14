package com.leocth.lcmw.fabric.client.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class Keybindings {

    public static final KeyBinding RELOAD_KEY = KeyBindingHelper.registerKeyBinding(
                    new KeyBinding(
                            "key.lcmw.reload",
                            InputUtil.Type.KEYSYM,
                            GLFW.GLFW_KEY_R,
                            "key.lcmw.action"));

    public static final KeyBinding SWITCH_FIREMODE_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.lcmw.switch_firemode",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    "key.lcmw.action"));

    private Keybindings() {}


}
