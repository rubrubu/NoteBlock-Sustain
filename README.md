# NoteBlock Sustain

A client-side Fabric mod for Minecraft Java 26.2 that replaces vanilla Note Block sound packets with Note Block Master's original sustained audio samples.

## Scope

- Replaces the audio for the 16 vanilla Note Block instruments.
- Uses the original dedicated sustained OGG samples from Note Block Master 0.8.1.
- Preserves the sound packet's instrument, source, volume, pitch, and position.
- Runs entirely on the client. A server such as DonutSMP does not need this mod installed; only the player running this client hears the replacement audio.
- Does not add blocks, items, tuning cores, composer tools, extended note range, extra octaves, or a pitch-limit mixin.

The mod intercepts the incoming clientbound Note Block sound packet before Minecraft creates the vanilla sound instance. It then plays a local replacement sound and cancels the vanilla packet's normal sound handling.

## Requirements

- Minecraft Java Edition 26.2
- Fabric Loader 0.20.1 or newer
- Java 25

## Installation

1. Install Fabric for Minecraft 26.2.
2. Put the built `noteblock-sustain-2.0.0.jar` in the instance's `mods` folder.
3. Launch the Fabric profile. No server-side installation is required.

## Configuration

When Mod Menu is installed, open **Mods → NoteBlock Sustain → Configure** to change the settings in-game. The settings save automatically. Mod Menu is optional; without it, you can edit `config/noteblock-sustain.properties` manually:

```properties
enabled=true
variant=1
```

- `enabled`: turns the replacement sounds on or off.
- `variant`: chooses the sample variant for harp, bass, and flute. Set to `1` or `2`. Instruments with a single sustained sample always use that sample.

This release does not use a keyboard shortcut to toggle sustain.

Restart Minecraft after editing the file.

## Audio assets and license

The sustained OGG files are from **Note Block Master 0.8.1** by **Nayrus**, distributed under **GNU GPL version 3 only**. They are sourced from the exact 0.8.1 release artifact, pinned by Modrinth version ID `KZC6xIse`, and extracted at build time. The build uses only the OGG files; it does not package Note Block Master's NeoForge classes, blocks, items, or pitch-range changes.

Because the redistributed audio is GPL-3.0-only, this project is also distributed under GPL-3.0-only. See [LICENSE](LICENSE) and [NOTICE.md](NOTICE.md).

## Build

Use JDK 25 and Gradle 9.8.2:

```bash
gradle build
```

The build downloads the pinned Note Block Master 0.8.1 archive as a build-only asset source, extracts only its sustained OGG files, then packages the Fabric mod. The NeoForge mod itself is not a runtime dependency.

The resulting JAR is written to `build/libs/`. GitHub Actions builds the project on pushes and pull requests and uploads the JAR as an artifact.

## Compatibility notes

This implementation is designed for Minecraft 26.2 Fabric and server-side vanilla Note Block sound packets. It has no server mod or custom payload dependency. Compilation and packet interception can be verified in CI, but final confirmation still requires an in-game test on a local world and DonutSMP.
