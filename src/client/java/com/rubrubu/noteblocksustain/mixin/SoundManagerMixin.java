package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import com.rubrubu.noteblocksustain.sound.SustainedNoteSound;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {
    @ModifyVariable(
            method = {"play", "playDelayed"},
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private SoundInstance noteblocksustain$extendNoteBlockSound(SoundInstance original) {
        if (!(original instanceof AbstractSoundInstance abstractOriginal)
                || original instanceof SustainedNoteSound
                || !SustainConfig.isEnabled()) {
            return original;
        }

        String id = original.getIdentifier().toString();
        if (!id.startsWith("minecraft:block.note_block.")) {
            return original;
        }

        return new SustainedNoteSound(
                abstractOriginal,
                SustainConfig.getSustainTicks(),
                SustainConfig.getReleaseTicks()
        );
    }
}
