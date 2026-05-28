package com.saberslay.slayercore.core.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class SoundEngine {

    private static final Map<String, Clip> cache = new HashMap<>();
    private static boolean enabled = true;
    private static float volume = 1.0f; // 0.0 – 1.0

    private SoundEngine() {}

    // ============================================================
    //                       PUBLIC API
    // ============================================================

    public static void setEnabled(boolean v) {
        enabled = v;
    }

    public static void setVolume(float v) {
        volume = Math.max(0f, Math.min(1f, v));
    }

    public static void play(String name) {
        if (!enabled) return;

        try {
            Clip clip = cache.get(name);

            // Load + cache if needed
            if (clip == null) {
                URL url = SoundEngine.class.getResource("/sounds/" + name + ".wav");
                if (url == null) return;

                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(ais);

                cache.put(name, clip);
            }

            // Restart clip
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);

            // Apply volume
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float)(Math.log(volume == 0 ? 0.0001 : volume) / Math.log(10) * 20);
                gain.setValue(dB);
            }

            clip.start();

        } catch (Exception ignored) {}
    }
}