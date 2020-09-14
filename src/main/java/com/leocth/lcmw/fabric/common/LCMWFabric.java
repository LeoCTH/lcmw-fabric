package com.leocth.lcmw.fabric.common;

import com.leocth.lcmw.fabric.common.item.MWItems;
import com.leocth.lcmw.fabric.common.network.C2SPackets;
import com.leocth.lcmw.fabric.common.util.MWSounds;
import net.fabricmc.api.ModInitializer;

public final class LCMWFabric implements ModInitializer {

    public static final String MODID = "lcmw";

    @Override
    public void onInitialize() {
        MWSounds.register();
        MWItems.register();
        C2SPackets.register();
    }
}
