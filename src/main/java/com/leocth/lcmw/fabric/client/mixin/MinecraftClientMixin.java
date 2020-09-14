package com.leocth.lcmw.fabric.client.mixin;

import com.leocth.lcmw.fabric.client.ClientEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * almost the same as the one found in TooManyEvents.
 * :tiny_potato:
 */
@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow public ClientPlayerEntity player;
    @Shadow public HitResult crosshairTarget;
    @Shadow @Final public GameOptions options;

    private boolean shouldSwing = false;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/MinecraftClient;doAttack()V"
            ),
            method = "handleInputEvents",
            cancellable = true
    )
    private void doAttack(CallbackInfo ci) {
        ActionResult result = ClientEvents.ATTACK_KEY_PRESS.invoker().onAttackKeyPress(player, crosshairTarget);
        shouldSwing = result != ActionResult.FAIL;
        if (result != ActionResult.PASS) {
            ci.cancel();
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "handleInputEvents",
            cancellable = true
    )
    private void hookHandleInputEvents(CallbackInfo ci) {
        if (this.options.keyAttack.isPressed()) {
            ActionResult result = ClientEvents.ATTACK_KEY_HOLD.invoker().onAttackKeyHold(player, crosshairTarget);
            shouldSwing = result != ActionResult.FAIL;
        }
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V"
            ),
            method = "doAttack"
    )
    private void redirectSwingHand(ClientPlayerEntity client, Hand hand) {
        if (shouldSwing) {
            client.swingHand(hand);
        }
    }
}
