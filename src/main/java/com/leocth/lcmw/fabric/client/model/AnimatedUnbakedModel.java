package com.leocth.lcmw.fabric.client.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leocth.lcmw.fabric.client.model.deserialization.AnimatedUnbakedModelDeserializer;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class AnimatedUnbakedModel extends JsonUnbakedModel {

    public static final Gson GSON =
            (new GsonBuilder())
                    .registerTypeAdapter(AnimatedUnbakedModel.class, new AnimatedUnbakedModelDeserializer())
                    .registerTypeAdapter(JsonUnbakedModel.class, new JsonUnbakedModel.Deserializer()) // fallback
                    .create();


    public AnimatedUnbakedModel(@Nullable Identifier parentId, List<ModelElement> elements, Map<String, Either<SpriteIdentifier, String>> textureMap, boolean ambientOcclusion, @Nullable GuiLight guiLight, ModelTransformation transformations, List<ModelOverride> overrides) {
        super(parentId, elements, textureMap, ambientOcclusion, guiLight, transformations, overrides);
    }

    public static AnimatedUnbakedModel deserialize(Reader input) {
        return JsonHelper.deserialize(GSON, input, AnimatedUnbakedModel.class);
    }

    public static AnimatedUnbakedModel deserialize(String json) {
        return deserialize(new StringReader(json));
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return null;
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
        return null;
    }

    @Override
    public @Nullable BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        return null;
    }
}
