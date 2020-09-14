package com.leocth.lcmw.fabric.common.item;

import com.leocth.lcmw.fabric.api.WeaponType;
import com.leocth.lcmw.fabric.common.ammo.FixedAmmoBank;
import com.leocth.lcmw.fabric.common.network.S2CPackets;
import com.leocth.lcmw.fabric.common.util.*;
import com.leocth.lcmw.fabric.api.Weapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

public class WeaponItem extends Item implements TriggerOnAttack, Reloadable, IgnoreNbt {

    protected final WeaponType weaponType;
    protected final SoundEvent fireSound;
    protected final SoundEvent reloadSound;

    public WeaponItem(Settings settings, WeaponType weapon, SoundEvent fireSound, SoundEvent reloadSound) {
        super(settings);
        this.weaponType = weapon;
        this.fireSound = fireSound;
        this.reloadSound = reloadSound;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
    public SoundEvent getFireSound(Weapon weapon) {
        return fireSound;
    }
    public SoundEvent getReloadSound(Weapon weapon) {
        return reloadSound;
    }

    /* [ Actions */

    @Override
    public void attack(World world, PlayerEntity player, Hand hand, ItemStack stack, boolean keyHeldDown) {
        if (!world.isClient) {
            Weapon weapon = this.getWeapon(stack);
            if (weapon.attemptFire(world, player, stack, keyHeldDown)) {
                this.playFireSound(world, player, stack, weapon);
            }
            this.applyChanges(weapon, stack);
        }
    }

    @Override
    public void reload(World world, PlayerEntity user, ItemStack stack, Hand hand) {
        if (!world.isClient) {
            Weapon weapon = this.getWeapon(stack);
            if (weapon.attemptReload() == ReloadProgress.START) {
                this.playReloadSound(world, user, stack, weapon);
            }
            this.applyChanges(weapon, stack);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        Weapon weapon = this.getWeapon(stack);
        if (!world.isClient) {
            if (selected) {
                if (weapon.progressReload() == ReloadProgress.START) {
                    this.playReloadSound(world, entity, stack, weapon);
                }
            }
            else {
                if (weapon.ammoBank.isReloading()) {
                    weapon.ammoBank.stopReload();
                    this.stopReloadSound(world, stack, weapon);
                }
            }
            this.applyChanges(weapon, stack);
        }
        else {
            if (selected && entity instanceof PlayerEntity) {
                this.displayMessage(world, (PlayerEntity) entity, stack, weapon);
            }
        }
    }

    /* Actions ] */

    /* [ UI */

    protected void displayMessage(World world, PlayerEntity player, ItemStack stack, Weapon weapon) {
        //TODO use a separate hud for that
        if (weapon.ammoBank instanceof FixedAmmoBank) {
            FixedAmmoBank ammo = (FixedAmmoBank) weapon.ammoBank;
            Text text;
            if (ammo.getAmmo() <= 0 && ammo.getReserveAmmo() <= 0) {
                text = new TranslatableText("text.lcmw.outofammo");
            }
            else if (ammo.isReloading()) {
                text = new TranslatableText("text.lcmw.reloading", String.format("%.2f%%", ammo.getReloadProgress() * 100));
            }
            else {
                String str = StringUtils.repeat('!', ammo.getAmmo()) +
                        StringUtils.repeat('.', ammo.getMaxAmmo() - ammo.getAmmo());
                text = new TranslatableText("text.lcmw.ammo", str, ammo.getReserveAmmo(), weapon.getFireMode().displayText);
            }
            player.sendMessage(text, true);
        }
    }

    /* UI ] */

    /* [ Sounds */


    public SoundEvent getSwitchFireModeSound(Weapon weapon) {
        return MWSounds.SELECT_FIRE;
    }

    protected void playFireSound(World world, Entity entity, ItemStack stack, Weapon weapon) {
        world.playSoundFromEntity(null, entity, this.getFireSound(weapon), SoundCategory.AMBIENT, 1.0f, 1.0f);
    }

    protected void playReloadSound(World world, Entity entity, ItemStack stack, Weapon weapon) {
        world.playSoundFromEntity(null, entity, this.getReloadSound(weapon), SoundCategory.AMBIENT, 1.0f, 1.0f);
    }

    public void playSwitchFireModeSound(World world, Entity entity, ItemStack stack, Weapon weapon) {
        world.playSoundFromEntity(null, entity, this.getSwitchFireModeSound(weapon), SoundCategory.AMBIENT, 1.0f, 1.0f);
    }

    protected void stopReloadSound(World world, ItemStack stack, Weapon weapon) {
        S2CPackets.sendStopSoundPacket(world, this.getReloadSound(weapon), SoundCategory.AMBIENT);
    }

    public Weapon getWeapon(ItemStack stack) {
        return weaponType.construct(stack.getOrCreateTag());
    }

    protected void applyChanges(Weapon weapon, ItemStack stack) {
        weapon.toTag(stack.getOrCreateTag());
    }

    /* Sounds ] */
}
