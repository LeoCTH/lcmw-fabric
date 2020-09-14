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

public class DeagleItem extends WeaponItem {

    public DeagleItem() {
        super(new Settings().maxCount(1), MWWeapons.DEAGLE, MWSounds.DEAGLE_FIRE, MWSounds.DEAGLE_RELOAD);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        TextUtil.appendDescTooltips(tooltip, this, 4);
        TextUtil.appendWittyTooltips(tooltip, this, 1);
    }

}
