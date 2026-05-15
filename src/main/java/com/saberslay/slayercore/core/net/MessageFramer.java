package com.saberslay.slayercore.core.net;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.nio.charset.StandardCharsets;

public class MessageFramer {

    private final String delimiter;

    public MessageFramer(String delimiter) {
        this.delimiter = delimiter;
    }

    public byte[] frame(String message) {
        return (message + delimiter).getBytes(StandardCharsets.UTF_8);
    }

    public String deframe(byte[] data, int length) {
        return new String(data, 0, length, StandardCharsets.UTF_8).trim();
    }
}