package com.leocth.lcmw.fabric.client.animation;

import com.google.gson.*;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;

public class KeyFrame {
    public final int tick;
    public final Transformation trans;

    public KeyFrame(int tick, Transformation trans) {
        this.tick = tick;
        this.trans = trans;
    }

    public static class Deserializer implements JsonDeserializer<KeyFrame> {

        private static final Vector3f DEFAULT_ROTATION = new Vector3f(0.0F, 0.0F, 0.0F);
        private static final Vector3f DEFAULT_TRANSLATION = new Vector3f(0.0F, 0.0F, 0.0F);
        private static final Vector3f DEFAULT_SCALE = new Vector3f(1.0F, 1.0F, 1.0F);

        @Override
        public KeyFrame deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                // uncompressed format, almost the same as Transformation$Deserializer
                JsonObject jsonObject = json.getAsJsonObject();
                Vector3f translation = parseVector3f(jsonObject, "transformation", DEFAULT_TRANSLATION);
                Vector3f rotation = parseVector3f(jsonObject, "rotation", DEFAULT_ROTATION);
                Vector3f scale = parseVector3f(jsonObject, "scale", DEFAULT_SCALE);
                int tick = JsonHelper.getInt(jsonObject, "tick");
                return new KeyFrame(tick, new Transformation(rotation, translation, scale));
            }
            return null;
        }

        // the same as Transformation$Deserializer#parseVector3f
        private Vector3f parseVector3f(JsonObject json, String key, Vector3f fallback) {
            if (!json.has(key)) {
                return fallback;
            } else {
                JsonArray jsonArray = JsonHelper.getArray(json, key);
                if (jsonArray.size() != 3) {
                    throw new JsonParseException("Expected 3 " + key + " values, found: " + jsonArray.size());
                } else {
                    float[] fs = new float[3];

                    for(int i = 0; i < fs.length; ++i) {
                        fs[i] = JsonHelper.asFloat(jsonArray.get(i), key + "[" + i + "]");
                    }

                    return new Vector3f(fs[0], fs[1], fs[2]);
                }
            }
        }
    }
}
