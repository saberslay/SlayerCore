package com.saberslay.slayercore.core.composers;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

/**
 * Represents a single tone with a frequency and duration.
 */
public class Tone {

    private final int frequency;
    private final int durationMs;

    public Tone(int frequency, int durationMs) {
        this.frequency = frequency;
        this.durationMs = durationMs;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getDurationMs() {
        return durationMs;
    }
}