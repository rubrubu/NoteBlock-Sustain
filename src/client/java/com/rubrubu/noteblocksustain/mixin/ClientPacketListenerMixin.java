package com.rubrubu.noteblocksustain.mixin;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import com.rubrubu.noteblocksustain.sound.SoundSampleMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Replaces the Note Block audio at the packet boundary, before Minecraft creates
 * or resolves a vanilla SoundInstance. The replacement plays only on this client.
 */
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Inject(method = "handleSoundEvent", at = @At("HEAD"), cancellable = true)
    private void noteblocksustain$replaceNoteBlockSound(
            ClientboundSoundPacket packet,
            CallbackInfo ci
    ) {
        if (!SustainConfig.isEnabled()) {
            return;
        }

        // SoundEvent is a record in Minecraft 26.2; its location is the registry id.
        Identifier vanillaSoundId = packet.getSound().value().location();

        if (vanillaSoundId == null) {
            return;
        }

        SoundEvent sustainedSound = SoundSampleMap.getSustainedSound(
                vanillaSoundId,
                SustainConfig.getVariant()
        );

        if (sustainedSound == null) {
            return;
        }

        SimpleSoundInstance replacement = new SimpleSoundInstance(
                sustainedSound,
                packet.getSource(),
                packet.getVolume(),
                packet.getPitch(),
                RandomSource.create(packet.getSeed()),
                packet.getX(),
                packet.getY(),
                packet.getZ()
        );

        Minecraft.getInstance().getSoundManager().play(replacement);
        ci.cancel();
    }
}
