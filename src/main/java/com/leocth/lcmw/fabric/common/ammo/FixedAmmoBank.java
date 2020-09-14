package com.leocth.lcmw.fabric.common.ammo;

import com.leocth.lcmw.fabric.api.AmmoBank;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.CompoundTag;

/**
 * A simple-ish implementation of an {@code AmmoBank}, resembling the fixed-capacity, fixed-reserve
 * ammo bank found in a lot of shooters, such as Counter-Strike, Call of Duty* and others.<br/>
 * * well technically you can pick up more ammo. /shrug
 *
 * @author leocth
 */
public class FixedAmmoBank extends AmmoBank {

    protected int curAmmo;
    protected final int maxAmmo;
    protected int reserveAmmo;
    protected final int reloadDuration;

    /**
     * Creates a {@code FixedAmmoBank} with default values. Use {@code deserialize} to restore saved data.
     * @param curAmmo rounds currently loaded
     * @param maxAmmo max capacity of rounds
     * @param reserveAmmo reserve rounds for reloading
     * @param reloadDuration the time in ticks to complete a reload
     */
    public FixedAmmoBank(int curAmmo, int maxAmmo, int reserveAmmo, int reloadDuration) {
        this.curAmmo = curAmmo;
        this.maxAmmo = maxAmmo;
        this.reserveAmmo = reserveAmmo;
        this.reloadDuration = reloadDuration;
    }

    @Override public int getAmmo() { return curAmmo; }
    @Override protected void setAmmo(int ammo) {
        this.curAmmo = ammo;
    }
    @Override public int getMaxAmmo() { return maxAmmo; }
    public int getReserveAmmo() { return reserveAmmo; }
    @Override public int getReloadDuration() { return reloadDuration; }

    @Override
    public boolean canStartReload() {
        return super.canStartReload() && reserveAmmo > 0;
    }

    @Override
    protected void reload() {
        int amount = maxAmmo - curAmmo;
        if (amount >= reserveAmmo) {
            amount = reserveAmmo;
        }
        curAmmo += amount;
        reserveAmmo -= amount;
        reloadTicks = -1;
    }

    @Override
    public boolean refill(Inventory inv) {
        return false;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        CompoundTag sub = tag.getCompound("FxAmmoBank");
        if (sub.contains("Ammo", 3))
            this.curAmmo = sub.getInt("Ammo");
        if (sub.contains("ReserveAmmo", 3))
            this.reserveAmmo = sub.getInt("ReserveAmmo");
        if (sub.contains("ReloadTicks", 3))
            this.reloadTicks = sub.getInt("ReloadTicks");
    }

    @Override
    public void serialize(CompoundTag tag) {
        CompoundTag sub = tag.getCompound("FxAmmoBank");
        sub.putInt("Ammo", curAmmo);
        sub.putInt("ReserveAmmo", reserveAmmo);
        sub.putInt("ReloadTicks", reloadTicks);
        tag.put("FxAmmoBank", sub);
    }

    @Override
    public AmmoBank copy() {
        return new FixedAmmoBank(this.curAmmo, this.maxAmmo, this.reserveAmmo, this.reloadDuration);
    }

}
