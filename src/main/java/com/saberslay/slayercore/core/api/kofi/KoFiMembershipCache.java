package com.saberslay.slayercore.core.api.kofi;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.platform.Platform;
import com.saberslay.slayercore.core.serialization.SCDatabase;
import com.saberslay.slayercore.core.serialization.SCObject;
import com.saberslay.slayercore.core.serialization.SCField;
import com.saberslay.slayercore.core.serialization.SCString;

import java.nio.file.Path;

/**
 * Caches Ko‑fi membership status for 24 hours.
 */
public class KoFiMembershipCache {

    private static final long CACHE_DURATION = 24L * 60L * 60L * 1000L; // 24 hours

    private final Path file;

    private boolean cachedStatus = false;
    private long lastChecked = 0;

    private SupporterInfo cachedInfo = new SupporterInfo();

    public KoFiMembershipCache() {
        Path dir = Platform.ensureDir(Platform.appDataDir(".Saberslay"));
        file = dir.resolve("kofi_cache.scd");
        load();
    }

    private void load() {
        if (!file.toFile().exists()) return;

        SCDatabase db = SCDatabase.DeserializeFromFile(file.toString());
        if (db.objects.isEmpty()) return;

        SCObject obj = db.objects.get(0);

        cachedInfo = new SupporterInfo();

        if (obj.findField("isSupporter") != null)
            cachedInfo.isSupporter = obj.findField("isSupporter").getBoolean();

        if (obj.findField("amount") != null)
            cachedInfo.amount = obj.findField("amount").getDouble();

        if (obj.findString("tierName") != null)
            cachedInfo.tierName = obj.findString("tierName").getString();

        if (obj.findField("isMonthly") != null)
            cachedInfo.isMonthly = obj.findField("isMonthly").getBoolean();

        if (obj.findField("timestamp") != null)
            lastChecked = obj.findField("timestamp").getLong();
    }

    public SupporterInfo getCachedInfo() {
        return cachedInfo;
    }

    private void save() {
        SCDatabase db = new SCDatabase("KoFiCache");
        SCObject obj = new SCObject("KoFiCache");

        obj.addField(SCField.Boolean("isSupporter", cachedInfo.isSupporter));
        obj.addField(SCField.Double("amount", cachedInfo.amount));
        obj.addString(SCString.Create("tierName", cachedInfo.tierName));
        obj.addField(SCField.Boolean("isMonthly", cachedInfo.isMonthly));

        obj.addField(SCField.Long("timestamp", lastChecked));

        db.addObject(obj);
        db.serializeToFile(file.toString());
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - lastChecked > CACHE_DURATION;
    }

    public boolean getCachedStatus() {
        return cachedStatus;
    }

    public void update(SupporterInfo info) {
        this.cachedInfo = info;
        this.lastChecked = System.currentTimeMillis();
        save();
    }
}