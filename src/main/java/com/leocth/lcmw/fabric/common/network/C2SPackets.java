package com.leocth.lcmw.fabric.common.network;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import com.leocth.lcmw.fabric.common.network.c2s.C2SFirePacketConsumer;
import com.leocth.lcmw.fabric.common.network.c2s.C2SReloadPacketConsumer;
import com.leocth.lcmw.fabric.common.network.c2s.C2SSwitchFireModePacketConsumer;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public final class C2SPackets {
    public static final Identifier FIRE_PACKET_ID = new Identifier(LCMWFabric.MODID, "c2s/fire");
    public static final Identifier RELOAD_PACKET_ID = new Identifier(LCMWFabric.MODID, "c2s/reload");
    public static final Identifier SWITCH_FIREMODE_PACKET_ID = new Identifier(LCMWFabric.MODID, "c2s/switch_firemode");

    private C2SPackets() {}

    public static void register() {
        ServerSidePacketRegistry.INSTANCE.register(FIRE_PACKET_ID, new C2SFirePacketConsumer());
        ServerSidePacketRegistry.INSTANCE.register(RELOAD_PACKET_ID, new C2SReloadPacketConsumer());
        ServerSidePacketRegistry.INSTANCE.register(SWITCH_FIREMODE_PACKET_ID, new C2SSwitchFireModePacketConsumer());
    }

    @Environment(EnvType.CLIENT)
    public static void sendFirePacket(boolean mouseHeldDown) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(mouseHeldDown);
        ClientSidePacketRegistry.INSTANCE.sendToServer(FIRE_PACKET_ID, buf);
    }

    @Environment(EnvType.CLIENT)
    public static void sendReloadPacket() {
        ClientSidePacketRegistry.INSTANCE.sendToServer(RELOAD_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
    }

    @Environment(EnvType.CLIENT)
    public static void sendSwitchFireModePacket() {
        ClientSidePacketRegistry.INSTANCE.sendToServer(SWITCH_FIREMODE_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
    }
}
