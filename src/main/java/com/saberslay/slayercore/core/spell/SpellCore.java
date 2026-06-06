package com.saberslay.slayercore.core.spell;

import com.saberslay.slayercore.core.serialization.SCDatabase;
import com.saberslay.slayercore.core.serialization.SCObject;
import com.saberslay.slayercore.core.serialization.SCString;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellCore {

    private static final Set<String> dictionary = new HashSet<>();

    private static final int DEBOUNCE_MS = 120;
    private static javax.swing.Timer debounceTimer;

    private static final ExecutorService worker = Executors.newSingleThreadExecutor();

    // Word pattern: letters and apostrophes (you can tweak this)
    private static final Pattern WORD_PATTERN = Pattern.compile("[A-Za-z']+");

    // ============================
    // LOAD DICTIONARY
    // ============================
    public static void init(Path storageDir) {
        try {
            Path dictPath = storageDir.resolve("en_GB.scd");

            System.out.println("Loading dictionary from: " + dictPath.toAbsolutePath());
            if (!Files.exists(dictPath)) {
                throw new IllegalStateException("dictionary.scd missing from storage directory: " + dictPath);
            }

            byte[] data = Files.readAllBytes(dictPath);
            SCDatabase db = SCDatabase.Deserialize(data);

            if (db == null) {
                System.err.println("ERROR: SCDatabase.Deserialize returned null (file is not valid binary SCD)");
                return;
            }

            String[] categories = { "ADJECTIVES", "VERBS", "NOUNS" };
            int loaded = 0;

            for (String cat : categories) {
                SCObject obj = db.findObject(cat);
                if (obj == null) {
                    System.err.println("Dictionary category missing: " + cat);
                    continue;
                }

                for (SCString s : obj.strings) {
                    dictionary.add(s.getString().toLowerCase());
                    loaded++;
                }
            }

            System.out.println("Loaded " + loaded + " words from ADJECTIVES, VERBS, and NOUNS.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================
    // ATTACH SPELL CHECKER
    // ============================
    public static void attach(JTextPane text) {
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { scheduleCheck(text); }
            @Override public void removeUpdate(DocumentEvent e) { scheduleCheck(text); }
            @Override public void changedUpdate(DocumentEvent e) { scheduleCheck(text); }
        });
    }

    private static void scheduleCheck(JTextPane text) {
        if (debounceTimer != null) debounceTimer.stop();
        debounceTimer = new javax.swing.Timer(DEBOUNCE_MS, e -> runSpellCheck(text));
        debounceTimer.setRepeats(false);
        debounceTimer.start();
    }

    // ============================
    // BACKGROUND SPELL CHECK
    // ============================
    private static void runSpellCheck(JTextPane text) {
        String contentSnapshot = text.getText();

        worker.submit(() -> {
            try {
                String lower = contentSnapshot.toLowerCase(Locale.ROOT);
                Matcher matcher = WORD_PATTERN.matcher(lower);

                List<HighlightOp> ops = new ArrayList<>();
                String prev = null;

                while (matcher.find()) {
                    String word = matcher.group();
                    if (word.isEmpty()) continue;

                    int start = matcher.start();
                    int length = word.length();

                    Color color;

                    boolean grammarIssue =
                            GrammarCore.isDoubleWord(prev, word) ||
                                    GrammarCore.isSubjectVerbError(prev, word);

                    if (grammarIssue) {
                        color = Color.ORANGE;
                    } else if (dictionary.contains(word)) {
                        color = new Color(0, 255, 0);
                    } else if (word.length() <= 2) {
                        color = Color.YELLOW;
                    } else {
                        color = Color.RED;
                    }

                    ops.add(new HighlightOp(start, length, color));
                    prev = word;
                }

                if (GrammarCore.needsCapital(lower)) {
                    ops.add(new HighlightOp(0, 1, Color.ORANGE));
                }

                if (lower.length() > 0 && GrammarCore.needsPunctuation(lower)) {
                    ops.add(new HighlightOp(lower.length() - 1, 1, Color.ORANGE));
                }

                SwingUtilities.invokeLater(() -> applyHighlights(text, ops));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // ============================
    // APPLY HIGHLIGHTS ON EDT
    // ============================
    private static void applyHighlights(JTextPane text, List<HighlightOp> ops) {
        try {
            StyledDocument doc = text.getStyledDocument();
            String content = text.getText();

            SimpleAttributeSet normal = new SimpleAttributeSet();
            StyleConstants.setForeground(normal, new Color(0, 255, 0));
            doc.setCharacterAttributes(0, content.length(), normal, true);

            for (HighlightOp op : ops) {
                SimpleAttributeSet style = new SimpleAttributeSet();
                StyleConstants.setForeground(style, op.color);
                doc.setCharacterAttributes(op.start, op.length, style, true);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ============================
    // HELPER CLASS
    // ============================
    private static class HighlightOp {
        final int start, length;
        final Color color;

        HighlightOp(int s, int l, Color c) {
            start = s;
            length = l;
            color = c;
        }
    }
}