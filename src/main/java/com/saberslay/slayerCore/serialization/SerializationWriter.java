package com.saberslay.slayerCore.serialization;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class SerializationWriter {

    public static final byte[] HEADER = "SC".getBytes();
    public static final short VERSION = 0x0100; // big endian

    public static int writeBytes(byte[] dest, int pointer, byte[] src) {
        for(int i = 0; i < src.length; i++) {
            dest[pointer++] = src[i];
        }
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, byte value) {
        dest[pointer++] = value;
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, short value) {
        dest[pointer++] = (byte)((value >> 8) & 0xff);
        dest[pointer++] = (byte)((value >> 0) & 0xff);
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, char value) {
        dest[pointer++] = (byte)((value >> 8) & 0xff);
        dest[pointer++] = (byte)((value >> 0) & 0xff);
        return pointer;
    }


    public static int writeBytes(byte[] dest, int pointer, int value) {
        dest[pointer++] = (byte)((value >> 24) & 0xff);
        dest[pointer++] = (byte)((value >> 16) & 0xff);
        dest[pointer++] = (byte)((value >> 8) & 0xff);
        dest[pointer++] = (byte)((value >> 0) & 0xff);
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, long value) {
        dest[pointer++] = (byte)((value >> 56) & 0xff);
        dest[pointer++] = (byte)((value >> 48) & 0xff);
        dest[pointer++] = (byte)((value >> 40) & 0xff);
        dest[pointer++] = (byte)((value >> 32) & 0xff);
        dest[pointer++] = (byte)((value >> 24) & 0xff);
        dest[pointer++] = (byte)((value >> 16) & 0xff);
        dest[pointer++] = (byte)((value >> 8) & 0xff);
        dest[pointer++] = (byte)((value >> 0) & 0xff);
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, float value) {
       int data = Float.floatToIntBits(value);
       return writeBytes(dest, pointer, data);
    }

    public static int writeBytes(byte[] dest, int pointer, double value) {
        long data = Double.doubleToLongBits(value);
        return writeBytes(dest, pointer, data);
    }

    public static int writeBytes(byte[] dest, int pointer, boolean value) {
        dest[pointer++] = (byte)(value ? 1 : 0);
        return pointer;
    }

    public static int writeBytes(byte[] dest, int pointer, String string ) {
        pointer = writeBytes(dest, pointer, (short) string.length());
        return writeBytes(dest, pointer, string.getBytes());
    }
}