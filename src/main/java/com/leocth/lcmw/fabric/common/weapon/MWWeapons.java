package com.leocth.lcmw.fabric.common.weapon;

import com.leocth.lcmw.fabric.api.WeaponType;
import com.leocth.lcmw.fabric.common.util.FireMode;
import com.leocth.lcmw.fabric.common.util.physics.hitscan.HitscanArgs;

public final class MWWeapons {

    // jsonify? :jsthonk:

    public static final WeaponType DEAGLE = new WeaponType.Builder()
            .fixedAmmoBank(7, 35, 44)
            .simpleHitscanFireManager(11.3f, HitscanArgs.DEFAULT)
            .fireModes(FireMode.SAFE, FireMode.SINGLE)
            .build();

    public static final WeaponType GLOCK_FULL_MOD = new WeaponType.Builder()
            .fixedAmmoBank(50, 300, 40)
            .simpleHitscanFireManager(5.7f, HitscanArgs.DEFAULT)
            .fireModes(FireMode.SAFE, FireMode.SINGLE, FireMode.burst(3), FireMode.AUTO)
            .build();

    private MWWeapons() {}
}
