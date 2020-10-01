package com.leocth.lcmw.fabric.common.util;

import net.minecraft.text.Text;

public class FireMode {
    public static final FireMode SAFE;
    public static final FireMode SINGLE;
    public static final FireMode AUTO;

    public final int shots;
    public final boolean heldDown;
    public final Text displayText;

    public FireMode(int shots, boolean heldDown, String id) {
        this.shots = shots;
        this.heldDown = heldDown;
        this.displayText = TextUtil.makeTranslatableTextDefaultedId("firemode", id);
    }

    public static FireMode burst(int shots) {
        return new FireMode(shots, false, "burst");
    }

    static {
        SAFE = new FireMode(0, false, "safe");
        SINGLE = new FireMode(1, false, "single");
        AUTO = new FireMode(1, true, "auto");
    }
}
