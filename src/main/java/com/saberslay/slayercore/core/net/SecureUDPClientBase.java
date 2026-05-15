package com.saberslay.slayercore.core.net;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.crypto.StrongCustomCipher;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class SecureUDPClientBase {

    protected DatagramSocket socket;
    protected InetAddress remoteAddress;
    protected int remotePort;

    private volatile boolean running = false;
    private final StrongCustomCipher cipher;

    public SecureUDPClientBase(String host, int port, int timeoutMs, byte[] masterKey) throws Exception {
        this.remoteAddress = InetAddress.getByName(host);
        this.remotePort = port;

        this.socket = new DatagramSocket();
        this.socket.setSoTimeout(timeoutMs);

        this.cipher = new StrongCustomCipher(masterKey);
    }

    public void start() {
        running = true;

        Thread worker = new Thread(this::listenLoop);
        worker.setDaemon(true);
        worker.start();
    }

    private void listenLoop() {
        while (running) {
            try {
                byte[] buffer = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                byte[] received = new byte[packet.getLength()];
                System.arraycopy(packet.getData(), 0, received, 0, packet.getLength());

                byte[] decrypted = cipher.decrypt(received);
                onPacketDecrypted(decrypted);

            } catch (Exception e) {
                onReceiveError(e);
            }
        }
    }

    public void sendSecure(byte[] plaintext) {
        try {
            byte[] encrypted = cipher.encrypt(plaintext);

            DatagramPacket packet =
                    new DatagramPacket(encrypted, encrypted.length, remoteAddress, remotePort);

            socket.send(packet);

        } catch (Exception e) {
            onSendError(e);
        }
    }

    public void stop() {
        running = false;
        try {
            socket.close();
        } catch (Exception ignored) {}
    }

    protected abstract void onPacketDecrypted(byte[] data);
    protected abstract void onReceiveError(Exception e);
    protected abstract void onSendError(Exception e);
}