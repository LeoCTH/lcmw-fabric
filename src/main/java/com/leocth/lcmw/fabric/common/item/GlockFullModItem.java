package com.leocth.lcmw.fabric.common.item;

import com.leocth.lcmw.fabric.common.util.MWSounds;
import com.leocth.lcmw.fabric.common.util.TextUtil;
import com.leocth.lcmw.fabric.common.weapon.MWWeapons;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlockFullModItem extends WeaponItem {

    public GlockFullModItem() {
        super(new Settings().maxCount(1), MWWeapons.GLOCK_FULL_MOD, MWSounds.GLOCK_FIRE, MWSounds.GLOCK_RELOAD);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        TextUtil.appendDescTooltips(tooltip, this, 3);
        TextUtil.appendWittyTooltips(tooltip, this, 1);
    }
}
