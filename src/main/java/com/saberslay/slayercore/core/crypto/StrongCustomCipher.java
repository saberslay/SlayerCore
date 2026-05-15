package com.saberslay.slayercore.core.crypto;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class StrongCustomCipher {

    private static final String AES_TRANSFORM = "AES/CTR/NoPadding";
    private static final String HMAC_ALGO = "HmacSHA256";
    private static final int NONCE_LEN = 16;
    private static final int HMAC_LEN = 32;

    private final byte[] masterKey;
    private final SecureRandom random = new SecureRandom();

    public StrongCustomCipher(byte[] masterKey) {
        if (masterKey.length != 32) {
            throw new IllegalArgumentException("masterKey must be 32 bytes (256-bit)");
        }
        this.masterKey = masterKey.clone();
    }

    private byte[] deriveKey(byte[] nonce) throws Exception {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(masterKey, HMAC_ALGO);
        mac.init(keySpec);
        return mac.doFinal(nonce); // 32 bytes
    }

    private byte[] aesCtr(byte[] key, byte[] nonce, byte[] input, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORM);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);
        cipher.init(mode, keySpec, ivSpec);
        return cipher.doFinal(input);
    }

    private byte[] hmac(byte[] data) throws Exception {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(masterKey, HMAC_ALGO);
        mac.init(keySpec);
        return mac.doFinal(data);
    }

    public byte[] encrypt(byte[] plaintext) throws Exception {
        byte[] nonce = new byte[NONCE_LEN];
        random.nextBytes(nonce);

        byte[] derivedKey = deriveKey(nonce);
        byte[] ciphertext = aesCtr(derivedKey, nonce, plaintext, Cipher.ENCRYPT_MODE);

        byte[] macInput = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, macInput, 0, nonce.length);
        System.arraycopy(ciphertext, 0, macInput, nonce.length, ciphertext.length);

        byte[] tag = hmac(macInput);

        byte[] out = new byte[nonce.length + ciphertext.length + tag.length];
        System.arraycopy(nonce, 0, out, 0, nonce.length);
        System.arraycopy(ciphertext, 0, out, nonce.length, ciphertext.length);
        System.arraycopy(tag, 0, out, nonce.length + ciphertext.length, tag.length);

        return out;
    }

    public byte[] decrypt(byte[] packet) throws Exception {
        if (packet.length < NONCE_LEN + HMAC_LEN) {
            throw new IllegalArgumentException("Packet too short");
        }

        byte[] nonce = Arrays.copyOfRange(packet, 0, NONCE_LEN);
        byte[] tag = Arrays.copyOfRange(packet, packet.length - HMAC_LEN, packet.length);
        byte[] ciphertext = Arrays.copyOfRange(packet, NONCE_LEN, packet.length - HMAC_LEN);

        byte[] macInput = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, macInput, 0, nonce.length);
        System.arraycopy(ciphertext, 0, macInput, nonce.length, ciphertext.length);

        byte[] expectedTag = hmac(macInput);
        if (!constantTimeEquals(tag, expectedTag)) {
            throw new SecurityException("HMAC verification failed");
        }

        byte[] derivedKey = deriveKey(nonce);
        return aesCtr(derivedKey, nonce, ciphertext, Cipher.DECRYPT_MODE);
    }

    private boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) return false;
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= (a[i] ^ b[i]);
        }
        return result == 0;
    }
}