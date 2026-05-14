package com.saberslay.slayercore.core.platform;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.nio.file.Files;
import java.nio.file.Path;

public final class Platform {

    private static final String OS =
            System.getProperty("os.name").toLowerCase();

    private static final String ARCH =
            System.getProperty("os.arch").toLowerCase();

    private Platform() {}

    // ===== OS DETECTION =====
    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isLinux() {
        return OS.contains("nux") || OS.contains("nix");
    }

    public static String osName() {
        return OS;
    }

    // ===== ARCHITECTURE =====
    public static boolean is64Bit() {
        return ARCH.contains("64");
    }

    public static boolean isArm() {
        return ARCH.contains("arm") || ARCH.contains("aarch");
    }

    public static String arch() {
        return ARCH;
    }

    // ===== APP DATA DIRECTORY (CROSS‑PLATFORM) =====
    public static Path appDataDir(String appName) {

        String base;

        if (isWindows()) {
            base = System.getenv("APPDATA");
            if (base == null) {
                base = System.getProperty("user.home");
            }
            return Path.of(base, appName);
        }

        if (isMac()) {
            base = System.getProperty("user.home");
            return Path.of(base, "Library", "Application Support", appName);
        }

        // Linux / Unix
        base = System.getenv("XDG_DATA_HOME");
        if (base == null) {
            base = System.getProperty("user.home") + "/.local/share";
        }
        return Path.of(base, appName);
    }

    // ===== ENSURE DIRECTORY EXISTS =====
    public static Path ensureDir(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create directory: " + dir, e);
        }
        return dir;
    }
}