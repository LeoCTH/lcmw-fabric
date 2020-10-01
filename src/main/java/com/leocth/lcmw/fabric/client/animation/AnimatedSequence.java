package com.leocth.lcmw.fabric.client.animation;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

import java.util.List;

public class AnimatedSequence {

    private float current;
    private Transformation curTrans;
    private Transformation nextTrans;
    public final Int2ObjectOpenHashMap<KeyFrame> frames;
    public final int duration;

    public AnimatedSequence(List<KeyFrame> frames, int duration) {
        this.frames = new Int2ObjectOpenHashMap<>();
        for (KeyFrame frame : frames) {
            this.frames.putIfAbsent(frame.tick, frame);
        }
        this.duration = duration;
    }

    public void update() {
        if (this.current > this.duration)
            this.stop();
        this.curTrans = this.frames.get(MathHelper.floor(this.current)).trans;
        this.nextTrans = this.frames.get(MathHelper.ceil(this.current)).trans;
    }

    public void stop() {
        this.current = 0f;
    }

    public void play(boolean mirrorVertically, MatrixStack matrices, float delta) {
        this.current += delta;
        this.update();
        int i = mirrorVertically ? -1 : 1;
        Vector3f translation = this.curTrans.translation;
        Vector3f rotation = this.curTrans.rotation;
        Vector3f scale = this.curTrans.scale;
        matrices.translate(i * this.curTrans.translation.getX(), (double)this.translation.getY(), (double)this.translation.getZ());
        matrices.multiply(new Quaternion(f, g, h, true));
        matrices.scale(this.scale.getX(), this.scale.getY(), this.scale.getZ());
    }
}
