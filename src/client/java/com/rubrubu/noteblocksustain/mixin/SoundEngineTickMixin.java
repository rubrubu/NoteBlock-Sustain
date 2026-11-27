package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.audio.PendingSoundQueue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Flushes queued replacement notes immediately after SoundEngine has finished
 * processing its active-sound collection for this tick.
 */
@Mixin(SoundEngine.class)
public abstract class SoundEngineTickMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void noteblocksustain$flushPendingSounds(CallbackInfo ci) {
        PendingSoundQueue.flush(Minecraft.getInstance());
    }
}
