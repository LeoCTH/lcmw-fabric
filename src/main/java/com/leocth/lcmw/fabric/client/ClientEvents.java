package com.leocth.lcmw.fabric.client;

import com.leocth.lcmw.fabric.client.util.Keybindings;
import com.leocth.lcmw.fabric.common.item.TriggerOnAttack;
import com.leocth.lcmw.fabric.common.network.C2SPackets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class ClientEvents {
    public static final Event<AttackKeyPress> ATTACK_KEY_PRESS = EventFactory.createArrayBacked(AttackKeyPress.class, listeners -> (player, hitResult) -> {
        for (AttackKeyPress listener : listeners) {
            ActionResult result = listener.onAttackKeyPress(player, hitResult);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    public static final Event<AttackKeyHold> ATTACK_KEY_HOLD = EventFactory.createArrayBacked(AttackKeyHold.class, listeners -> (player, hitResult) -> {
        for (AttackKeyHold listener : listeners) {
            ActionResult result = listener.onAttackKeyHold(player, hitResult);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    private ClientEvents() {}

    public static void register() {
        ATTACK_KEY_PRESS.register((player, hitResult) -> {
            ItemStack stack = player.getMainHandStack();
            Item item = stack.getItem();
            if (item instanceof TriggerOnAttack) {
                C2SPackets.sendFirePacket(false);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });

        ATTACK_KEY_HOLD.register((player, hitResult) -> {
            ItemStack stack = player.getMainHandStack();
            Item item = stack.getItem();
            if (item instanceof TriggerOnAttack) {
                C2SPackets.sendFirePacket(true);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (Keybindings.RELOAD_KEY.wasPressed()) {
                C2SPackets.sendReloadPacket();
            }
            while (Keybindings.SWITCH_FIREMODE_KEY.wasPressed()) {
                C2SPackets.sendSwitchFireModePacket();
            }
        });
    }

    /**
     * A carbon copy of the TooManyEvents event.
     */
    @FunctionalInterface
    public interface AttackKeyPress {
        /**
         * @param player the attacking player.
         * @param hitResult the result of the hit (miss/block/entity), might be null.
         *
         * @return
         * {@code SUCCESS} or {@code CONSUME} cancels further event processing and swings the player's arms.
         * {@code PASS} pass event handling on to further processing. If all listeners pass, vanilla behavior is executed instead.
         * {@code FAIL} cancels further event processing without swinging the player's arms.
         */
        ActionResult onAttackKeyPress(ClientPlayerEntity player, @Nullable HitResult hitResult);
    }

    /**
     * A carbon copy of the TooManyEvents event.
     */
    @FunctionalInterface
    public interface AttackKeyHold {
        /**
         * @param player the attacking player.
         * @param hitResult the result of the hit (miss/block/entity), might be null.
         *
         * @return
         * {@code SUCCESS} or {@code CONSUME} cancels further event processing and swings the player's arms.
         * {@code PASS} pass event handling on to further processing. If all listeners pass, vanilla behavior is executed instead.
         * {@code FAIL} cancels further event processing without swinging the player's arms.
         */
        ActionResult onAttackKeyHold(ClientPlayerEntity player, @Nullable HitResult hitResult);
    }
}
