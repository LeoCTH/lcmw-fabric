package com.leocth.lcmw.fabric.api;

import com.google.common.collect.ImmutableList;
import com.leocth.lcmw.fabric.common.util.FireMode;
import com.leocth.lcmw.fabric.common.util.ReloadProgress;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import java.util.List;

public class Weapon {

    public final WeaponType type;
    public final AmmoBank ammoBank;
    public final FireManager fireManager;
    public final List<FireMode> fireModes;
    protected int fireModeIdx;

    Weapon(WeaponType type, CompoundTag tag) {
        this.type = type;
        this.ammoBank = type.ammoBank.copy();
        this.fireManager = type.fireManager;
        this.fireModes = ImmutableList.copyOf(type.fireModes);
        this.fromTag(tag);
    }

    /**
     * Attempt to fire a round.
     * @param heldDown false if the attack key is pressed, true if the attack key is held down.
     * @return if the action is executed successfully
     */
    public boolean attemptFire(World world, PlayerEntity player, ItemStack stack, boolean heldDown) {
        FireMode mode = this.getFireMode();
        if (mode.heldDown == heldDown) {
            int successfulShots = 0;
            for (int i = 0; i < mode.shots; i++) {
                if (ammoBank.attemptFire()) {
                    fireManager.fireSingle(world, player, stack);
                    ++successfulShots;
                }
            }
            return successfulShots > 0;
        }
        return false;
    }

    public void fromTag(CompoundTag tag) {
        ammoBank.deserialize(tag);
        fireModeIdx = tag.getByte("FireMode");
    }

    public void toTag(CompoundTag tag) {
        ammoBank.serialize(tag);
        tag.putByte("FireMode", (byte) fireModeIdx);
    }

    /**
     * Attempt to reload. (e.g. when pressing the reload key)
     * @return
     * {@code NONE} if failed to reload for some reason (e.g. current ammo >= max ammo)
     * {@code START} if successfully started reloading
     * {@code MID} if already in midst of the reloading process
     */
    public ReloadProgress attemptReload() {
        return ammoBank.attemptReload();
    }

    /**
     * Progress the reload.
     * @return
     * {@code NONE} if not reloading
     * {@code START} if just started reloading (reloadTicks = 0)
     * {@code MID} if in midst of the reloading process
     * {@code FINISH} if finished reloading
     */
    public ReloadProgress progressReload() {
        return ammoBank.tickReload();
    }



    public FireMode getFireMode() {
        try {
            return fireModes.get(fireModeIdx);
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return FireMode.SAFE;
        }
    }

    public FireMode toggleFireMode() {
        try {
            if (!fireModes.isEmpty()) {
                fireModeIdx = Math.floorMod(fireModeIdx + 1, fireModes.size());
                return fireModes.get(fireModeIdx);
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return FireMode.SAFE;
    }
}
