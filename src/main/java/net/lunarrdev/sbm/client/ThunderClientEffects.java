package net.lunarrdev.sbm.client;

import net.minecraft.entity.player.PlayerEntity;

public class ThunderClientEffects {

    private static long shakeEndTime = 0;

    public static void trigger(PlayerEntity player) {
        // Shake for 0.5 seconds
        shakeEndTime = System.currentTimeMillis() + 500;
    }

    public static boolean isShaking() {
        return System.currentTimeMillis() < shakeEndTime;
    }
}