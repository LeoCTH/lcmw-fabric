package com.leocth.lcmw.fabric.common.mixin;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SoundEvent.class)
public interface SoundEventAccessor {
    // why is it only available in the client????
    @Accessor Identifier getId();
}
