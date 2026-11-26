package com.rubrubu.noteblocksustain.sound;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

/**
 * Maps only the 16 vanilla Note Block instrument sounds to their sustained samples.
 * No custom Note Block blocks, new pitches, or pitch-limit changes are introduced.
 */
public final class SoundSampleMap {
    private SoundSampleMap() {}

    public static SoundEvent getSustainedSound(Identifier vanillaSound, int variant) {
        return switch (vanillaSound.toString()) {
            case "minecraft:block.note_block.harp" ->
                    variant == 2 ? SoundRegistry.HARP_2 : SoundRegistry.HARP_1;
            case "minecraft:block.note_block.bass" ->
                    variant == 2 ? SoundRegistry.BASS_2 : SoundRegistry.BASS_1;
            case "minecraft:block.note_block.flute" ->
                    variant == 2 ? SoundRegistry.FLUTE_2 : SoundRegistry.FLUTE_1;
            case "minecraft:block.note_block.basedrum" -> SoundRegistry.BASEDRUM;
            case "minecraft:block.note_block.snare" -> SoundRegistry.SNARE;
            case "minecraft:block.note_block.hat" -> SoundRegistry.HAT;
            case "minecraft:block.note_block.guitar" -> SoundRegistry.GUITAR;
            case "minecraft:block.note_block.bell" -> SoundRegistry.BELL;
            case "minecraft:block.note_block.chime" -> SoundRegistry.CHIME;
            case "minecraft:block.note_block.xylophone" -> SoundRegistry.XYLOPHONE;
            case "minecraft:block.note_block.iron_xylophone" -> SoundRegistry.IRON_XYLOPHONE;
            case "minecraft:block.note_block.cow_bell" -> SoundRegistry.COWBELL;
            case "minecraft:block.note_block.didgeridoo" -> SoundRegistry.DIDGERIDOO;
            case "minecraft:block.note_block.bit" -> SoundRegistry.BIT;
            case "minecraft:block.note_block.banjo" -> SoundRegistry.BANJO;
            case "minecraft:block.note_block.pling" -> SoundRegistry.PLING;
            default -> null;
        };
    }
}
