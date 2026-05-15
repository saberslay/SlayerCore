package com.saberslay.slayercore.core.crypto;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class EncryptedMessageFramer {

    public byte[] frame(byte[] encryptedPayload) {
        return encryptedPayload; // raw binary, no delimiter needed
    }

    public byte[] deframe(byte[] data, int length) {
        byte[] out = new byte[length];
        System.arraycopy(data, 0, out, 0, length);
        return out;
    }
}