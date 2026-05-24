package com.saberslay.slayercore.core.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

    private final String versionUrl;

    public UpdateChecker(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String fetchLatestVersion() {
        try {
            URL url = new URL(versionUrl);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                return br.readLine().trim();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isNewer(String current, String latest) {
        if (current == null || latest == null) return false;

        String[] c = current.split("\\.");
        String[] l = latest.split("\\.");

        for (int i = 0; i < Math.min(c.length, l.length); i++) {
            int ci = Integer.parseInt(c[i]);
            int li = Integer.parseInt(l[i]);

            if (li > ci) return true;
            if (li < ci) return false;
        }

        return l.length > c.length;
    }
}