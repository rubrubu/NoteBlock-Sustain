package com.rubrubu.noteblocksustain;

import com.rubrubu.noteblocksustain.audio.PendingSoundQueue;
import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class NoteBlockSustainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SustainConfig.load();

        // Play replacement samples after Minecraft has completed its sound-engine tick.
        // This avoids mutating the active-sound map while SoundEngine is iterating it.
        ClientTickEvents.END_CLIENT_TICK.register(PendingSoundQueue::flush);

        NoteBlockSustainMod.LOGGER.info(
                "NoteBlock Sustain client initialized. Enabled: {}, audio variant: {}",
                SustainConfig.isEnabled(),
                SustainConfig.getVariant()
        );
    }
}
