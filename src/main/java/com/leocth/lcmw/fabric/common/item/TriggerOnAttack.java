package com.leocth.lcmw.fabric.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@FunctionalInterface
public interface TriggerOnAttack {
    void attack(World world, PlayerEntity player, Hand hand, ItemStack stack, boolean keyHeldDown);
}
