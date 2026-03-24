package net.lunarrdev.sbm.mixin;

import net.lunarrdev.sbm.client.ThunderClientEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class ShakeMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void thunder_shake(CallbackInfo ci) {

        if (ThunderClientEffects.isShaking()) {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player != null) {
                float offsetX = (float)(Math.random() - 0.5) * 0.2f;
                float offsetY = (float)(Math.random() - 0.5) * 0.2f;

                client.player.setYaw(client.player.getYaw() + offsetX);
                client.player.setPitch(client.player.getPitch() + offsetY);
            }
        }
    }
}