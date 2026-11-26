package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class SustainToggleKeyMixin {
    // SDL keycode for the K key.
    private static final int NOTEBLOCKSUSTAIN$KEY_K = 107;

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void noteblocksustain$toggleSustain(KeyEvent event, CallbackInfo ci) {
        if (event.key() == NOTEBLOCKSUSTAIN$KEY_K) {
            SustainConfig.toggleEnabled();
        }
    }
}
