package com.leocth.lcmw.fabric.common.network.s2c;

import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;

public class S2CStopSoundPacketConsumer implements PacketConsumer {

    @Override
    public void accept(PacketContext context, PacketByteBuf buf) {
        SoundCategory category = null;
        if (buf.readBoolean())
            category = buf.readEnumConstant(SoundCategory.class);
        Identifier id = buf.readIdentifier();
        SoundCategory finalCategory = category; // lambda satisfaction (tm)
        context.getTaskQueue().execute(() -> MinecraftClient.getInstance().getSoundManager().stopSounds(id, finalCategory));
    }

}
