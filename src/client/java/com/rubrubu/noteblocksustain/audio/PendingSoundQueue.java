package com.rubrubu.noteblocksustain.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Queues replacement sounds until SoundEngine has finished iterating its
 * active-sound collection for the current tick. Playback is flushed at the
 * tail of SoundEngine.tick to minimize timing drift.
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
