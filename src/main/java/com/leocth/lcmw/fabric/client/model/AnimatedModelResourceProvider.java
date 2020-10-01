package com.leocth.lcmw.fabric.client.model;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class AnimatedModelResourceProvider implements ModelResourceProvider {

    private final ResourceManager resourceManager;

    public AnimatedModelResourceProvider(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }


    @Override
    public UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) throws ModelProviderException {
        if (resourceId.getNamespace().equals(LCMWFabric.MODID)) {
            Reader reader = null;
            Resource resource = null;

            //imitate vanilla behavior
            try {
                resource = this.resourceManager.getResource(new Identifier(resourceId.getNamespace(), "models/" + resourceId.getPath() + ".json"));
                reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

                AnimatedUnbakedModel model = AnimatedUnbakedModel.deserialize(reader);
                model.id = resourceId.toString();
                return model;
            }
            catch (IOException e) {
                ModelManager.LOGGER.warn("Unable to load model: '{}'!", resourceId.toString());
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(resource);
            }

        }
        return null;
    }
}
