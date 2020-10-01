package com.leocth.lcmw.fabric.client.model.animation;

import net.minecraft.client.render.model.json.ModelElement;
import net.minecraft.client.render.model.json.ModelElementFace;
import net.minecraft.client.render.model.json.ModelRotation;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AnimatedElement extends ModelElement {
    public final Identifier animationPartId;
    public AnimatedElement(Vector3f from, Vector3f to, Map<Direction, ModelElementFace> faces, @Nullable ModelRotation rotation, boolean shade, Identifier animationPartId) {
        super(from, to, faces, rotation, shade);
        this.animationPartId = animationPartId;
    }
}
