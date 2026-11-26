# NoteBlock Sustain

A client-side Fabric mod for Minecraft Java 26.2 that makes vanilla Note Block sounds last longer.

## What it does

When a vanilla Note Block sound starts, the mod makes that sound instance loop for a configurable hold period, then fades its volume down during a configurable release period.

**Audio behavior:** this is sample looping, not true time-stretching or a piano-style sustain pedal. The original sound sample restarts at its loop point, so some instruments may have a noticeable repetition. The original instrument, pitch, volume, and location are retained.

## Requirements

- Minecraft Java Edition 26.2
- Fabric Loader 0.20.1 or newer
- Java 25

Fabric API is not required. The mod is client-side. Other players will only hear the sustained effect if they also install it.

## Configuration

On first launch, the mod creates `config/noteblock-sustain.properties`.

```properties
enabled=true
sustainSeconds=3.0
releaseSeconds=0.35
```

- `enabled`: enables or disables the effect.
- `sustainSeconds`: how long the note loops before release (0.1–30 seconds).
- `releaseSeconds`: fade-out duration (0–10 seconds).

Restart Minecraft after editing the file.

## Build

Use JDK 25 and Gradle 9.8.2:

```bash
gradle build
```

The output JAR is in `build/libs/`. GitHub Actions builds the project on each push and pull request and uploads the JAR as a workflow artifact.

## Status

Initial source implementation. It still needs a successful CI build and an in-game audio test before it can be considered a verified release.
