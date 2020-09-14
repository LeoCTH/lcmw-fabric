package com.leocth.lcmw.fabric.common.network.c2s;

import com.leocth.lcmw.fabric.api.Weapon;
import com.leocth.lcmw.fabric.common.item.WeaponItem;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

public class C2SSwitchFireModePacketConsumer implements PacketConsumer {
    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {
        PlayerEntity player = context.getPlayer();
        context.getTaskQueue().execute(() -> {
            ItemStack stack = player.getMainHandStack();
            Item item = stack.getItem();
            //TODO interfacify
            if (item instanceof WeaponItem) {
                WeaponItem gun = (WeaponItem) item;
                Weapon weapon = gun.getWeapon(stack);
                weapon.toggleFireMode();
                weapon.toTag(stack.getOrCreateTag());
                gun.playSwitchFireModeSound(player.world, player, stack, weapon);
            }
        });
    }
}
