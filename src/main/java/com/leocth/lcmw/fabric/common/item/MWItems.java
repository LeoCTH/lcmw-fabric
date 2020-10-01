package com.leocth.lcmw.fabric.common.item;

import com.leocth.lcmw.fabric.api.Weapon;
import com.leocth.lcmw.fabric.api.WeaponType;
import com.leocth.lcmw.fabric.common.LCMWFabric;
import com.leocth.lcmw.fabric.common.util.MWSounds;
import com.leocth.lcmw.fabric.common.util.TooltipBuilder;
import com.leocth.lcmw.fabric.common.weapon.MWWeapons;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.UnaryOperator;

public final class MWItems {
    public static WeaponItem DEAGLE;
    public static WeaponItem GLOCK_FULL_MOD;

    private static final Item.Settings DEFAULT_SETTINGS = new Item.Settings().maxCount(1);

    private MWItems() {}

    public static void register() {
        DEAGLE = registerWeaponItem(
                "deagle",
                DEFAULT_SETTINGS,
                MWWeapons.DEAGLE,
                MWSounds.DEAGLE_FIRE,
                MWSounds.DEAGLE_RELOAD,
                b -> b.desc(4).witty(1)
        );
        GLOCK_FULL_MOD = registerWeaponItem(
                "glock_full_mod",
                DEFAULT_SETTINGS,
                MWWeapons.GLOCK_FULL_MOD,
                MWSounds.GLOCK_FIRE,
                MWSounds.GLOCK_RELOAD,
                b -> b.desc(3).witty(1)
        );
    }

    private static Item registerItem(Item item, String id) {
        return Registry.register(Registry.ITEM, new Identifier(LCMWFabric.MODID, id), item);
    }

    private static WeaponItem registerWeaponItem(
            String id,
            Item.Settings settings,
            WeaponType type,
            SoundEvent fire,
            SoundEvent reload,
            UnaryOperator<TooltipBuilder> tooltip) {
        TooltipBuilder builder = new TooltipBuilder(id);
        builder = tooltip.apply(builder);
        WeaponItem newItem = new WeaponItem(settings,type, fire, reload, builder);
        return Registry.register(Registry.ITEM, new Identifier(LCMWFabric.MODID, id), newItem);
    }

}
