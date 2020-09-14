package com.leocth.lcmw.fabric.common.network;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import com.leocth.lcmw.fabric.common.mixin.SoundEventAccessor;
import com.leocth.lcmw.fabric.common.network.c2s.C2SFirePacketConsumer;
import com.leocth.lcmw.fabric.common.network.s2c.S2CStopSoundPacketConsumer;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public final class S2CPackets {
    public static final Identifier STOP_SOUND_ID = new Identifier(LCMWFabric.MODID, "c2s/stop_sound");

    private S2CPackets() {}

    @Environment(EnvType.CLIENT)
    public static void register() {
        ClientSidePacketRegistry.INSTANCE.register(STOP_SOUND_ID, new S2CStopSoundPacketConsumer());
    }

    public static void sendStopSoundPacket(World world, SoundEvent sound, @Nullable SoundCategory category) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        if (category != null) {
            buf.writeBoolean(true);
            buf.writeEnumConstant(category);
        }
        else {
            buf.writeBoolean(false);
        }
        buf.writeIdentifier(((SoundEventAccessor) sound).getId());
        if (world.getServer() != null)
            PlayerStream.all(world.getServer()).forEach(player -> ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, STOP_SOUND_ID, buf));
    }
}
