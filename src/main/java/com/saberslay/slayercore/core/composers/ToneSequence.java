package com.saberslay.slayercore.core.composers;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A simple container for a sequence of tones to be played by NokiaComposer.
 */
public class ToneSequence {

    private final List<Tone> tones = new ArrayList<>();

    public ToneSequence add(int frequency, int durationMs) {
        tones.add(new Tone(frequency, durationMs));
        return this;
    }

    public ToneSequence add(Tone tone) {
        tones.add(tone);
        return this;
    }

    public List<Tone> getTones() {
        return tones;
    }

    public boolean isEmpty() {
        return tones.isEmpty();
    }
}