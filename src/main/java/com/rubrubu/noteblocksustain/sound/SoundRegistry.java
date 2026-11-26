package com.rubrubu.noteblocksustain.sound;

import com.rubrubu.noteblocksustain.NoteBlockSustainMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

/**
 * Sound event registrations for the original Note Block Master sustained samples.
 * The OGG files are unpacked from the pinned 0.8.1 reference JAR during the build.
 */
public final class SoundRegistry {
    public static final SoundEvent HARP_1 = register("sustained_harp1");
    public static final SoundEvent HARP_2 = register("sustained_harp2");
    public static final SoundEvent BASS_1 = register("sustained_bass1");
    public static final SoundEvent BASS_2 = register("sustained_bass2");
    public static final SoundEvent FLUTE_1 = register("sustained_flute1");
    public static final SoundEvent FLUTE_2 = register("sustained_flute2");

    public static final SoundEvent BASEDRUM = register("sustained_basedrum");
    public static final SoundEvent SNARE = register("sustained_snare");
    public static final SoundEvent HAT = register("sustained_hat");
    public static final SoundEvent GUITAR = register("sustained_guitar");
    public static final SoundEvent BELL = register("sustained_bell");
    public static final SoundEvent CHIME = register("sustained_chime");
    public static final SoundEvent XYLOPHONE = register("sustained_xylophone");
    public static final SoundEvent IRON_XYLOPHONE = register("sustained_ironxylophone");
    public static final SoundEvent COWBELL = register("sustained_cowbell");
    public static final SoundEvent DIDGERIDOO = register("sustained_didgeridoo");
    public static final SoundEvent BIT = register("sustained_bit");
    public static final SoundEvent BANJO = register("sustained_banjo");
    public static final SoundEvent PLING = register("sustained_pling");

    private SoundRegistry() {}

    private static SoundEvent register(String path) {
        Identifier id = Identifier.fromNamespaceAndPath(NoteBlockSustainMod.MOD_ID, path);
        return Registry.register(
                BuiltInRegistries.SOUND_EVENT,
                id,
                SoundEvent.createVariableRangeEvent(id)
        );
    }

    public static void initialize() {
        // Calling this method initializes all static registrations above.
    }
}
