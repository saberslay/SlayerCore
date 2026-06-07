package com.saberslay.slayercore.core.system;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class AppInfo {
    public static String getVersion() {
        Package pkg = AppInfo.class.getPackage();
        String v = (pkg != null ? pkg.getImplementationVersion() : null);

        if (v == null) return "DEV";
        if (v.equals("0.0.0")) return "pre-Alther";
        if (v.equals("0.1.0")) return "Alther";

        return v;
    }

    public enum VersionTier {
        DEV,
        PRE_ALTHER,
        ALTHER,
        RELEASE;

        public static VersionTier fromVersion(String version) {
            if (version == null) return DEV;

            switch (version) {
                case "0.0.0":
                    return PRE_ALTHER;

                case "0.1.0":
                    return ALTHER;

                default:
                    return RELEASE;
            }
        }
    }


    public static VersionTier getTier() {
        return VersionTier.fromVersion(getVersion());
    }
}
