package com.leocth.lcmw.fabric.common.util.damage;

import com.leocth.lcmw.fabric.common.util.TextUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

public class LethalWeaponryDamageSource extends EntityDamageSource implements CustomTimeUntilRegen {

    @Nullable
    private final Entity culprit;
    private final ItemStack weapon;

    public LethalWeaponryDamageSource(@Nullable Entity culprit, ItemStack weapon) {
        super("lethalweaponry", culprit);
        this.culprit = culprit;
        this.weapon = weapon;
    }
    public LethalWeaponryDamageSource(@Nullable Entity culprit) {
        this(culprit, ItemStack.EMPTY);
    }

    @Override
    public Text getDeathMessage(LivingEntity victim) {
        if (culprit == null) {
            return new TranslatableText(
                    TextUtil.composeString("death", name, "anon"),
                    victim.getDisplayName()
            );
        }
        else if (!weapon.isEmpty()) {
            return new TranslatableText(
                    TextUtil.composeString("death", name, "item"),
                    victim.getDisplayName(),
                    culprit.getDisplayName(),
                    weapon.toHoverableText());
        }
        else {
            return new TranslatableText(
                    TextUtil.composeString("death", name),
                    victim.getDisplayName(),
                    culprit.getDisplayName());
        }
    }

    @Override
    public int getTimeUntilRegen() {
        return 0;
    }
}
