package com.rubrubu.noteblocksustain.sound;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

/**
 * A looping copy of a vanilla Note Block sound that stops after a hold and release phase.
 *
 * This repeats the resolved vanilla sample; it does not time-stretch or synthesize a
 * piano-style sustain envelope.
 */
public final class SustainedNoteSound extends AbstractSoundInstance implements TickableSoundInstance {
    private final int sustainTicks;
    private final int releaseTicks;
    private final float startingVolume;
    private int age;
    private boolean stopped;

    public SustainedNoteSound(SoundInstance original, int sustainTicks, int releaseTicks) {
        super(original.getIdentifier(), original.getSource(), RandomSource.create());
        this.sustainTicks = Math.max(1, sustainTicks);
        this.releaseTicks = Math.max(0, releaseTicks);
        this.startingVolume = original.getVolume();

        this.volume = original.getVolume();
        this.pitch = original.getPitch();
        this.x = original.getX();
        this.y = original.getY();
        this.z = original.getZ();
        this.attenuation = original.getAttenuation();
        this.relative = original.isRelative();
        this.delay = original.getDelay();
        this.looping = true;
    }

    @Override
    public void tick() {
        age++;
        if (age <= sustainTicks) {
            volume = startingVolume;
            return;
        }

        if (releaseTicks == 0) {
            volume = 0.0F;
            stopped = true;
            return;
        }

        int releaseAge = age - sustainTicks;
        float remaining = 1.0F - Math.min(1.0F, releaseAge / (float) releaseTicks);
        volume = startingVolume * remaining;
        if (releaseAge >= releaseTicks) {
            volume = 0.0F;
            stopped = true;
        }
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }
}
