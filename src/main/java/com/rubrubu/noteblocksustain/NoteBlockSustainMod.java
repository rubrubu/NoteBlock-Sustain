package com.rubrubu.noteblocksustain;

import com.rubrubu.noteblocksustain.sound.SoundRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NoteBlockSustainMod implements ModInitializer {
    public static final String MOD_ID = "noteblocksustain";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SoundRegistry.initialize();
        LOGGER.info("NoteBlock Sustain registered its local audio events.");
    }
}
