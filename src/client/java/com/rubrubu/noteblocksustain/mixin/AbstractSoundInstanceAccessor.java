package com.rubrubu.noteblocksustain.mixin;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Reads the configured volume multiplier before Minecraft resolves the sound asset.
 * AbstractSoundInstance#getVolume() is not safe at that stage because its Sound is null.
 */
@Mixin(AbstractSoundInstance.class)
public interface AbstractSoundInstanceAccessor {
    @Accessor("volume")
    float noteblocksustain$getRawVolume();
}
