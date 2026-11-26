package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.InputConstants;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class SustainToggleKeyMixin {
    @Inject(method = "keyPress", at = @At("HEAD"))
    private void noteblocksustain$toggleSustain(KeyEvent event, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.screen != null || event.key() != InputConstants.KEY_K) {
            return;
        }

        SustainConfig.toggleEnabled();
        String status = SustainConfig.isEnabled() ? "ON" : "OFF";
        minecraft.gui.setOverlayMessage(
                Component.literal("NoteBlock Sustain: " + status),
                false
        );
    }
}
