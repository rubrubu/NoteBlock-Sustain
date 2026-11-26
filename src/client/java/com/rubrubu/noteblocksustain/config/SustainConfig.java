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
            save();
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

    public static boolean isEnabled() {
        return enabled;
    }

    public static int getVariant() {
        return variant;
    }

    public static void setEnabled(boolean value) {
        enabled = value;
        save();
    }

    public static void setVariant(int value) {
        variant = Math.max(1, Math.min(2, value));
        save();
    }

    public static void toggleEnabled() {
        setEnabled(!enabled);
    }

    private static void save() {
        Properties properties = new Properties();
        properties.setProperty("enabled", Boolean.toString(enabled));
        properties.setProperty("variant", Integer.toString(variant));

        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8)) {
                properties.store(writer, "NoteBlock Sustain settings.");
            }
        } catch (IOException exception) {
            NoteBlockSustainMod.LOGGER.warn("Could not save config file {}.", PATH, exception);
        }
    }
}
