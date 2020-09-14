package com.leocth.lcmw.fabric.common.item;

/**
 * Indicates that differences in NBT data are disregarded when comparing ItemStacks.
 * Used to suppress the reset of the equip progress in {@code HeldItemRenderer} when the NBT data changes for certain
 * items. (e.g. weapons)
 *
 * @author leocth
 */
public interface IgnoreNbt { }
