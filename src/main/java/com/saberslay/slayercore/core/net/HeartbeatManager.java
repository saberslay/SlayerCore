package com.saberslay.slayercore.core.net;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class HeartbeatManager {

    private long lastBeat = System.currentTimeMillis();
    private final long timeoutMs;

    public HeartbeatManager(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public void beat() {
        lastBeat = System.currentTimeMillis();
    }

    public boolean isTimedOut() {
        return System.currentTimeMillis() - lastBeat > timeoutMs;
    }
}
