package com.leocth.lcmw.fabric.common.util;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class MWSounds {

    public static SoundEvent SELECT_FIRE;
    public static SoundEvent DEAGLE_FIRE;
    public static SoundEvent DEAGLE_RELOAD;
    public static SoundEvent GLOCK_FIRE;
    public static SoundEvent GLOCK_RELOAD;

    private MWSounds() { }

    public static void register() {
        //TODO
        SELECT_FIRE     = registerSound("item.glock.selectfire");
        DEAGLE_FIRE     = registerSound("item.deagle.fire");
        DEAGLE_RELOAD   = registerSound("item.deagle.reload");
        GLOCK_FIRE      = registerSound("item.glock.fire");
        GLOCK_RELOAD    = registerSound("item.glock.reload");
    }

    private static SoundEvent registerSound(String id) {
        Identifier identifier = new Identifier(LCMWFabric.MODID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

}
