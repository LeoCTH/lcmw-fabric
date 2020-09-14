package com.leocth.lcmw.fabric.common.item;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class MWItems {
    public static final DeagleItem          DEAGLE = new DeagleItem();
    public static final GlockFullModItem    GLOCK_FULL_MOD = new GlockFullModItem();

    private MWItems() {}

    public static void register() {
        registerItem(DEAGLE, "deagle");
        registerItem(GLOCK_FULL_MOD, "glock_full_mod");
    }

    private static void registerItem(Item item, String id) {
        Registry.register(Registry.ITEM, new Identifier(LCMWFabric.MODID, id), item);
    }
}
