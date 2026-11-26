package com.rubrubu.noteblocksustain.client;

import com.rubrubu.noteblocksustain.config.SustainConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class SustainConfigScreen extends Screen {
    private final Screen parent;
    private Button enabledButton;
    private Button variantButton;

    public SustainConfigScreen(Screen parent) {
        super(Component.literal("NoteBlock Sustain Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        enabledButton = addRenderableWidget(Button.builder(enabledLabel(), button -> {
            SustainConfig.setEnabled(!SustainConfig.isEnabled());
            button.setMessage(enabledLabel());
        }).bounds(centerX - 100, centerY - 42, 200, 20).build());

        variantButton = addRenderableWidget(Button.builder(variantLabel(), button -> {
            SustainConfig.setVariant(SustainConfig.getVariant() == 1 ? 2 : 1);
            button.setMessage(variantLabel());
        }).bounds(centerX - 100, centerY - 12, 200, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Done"), button ->
                this.minecraft.setScreen(parent)
        ).bounds(centerX - 100, centerY + 30, 200, 20).build());
    }

    private static Component enabledLabel() {
        return Component.literal("Sustain: " + (SustainConfig.isEnabled() ? "ON" : "OFF"));
    }

    private static Component variantLabel() {
        return Component.literal("Harp / Bass / Flute: Variant " + SustainConfig.getVariant());
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
