package com.leocth.lcmw.fabric.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface Reloadable {
    void reload(World world, PlayerEntity user, ItemStack stack, Hand hand);
}
