package com.saberslay.slayercore.core.system;

public class AppInfo {
    public static String getVersion() {
        Package pkg = AppInfo.class.getPackage();
        String v = (pkg != null ? pkg.getImplementationVersion() : null);
        return (v != null ? v : "DEV");
    }
}
