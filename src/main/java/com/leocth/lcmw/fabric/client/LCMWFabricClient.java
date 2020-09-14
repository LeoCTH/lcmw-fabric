package com.leocth.lcmw.fabric.client;

import com.leocth.lcmw.fabric.common.network.S2CPackets;
import com.leocth.lcmw.fabric.common.util.MWSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LCMWFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientEvents.register();
        S2CPackets.register();
    }

}
