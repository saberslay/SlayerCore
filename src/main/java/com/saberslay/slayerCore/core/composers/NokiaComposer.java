package com.saberslay.slayerCore.core.composers;

import java.util.Map;
import javax.sound.sampled.*;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class NokiaComposer {

    public enum PlayStyle {
        STACCATO, LEGATO
    }

    public enum NoteLength {
        SHORT, LONG
    }

    private static final Map<Integer, Double> NOTES = Map.of(
            1, 261.63, // C
            2, 293.66, // D
            3, 329.63, // E
            4, 349.23, // F
            5, 392.00, // G
            6, 440.00, // A
            7, 493.88, // B
            8, 523.25  // C (high)
    );

    private static final float SAMPLE_RATE = 8000.0f;

    public void playNotes(
            boolean loop,
            int tempoMs,
            NoteLength noteLength,
            PlayStyle style,
            int... notes
    ) throws LineUnavailableException, InterruptedException {

        do {
            for (int key : notes) {
                int duration = (noteLength == NoteLength.SHORT)
                        ? tempoMs / 2
                        : tempoMs;

                // Rest note (9 = pause)
                if (key == 9) {
                    Thread.sleep(duration);
                    continue;
                }

                Double freq = NOTES.get(key);
                if (freq == null) continue;

                if (style == PlayStyle.STACCATO) {
                    playSquareWave(freq, duration / 2);
                    Thread.sleep(duration / 2);
                } else {
                    playSquareWave(freq, duration);
                }
            }
        } while (loop);
    }

    public void playNotesSafe(
            boolean loop,
            int tempoMs,
            NoteLength noteLength,
            PlayStyle style,
            int... notes
    ) {
        try {
            playNotes(loop, tempoMs, noteLength, style, notes);
        } catch (LineUnavailableException | InterruptedException e) {
            throw new RuntimeException("Error playing notes", e);
        }
    }

    public void playNotesAsync(
            boolean loop,
            int tempoMs,
            NoteLength noteLength,
            PlayStyle style,
            int... notes
    ) {
        new Thread(() ->
                playNotesSafe(loop, tempoMs, noteLength, style, notes)
        ).start();
    }

    private void playSquareWave(double freq, int ms)
            throws LineUnavailableException {

        int length = (int) (SAMPLE_RATE * ms / 1000);
        byte[] buffer = new byte[length];

        double period = SAMPLE_RATE / freq;

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) ((i % period < period / 2) ? 120 : -120);
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