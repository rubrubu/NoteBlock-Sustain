package com.rubrubu.noteblocksustain;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NoteBlockSustainClient implements ClientModInitializer {
    public static final String MOD_ID = "noteblocksustain";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        SustainConfig.load();
        LOGGER.info("NoteBlock Sustain initialized. Enabled: {}", SustainConfig.isEnabled());
    }
}
