package com.saberslay.slayercore.core.crypto;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.serialization.SCDatabase;

public class EncryptedSerializer {

    private final byte[] key;

    public EncryptedSerializer(byte[] key) {
        this.key = key;
    }

    public byte[] serializeAndEncrypt(SCDatabase db) throws Exception {
        byte[] serialized = db.serialize();
        return AES256Utils.encrypt(serialized, key);
    }

    public SCDatabase decryptAndDeserialize(byte[] encrypted) throws Exception {
        byte[] decrypted = AES256Utils.decrypt(encrypted, key);
        return SCDatabase.Deserialize(decrypted);
    }
}