package com.leocth.lcmw.fabric.common.mixin;

import com.leocth.lcmw.fabric.common.util.damage.CustomTimeUntilRegen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyConstant(method = "damage", constant = @Constant(intValue = 20, ordinal = 0))
    public int setTimeUntilRegen(int original, DamageSource source) {
        if (source instanceof CustomTimeUntilRegen) {
            return ((CustomTimeUntilRegen) source).getTimeUntilRegen();
        }
        return original;
    }
}
