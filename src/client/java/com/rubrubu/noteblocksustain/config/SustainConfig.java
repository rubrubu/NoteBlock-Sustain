package com.rubrubu.noteblocksustain.config;

import com.rubrubu.noteblocksustain.NoteBlockSustainMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class SustainConfig {
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("noteblock-sustain.properties");

    private static boolean enabled = true;
    // Note Block Master has two sustained samples for harp, bass, and flute.
    // Variant 1 is the shorter original sustain setting; variant 2 is longer.
    private static int variant = 1;

    private SustainConfig() {}

    public static void load() {
        Properties properties = new Properties();

        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
                properties.load(reader);
            } catch (IOException exception) {
                NoteBlockSustainMod.LOGGER.warn(
                        "Could not read {}. Using default settings.", PATH, exception
                );
            }
        } else {
            saveDefaults();
        }

        enabled = Boolean.parseBoolean(properties.getProperty("enabled", "true"));
        variant = parseVariant(properties.getProperty("variant", "1"));
    }

    private static int parseVariant(String value) {
        try {
            return Math.max(1, Math.min(2, Integer.parseInt(value.trim())));
        } catch (RuntimeException exception) {
            NoteBlockSustainMod.LOGGER.warn("Invalid sustain variant '{}'; using variant 1.", value);
            return 1;
        }
    }

    private static void saveDefaults() {
        Properties properties = new Properties();
        properties.setProperty("enabled", "true");
        properties.setProperty("variant", "1");

        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8)) {
                properties.store(writer,
                        "NoteBlock Sustain settings. Restart Minecraft after editing.");
            }
        } catch (IOException exception) {
            NoteBlockSustainMod.LOGGER.warn("Could not create config file {}.", PATH, exception);
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static int getVariant() {
        return variant;
    }
}
