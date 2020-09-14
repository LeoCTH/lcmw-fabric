package com.leocth.lcmw.fabric.api;

import com.leocth.lcmw.fabric.common.util.ReloadProgress;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;

/**
 * An object that stores ammunition for weapons.
 *
 * @author leocth
 */
public abstract class AmmoBank {

    protected int reloadTicks;

    public AmmoBank() {
        this.reloadTicks = -1;
    }

    public int getReloadTicks() { return reloadTicks; }

    public abstract int getAmmo();
    protected abstract void setAmmo(int ammo);

    public abstract int getMaxAmmo();
    public abstract int getReloadDuration();

    protected abstract void reload();
    public abstract boolean refill(Inventory inv);

    public abstract void deserialize(CompoundTag tag);
    public abstract void serialize(CompoundTag tag);

    public ReloadProgress tickReload() {
        if (reloadTicks >= 0) {
            return this.progressReload();
        }
        else if (this.getAmmo() <= 0 && this.canStartReload()) {
            this.startReload();
            return ReloadProgress.START;
        }
        else {
            this.stopReload();
            return ReloadProgress.NONE;
        }
    }

    protected ReloadProgress progressReload() {
        ++reloadTicks;
        if (reloadTicks >= this.getReloadDuration()) {
            this.reload();
            return ReloadProgress.FINISH;
        }
        else return ReloadProgress.MID;
    }

    public ReloadProgress attemptReload() {
        if (this.isReloading()) return ReloadProgress.MID;
        if (this.canStartReload()) {
            this.startReload();
            return ReloadProgress.START;
        }
        else {
            this.stopReload();
            return ReloadProgress.NONE;
        }
    }

    public boolean attemptFire() {
        if (!this.isReloading()) {
            if (this.getAmmo() > 0) {
                this.setAmmo(this.getAmmo() - 1);
                return true;
            } else {
                this.startReload();
            }
        }
        return false;
    }

    public abstract AmmoBank copy();

    public void startReload() { reloadTicks = 0;  }
    public void stopReload()  { reloadTicks = -1; }
    public boolean isReloading() { return reloadTicks >= 0; }
    public boolean canStartReload() {
        return this.getAmmo() < this.getMaxAmmo();
    }
    public float getReloadProgress() {
        return MathHelper.clamp(reloadTicks / (float) getReloadDuration(), 0f, 1f);
    }
}
