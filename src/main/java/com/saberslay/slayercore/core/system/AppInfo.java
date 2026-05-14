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
        return (v != null ? v : "DEV");
    }
}
