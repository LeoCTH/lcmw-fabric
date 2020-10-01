package com.leocth.lcmw.fabric.client.model.deserialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.leocth.lcmw.fabric.client.model.animation.AnimatedElement;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;

public class AnimatedElementDeserializer implements JsonDeserializer<AnimatedElement> {

    @Override
    public AnimatedElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!JsonHelper.isString(json)) {
            /// blah
        }
        return null;
    }
}
