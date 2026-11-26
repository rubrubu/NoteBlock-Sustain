package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class SustainToggleKeyMixin {
    @Unique
    private boolean noteblocksustain$toggleKeyWasDown;

    @Inject(method = "tick", at = @At("TAIL"))
    private void noteblocksustain$checkToggleKey(CallbackInfo ci) {
        Minecraft minecraft = (Minecraft) (Object) this;
        long window = minecraft.getWindow().getWindow();
        boolean keyDown = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_K) == GLFW.GLFW_PRESS;

        if (keyDown && !noteblocksustain$toggleKeyWasDown) {
            SustainConfig.toggleEnabled();
            if (minecraft.player != null) {
                String status = SustainConfig.isEnabled() ? "ON" : "OFF";
                minecraft.player.displayClientMessage(
                        Component.literal("NoteBlock Sustain: " + status + " (K)"),
                        true
                );
            }
        }

        noteblocksustain$toggleKeyWasDown = keyDown;
    }
}
