package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.audio.PendingSoundQueue;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Flushes replacement note-block sounds only after Minecraft's sound engine
 * has completed the current client tick and finished iterating active sounds.
 */
@Mixin(Minecraft.class)
public abstract class MinecraftClientTickMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void noteblocksustain$flushPendingSounds(CallbackInfo ci) {
        PendingSoundQueue.flush((Minecraft) (Object) this);
    }
}
