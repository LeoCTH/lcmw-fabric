package com.leocth.lcmw.fabric.common.ammo;

import com.google.common.collect.Lists;
import com.leocth.lcmw.fabric.api.AmmoBank;
import com.leocth.lcmw.fabric.common.util.ReloadProgress;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

// this entire thing is a huge todo rn
public class ItemBasedAmmoBank extends AmmoBank {

    private ItemStack ammoStack;
    private List<Item> acceptedAmmoItems;

    public ItemBasedAmmoBank(ItemStack stack, Item... acceptedAmmoItems) {
        this.acceptedAmmoItems = Lists.newArrayList(acceptedAmmoItems);
        CompoundTag tag = stack.getOrCreateSubTag("IBAmmoBank");
        ItemStack ammoStack = ItemStack.fromTag(tag.getCompound("Item"));
        if (this.acceptedAmmoItems.contains(ammoStack.getItem())) {
            this.ammoStack = ammoStack;
        }
    }

    @Override
    public int getAmmo() {
        return ammoStack.getOrCreateSubTag("Ammo").getInt("Ammo");
    }

    @Override
    protected void setAmmo(int ammo) {

    }

    @Override
    public int getMaxAmmo() {
        return ammoStack.getOrCreateSubTag("Ammo").getInt("MaxAmmo");
    }

    @Override
    public int getReloadDuration() {
        return ammoStack.getOrCreateSubTag("Ammo").getInt("ReloadDuration");
    }

    @Override
    public ReloadProgress tickReload() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public AmmoBank copy()  { throw new UnsupportedOperationException("not implemented"); }

    @Override
    public void reload() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean refill(Inventory inv) {
        // todo inventory search, displace current stack
        return false;
    }

    @Override
    public void deserialize(CompoundTag tag) {

    }

    @Override
    public void serialize(CompoundTag tag) {

    }
}
