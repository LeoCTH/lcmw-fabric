package com.leocth.lcmw.fabric.common.network.c2s;

import com.leocth.lcmw.fabric.common.item.Reloadable;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;

public class C2SReloadPacketConsumer implements PacketConsumer {

    @Override
    public void accept(PacketContext context, PacketByteBuf buf) {
        PlayerEntity player = context.getPlayer();
        context.getTaskQueue().execute(() -> {
            ItemStack stack = player.getMainHandStack();
            Item item = stack.getItem();
            //TODO offhand support?
            if (item instanceof Reloadable)
                ((Reloadable) item).reload(player.world, player, stack, Hand.MAIN_HAND);
        });
    }

}
