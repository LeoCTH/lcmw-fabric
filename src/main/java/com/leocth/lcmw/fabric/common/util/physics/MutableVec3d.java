package com.leocth.lcmw.fabric.common.util.physics;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.*;

import java.util.EnumSet;

public class MutableVec3d implements Position {
    private double x;
    private double y;
    private double z;

    public MutableVec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MutableVec3d(Vector3f vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableVec3d(Vec3d vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableVec3d(MutableVec3d vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableVec3d reverseSubtract(Vec3d vec) {
        this.x = vec.x - this.x;
        this.y = vec.y - this.y;
        this.z = vec.z - this.z;
        return this;
    }

    public MutableVec3d normalize() {
        double d = MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        if (d > 1.0E-4D) {
            this.x /= d;
            this.y /= d;
            this.z /= d;
        }
        return this;
    }

    public double dotProduct(Vec3d vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }
    public double dotProduct(MutableVec3d vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public MutableVec3d crossProduct(Vec3d vec) {
        this.x = this.y * vec.z - this.z * vec.y;
        this.y = this.z * vec.x - this.x * vec.z;
        this.z = this.x * vec.y - this.y * vec.x;
        return this;
    }
    public MutableVec3d crossProduct(MutableVec3d vec) {
        this.x = this.y * vec.z - this.z * vec.y;
        this.y = this.z * vec.x - this.x * vec.z;
        this.z = this.x * vec.y - this.y * vec.x;
        return this;
    }

    public MutableVec3d subtract(Vec3d vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }
    public MutableVec3d subtract(MutableVec3d vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }
    public MutableVec3d subtract(double x, double y, double z) {
        return this.add(-x, -y, -z);
    }

    public MutableVec3d add(Vec3d vec) {
        return this.add(vec.x, vec.y, vec.z);
    }
    public MutableVec3d add(MutableVec3d vec) {
        return this.add(vec.x, vec.y, vec.z);
    }
    public MutableVec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public boolean isInRange(Position pos, double radius) {
        return this.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()) < radius * radius;
    }

    public double distanceTo(MutableVec3d vec) {
        double d = vec.x - this.x;
        double e = vec.y - this.y;
        double f = vec.z - this.z;
        return MathHelper.sqrt(d * d + e * e + f * f);
    }
    public double distanceTo(Vec3d vec) {
        double d = vec.x - this.x;
        double e = vec.y - this.y;
        double f = vec.z - this.z;
        return MathHelper.sqrt(d * d + e * e + f * f);
    }

    public double squaredDistanceTo(MutableVec3d vec) {
        double d = vec.x - this.x;
        double e = vec.y - this.y;
        double f = vec.z - this.z;
        return d * d + e * e + f * f;
    }
    public double squaredDistanceTo(Vec3d vec) {
        double d = vec.x - this.x;
        double e = vec.y - this.y;
        double f = vec.z - this.z;
        return d * d + e * e + f * f;
    }

    public double squaredDistanceTo(double x, double y, double z) {
        double d = x - this.x;
        double e = y - this.y;
        double f = z - this.z;
        return d * d + e * e + f * f;
    }

    public MutableVec3d multiply(double mult) {
        return this.multiply(mult, mult, mult);
    }

    public MutableVec3d negate() {
        return this.multiply(-1.0D);
    }

    public MutableVec3d multiply(MutableVec3d mult) {
        return this.multiply(mult.x, mult.y, mult.z);
    }
    public MutableVec3d multiply(Vec3d mult) {
        return this.multiply(mult.x, mult.y, mult.z);
    }

    public MutableVec3d multiply(double multX, double multY, double multZ) {
        this.x *= multX;
        this.y *= multY;
        this.z *= multZ;
        return this;
    }

    public double length() {
        return MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof MutableVec3d)) {
            return false;
        } else {
            MutableVec3d vec3d = (MutableVec3d)o;
            if (Double.compare(vec3d.x, this.x) != 0) {
                return false;
            } else if (Double.compare(vec3d.y, this.y) != 0) {
                return false;
            } else {
                return Double.compare(vec3d.z, this.z) == 0;
            }
        }
    }

    public int hashCode() {
        long l = Double.doubleToLongBits(this.x);
        int i = (int)(l ^ l >>> 32);
        l = Double.doubleToLongBits(this.y);
        i = 31 * i + (int)(l ^ l >>> 32);
        l = Double.doubleToLongBits(this.z);
        i = 31 * i + (int)(l ^ l >>> 32);
        return i;
    }

    public String toString() {
        return "Mut(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public MutableVec3d rotateX(float angle) {
        float f = MathHelper.cos(angle);
        float g = MathHelper.sin(angle);
        this.y = this.y * (double)f + this.z * (double)g;
        this.z = this.z * (double)f - this.y * (double)g;
        return this;
    }

    public MutableVec3d rotateY(float angle) {
        float f = MathHelper.cos(angle);
        float g = MathHelper.sin(angle);
        this.x = this.x * (double)f + this.z * (double)g;
        this.z = this.z * (double)f - this.x * (double)g;
        return this;
    }

    public MutableVec3d rotateZ(float angle) {
        float g = MathHelper.cos(angle);
        float h = MathHelper.sin(angle);
        this.x = this.x * (double)g + this.y * (double)h;
        this.y = this.y * (double)g - this.x * (double)h;
        return this;
    }

    public static MutableVec3d fromPolar(Vec2f polar) {
        return fromPolar(polar.x, polar.y);
    }

    public static MutableVec3d fromPolar(float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
        float g = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
        float h = -MathHelper.cos(-pitch * 0.017453292F);
        float i = MathHelper.sin(-pitch * 0.017453292F);
        return new MutableVec3d(g * h, i, f * h);
    }

    public MutableVec3d floorAlongAxes(EnumSet<Direction.Axis> axes) {
        double d = axes.contains(Direction.Axis.X) ? (double)MathHelper.floor(this.x) : this.x;
        double e = axes.contains(Direction.Axis.Y) ? (double)MathHelper.floor(this.y) : this.y;
        double f = axes.contains(Direction.Axis.Z) ? (double)MathHelper.floor(this.z) : this.z;
        return new MutableVec3d(d, e, f);
    }

    public double getComponentAlongAxis(Direction.Axis axis) {
        return axis.choose(this.x, this.y, this.z);
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getZ() {
        return this.z;
    }
}
