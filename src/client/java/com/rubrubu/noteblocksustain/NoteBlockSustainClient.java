package com.rubrubu.noteblocksustain;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.fabricmc.api.ClientModInitializer;

public final class NoteBlockSustainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SustainConfig.load();
        NoteBlockSustainMod.LOGGER.info(
                "NoteBlock Sustain client initialized. Enabled: {}, audio variant: {}",
                SustainConfig.isEnabled(),
                SustainConfig.getVariant()
        );
    }
}
