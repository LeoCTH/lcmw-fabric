package com.leocth.lcmw.fabric.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.leocth.lcmw.fabric.common.ammo.FixedAmmoBank;
import com.leocth.lcmw.fabric.common.util.FireMode;
import com.leocth.lcmw.fabric.common.util.physics.hitscan.HitscanArgs;
import com.leocth.lcmw.fabric.common.fire.SimpleHitscanFireManager;
import net.minecraft.nbt.CompoundTag;

import java.util.Arrays;
import java.util.List;

public class WeaponType {

    final AmmoBank ammoBank;
    final FireManager fireManager;
    final List<FireMode> fireModes;

    protected WeaponType(AmmoBank ammoBank, FireManager fireManager, List<FireMode> fireModes) {
        this.ammoBank = ammoBank;
        this.fireManager = fireManager;
        this.fireModes = ImmutableList.copyOf(fireModes);
    }

    public Weapon construct(CompoundTag tag) {
        return new Weapon(this, tag);
    }

    public static class Builder {

        AmmoBank ammoBank;
        FireManager fireManager;
        List<FireMode> fireModes = Lists.newArrayList();

        public Builder ammoBank(AmmoBank bank) {
            this.ammoBank = bank;
            return this;
        }

        public Builder fixedAmmoBank(int current, int capacity, int reserve, int reloadDuration) {
            ammoBank = new FixedAmmoBank(current, capacity, reserve, reloadDuration);
            return this;
        }

        public Builder fixedAmmoBank(int capacity, int reserve, int reloadDuration) {
            return this.fixedAmmoBank(capacity, capacity, reserve, reloadDuration);
        }

        public Builder fireManager(FireManager fireManager) {
            this.fireManager = fireManager;
            return this;
        }

        public Builder simpleHitscanFireManager(float dps, HitscanArgs hitscanArgs) {
            this.fireManager = new SimpleHitscanFireManager(dps, hitscanArgs);
            return this;
        }

        public Builder fireModes(FireMode... fireModes) {
            this.fireModes.addAll(Arrays.asList(fireModes));
            return this;
        }

        public WeaponType build() {
            return new WeaponType(ammoBank, fireManager, fireModes);
        }
    }
}
