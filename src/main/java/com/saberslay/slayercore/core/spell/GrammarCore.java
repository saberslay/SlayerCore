package com.saberslay.slayercore.core.spell;

public class GrammarCore {

    // ============================
    // DOUBLE WORD DETECTION
    // ============================
    public static boolean isDoubleWord(String prev, String current) {
        if (prev == null || current == null) return false;
        return prev.equals(current);
    }

    // ============================
    // BASIC SUBJECT–VERB AGREEMENT
    // ============================
    public static boolean isSubjectVerbError(String prev, String current) {
        if (prev == null || current == null) return false;

        // Third-person singular subjects
        switch (prev) {
            case "he":
            case "she":
            case "it":
                return isBaseVerb(current);
        }

        return false;
    }

    private static boolean isBaseVerb(String word) {
        // Expandable list of verbs that require -s in third person
        switch (word) {
            case "go":
            case "do":
            case "say":
            case "walk":
            case "run":
            case "eat":
            case "sleep":
            case "talk":
                return true;
        }
        return false;
    }

    // ============================
    // CAPITAL LETTER CHECK
    // ============================
    public static boolean needsCapital(String content) {
        if (content == null || content.isEmpty()) return false;

        char c = content.charAt(0);
        return Character.isLetter(c) && Character.isLowerCase(c);
    }

    // ============================
    // END-OF-SENTENCE PUNCTUATION
    // ============================
    public static boolean needsPunctuation(String content) {
        if (content == null || content.isEmpty()) return false;

        char last = content.charAt(content.length() - 1);
        return last != '.' && last != '!' && last != '?';
    }
}