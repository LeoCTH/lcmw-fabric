package com.leocth.lcmw.fabric.api;

import com.leocth.lcmw.fabric.common.util.damage.LethalWeaponryDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FireManager {
    void fireSingle(World world, Entity user, ItemStack stack);
    float getDamagePerShot(ItemStack stack);

    default void onDamage(@NotNull Entity target, @Nullable Entity user, ItemStack stack) {
        target.damage(new LethalWeaponryDamageSource(user, stack), this.getDamagePerShot(stack));
    }
}
