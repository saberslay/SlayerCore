package com.saberslay.slayercore.core.composers;

import javax.sound.sampled.*;
import java.util.Map;

/*
 * SlayerCore NokiaComposer - Production Version
 */

public class NokiaComposer {

    // -----------------------------
    // Config
    // -----------------------------

    private static final Map<Integer, Double> NOTES = Map.of(
            1, 261.63,
            2, 293.66,
            3, 329.63,
            4, 349.23,
            5, 392.00,
            6, 440.00,
            7, 493.88,
            8, 523.25
    );

    public enum PlayStyle { STACCATO, LEGATO }

    public enum NoteLength { SHORT, LONG }

    private static final float SAMPLE_RATE = 8000f;

    private volatile boolean playing = false;
    private float volume = 1.0f;

    private Thread currentThread;

    // -----------------------------
    // Public API
    // -----------------------------

    public void setVolume(float volume) {
        this.volume = Math.max(0f, Math.min(1f, volume));
    }

    public boolean isPlaying() {
        return playing;
    }

    public void stop() {
        playing = false;

        if (currentThread != null) {
            currentThread.interrupt();
        }
    }

    // -----------------------------
    // Async Player
    // -----------------------------

    public void playNotesAsync(
            boolean loop,
            int tempoMs,
            NoteLength noteLength,
            PlayStyle style,
            int... notes
    ) {

        stop();

        playing = true;

        currentThread = new Thread(() -> {
            do {

                try {
                    playNotesInternal(tempoMs, noteLength, style, notes);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (loop && playing);

            playing = false;

        });

        currentThread.setDaemon(true);
        currentThread.start();
    }

    // -----------------------------
    // Internal Player
    // -----------------------------

    private void playNotesInternal(
            int tempoMs,
            NoteLength noteLength,
            PlayStyle style,
            int... notes
    ) throws Exception {

        for (int key : notes) {

            if (!playing)
                break;

            int duration = (noteLength == NoteLength.SHORT)
                    ? tempoMs / 2
                    : tempoMs;

            if (key == 9) {
                Thread.sleep(duration);
                continue;
            }

            Double freq = NOTES.get(key);
            if (freq == null)
                continue;

            if (style == PlayStyle.STACCATO) {
                playSquareWave(freq, duration / 2);
                Thread.sleep(duration / 2);
            } else {
                playSquareWave(freq, duration);
            }
        }
    }

    // -----------------------------
    // Wave Generator
    // -----------------------------

    private void playSquareWave(double freq, int ms) throws Exception {

        int length = (int)(SAMPLE_RATE * ms / 1000);
        byte[] buffer = new byte[length];

        double period = SAMPLE_RATE / freq;
        int amplitude = (int)(120 * volume);

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte)(
                    (i % period < period / 2)
                            ? amplitude
                            : -amplitude
            );
        }

        AudioFormat format = new AudioFormat(
                SAMPLE_RATE,
                8,
                1,
                true,
                false
        );

        SourceDataLine line = AudioSystem.getSourceDataLine(format);

        line.open(format);
        line.start();

        line.write(buffer, 0, buffer.length);

        line.drain();
        line.stop();
        line.close();
    }
}