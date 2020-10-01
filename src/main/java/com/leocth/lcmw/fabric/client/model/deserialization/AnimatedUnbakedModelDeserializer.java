package com.leocth.lcmw.fabric.client.model.deserialization;

import com.google.gson.*;
import com.leocth.lcmw.fabric.client.model.AnimatedUnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;

public class AnimatedUnbakedModelDeserializer implements JsonDeserializer<AnimatedUnbakedModel> {

    @Override
    public AnimatedUnbakedModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (JsonHelper.hasPrimitive(jsonObject, "animated_model_format")) {
            int version = JsonHelper.getInt(jsonObject, "animated_model_format");
            if (version > 0) { // TODO

            }
        }
        // fallback; not an animated model
        return context.deserialize(json, JsonUnbakedModel.class);
    }

}
