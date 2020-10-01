package com.leocth.lcmw.fabric.client.model;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelManager {

    public static final Logger LOGGER = LogManager.getLogger("LCMW-ModelManager");

    public static void register() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(AnimatedModelResourceProvider::new);
    }
}
