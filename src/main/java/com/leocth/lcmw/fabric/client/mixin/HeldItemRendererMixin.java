package com.leocth.lcmw.fabric.client.mixin;

import com.leocth.lcmw.fabric.common.item.IgnoreNbt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(
            method = "updateHeldItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;areEqual(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"
            )
    )
    private boolean updateHeldItems(ItemStack left, ItemStack right) {
        if (left.getItem() instanceof IgnoreNbt && right.getItem() instanceof IgnoreNbt) {
            // relax man
            return ItemStack.areItemsEqualIgnoreDamage(left, right);
        }
        return ItemStack.areEqual(left, right);
    }
}
