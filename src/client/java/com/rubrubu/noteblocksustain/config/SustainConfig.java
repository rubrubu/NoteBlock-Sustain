package com.rubrubu.noteblocksustain.config;

import com.rubrubu.noteblocksustain.NoteBlockSustainClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Simple user-editable config stored in config/noteblock-sustain.properties. */
public final class SustainConfig {
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("noteblock-sustain.properties");

    private static boolean enabled = true;
    private static double sustainSeconds = 3.0;
    private static double releaseSeconds = 0.35;

    private SustainConfig() {}

    public static void load() {
        Properties properties = new Properties();
        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
                properties.load(reader);
            } catch (IOException exception) {
                NoteBlockSustainClient.LOGGER.warn("Could not read {}. Using defaults.", PATH, exception);
            }
        }

        enabled = Boolean.parseBoolean(properties.getProperty("enabled", "true"));
        sustainSeconds = clamp(parseDouble(properties, "sustainSeconds", 3.0), 0.1, 30.0);
        releaseSeconds = clamp(parseDouble(properties, "releaseSeconds", 0.35), 0.0, 10.0);

        if (!Files.exists(PATH)) {
            saveDefaults();
        }
    }

    private static double parseDouble(Properties properties, String key, double fallback) {
        try {
            return Double.parseDouble(properties.getProperty(key, Double.toString(fallback)).trim());
        } catch (RuntimeException exception) {
            NoteBlockSustainClient.LOGGER.warn("Invalid value for '{}'; using {}.", key, fallback);
            return fallback;
        }
    }

    private static double clamp(double value, double min, double max) {
        if (!Double.isFinite(value)) return min;
        return Math.max(min, Math.min(max, value));
    }

    private static void saveDefaults() {
        Properties properties = new Properties();
        properties.setProperty("enabled", "true");
        properties.setProperty("sustainSeconds", "3.0");
        properties.setProperty("releaseSeconds", "0.35");
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8)) {
                properties.store(writer, "NoteBlock Sustain settings. Restart Minecraft after editing.");
            }
        } catch (IOException exception) {
            NoteBlockSustainClient.LOGGER.warn("Could not create config file {}.", PATH, exception);
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static int getSustainTicks() {
        return Math.max(1, (int) Math.round(sustainSeconds * 20.0));
    }

    public static int getReleaseTicks() {
        return Math.max(0, (int) Math.round(releaseSeconds * 20.0));
    }
}
