package com.saberslay.slayercore.core.crypto;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.serialization.SCDatabase;

public class SecureSCDatabaseChannel {

    private final StrongCustomCipher cipher;
    private final Listener listener;

    public interface Listener {
        void onDatabaseReceived(SCDatabase db);
        void onDecryptionFailed(Exception e);
    }

    public SecureSCDatabaseChannel(byte[] masterKey, Listener listener) {
        this.cipher = new StrongCustomCipher(masterKey);
        this.listener = listener;
    }

    /**
     * Serialize + encrypt an SCDatabase into a secure packet.
     */
    public byte[] encode(SCDatabase db) {
        try {
            byte[] plain = db.serialize();
            return cipher.encrypt(plain);
        } catch (Exception e) {
            throw new RuntimeException("Secure encode failed", e);
        }
    }

    /**
     * Decrypt + deserialize a received packet.
     */
    public void decode(byte[] packet) {
        try {
            byte[] decrypted = cipher.decrypt(packet);
            SCDatabase db = SCDatabase.Deserialize(decrypted);
            listener.onDatabaseReceived(db);
        } catch (Exception e) {
            listener.onDecryptionFailed(e);
        }
    }
}