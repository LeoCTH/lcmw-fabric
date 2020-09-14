package com.leocth.lcmw.fabric.common.util.physics.hitscan;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;

public class HitscanArgs {
    public static final HitscanArgs DEFAULT = new HitscanArgs();

    public Predicate<Entity> entityFilter;
    public Predicate<BlockState> blockCollisionFilter;
    public int maxRange;
    public boolean ignoreShooter;
    public int permeability;

    public HitscanArgs() {
        this.entityFilter = (entity) -> entity instanceof LivingEntity;
        this.blockCollisionFilter = (state) -> state.getBlock() instanceof LeavesBlock;
        this.maxRange = 128;
        this.ignoreShooter = true;
        this.permeability = 0;
    }

    public HitscanArgs entityFilter(Predicate<Entity> entityFilter) {
        this.entityFilter = entityFilter;
        return this;
    }

    public HitscanArgs blockCollisionFilter(Predicate<BlockState> blockCollisionFilter) {
        this.blockCollisionFilter = blockCollisionFilter;
        return this;
    }

    public HitscanArgs ignoreShooter(boolean ignoreShooter) {
        this.ignoreShooter = ignoreShooter;
        return this;
    }

    public HitscanArgs maxRange(int maxRange) {
        this.maxRange = maxRange;
        return this;
    }

    public HitscanArgs permeability(int permeability) {
        this.permeability = permeability;
        return this;
    }
}
