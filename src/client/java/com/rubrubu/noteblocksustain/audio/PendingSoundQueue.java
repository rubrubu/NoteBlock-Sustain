package com.rubrubu.noteblocksustain.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Defers replacement sound playback until the end of the client tick.
 * SoundManager.play mutates the active-sound collection, so it must not be
 * called while SoundEngine is iterating that collection.
 */
public final class PendingSoundQueue {
    private static final Queue<SimpleSoundInstance> PENDING = new ConcurrentLinkedQueue<>();
    private static final int MAX_PENDING = 2048;

    private PendingSoundQueue() {}

    public static void enqueue(SimpleSoundInstance sound) {
        if (PENDING.size() >= MAX_PENDING) {
            // Avoid unbounded growth if sound playback is overwhelmed.
            PENDING.poll();
        }
        PENDING.add(sound);
    }

    public static void flush(Minecraft client) {
        SimpleSoundInstance sound;
        while ((sound = PENDING.poll()) != null) {
            client.getSoundManager().play(sound);
        }
    }
}
