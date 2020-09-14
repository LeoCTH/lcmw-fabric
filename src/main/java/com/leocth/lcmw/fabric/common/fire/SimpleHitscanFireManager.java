package com.leocth.lcmw.fabric.common.fire;

import com.leocth.lcmw.fabric.api.FireManager;
import com.leocth.lcmw.fabric.common.util.physics.hitscan.HitscanArgs;
import com.leocth.lcmw.fabric.common.util.physics.hitscan.PhysicsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * A <em>very</em> simple FireManager implementation, with hitscan physics and a fixed damage.
 *
 * @author leocth
 */
public class SimpleHitscanFireManager implements FireManager {

    private final float dps;
    private final HitscanArgs hitscanArgs;

    public SimpleHitscanFireManager(float dps, HitscanArgs hitscanArgs) {
        this.dps = dps;
        this.hitscanArgs = hitscanArgs;
    }

    @Override
    public void fireSingle(World world, Entity user, ItemStack stack) {
        PhysicsHelper.calcHitscan(world, user, hitscanArgs, entity -> onDamage(entity, user, stack));
    }

    @Override
    public float getDamagePerShot(ItemStack stack) {
        return dps;
    }
}
