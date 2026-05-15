package com.saberslay.slayercore.core.net;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class UDPClientBase {

    protected DatagramSocket socket;
    protected InetAddress remoteAddress;
    protected int remotePort;

    private volatile boolean running = false;

    public UDPClientBase(String host, int port, int timeoutMs) throws Exception {
        this.remoteAddress = InetAddress.getByName(host);
        this.remotePort = port;

        socket = new DatagramSocket();
        socket.setSoTimeout(timeoutMs);
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
                byte[] buffer = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                onPacketReceived(packet.getData(), packet.getLength());

            } catch (Exception e) {
                onReceiveError(e);
            }
        }
    }

    public void send(byte[] data) {
        try {
            DatagramPacket packet =
                    new DatagramPacket(data, data.length, remoteAddress, remotePort);

            socket.send(packet);

        } catch (Exception e) {
            onSendError(e);
        }
    }

    public void stop() {
        running = false;
        try { socket.close(); } catch (Exception ignored) {}
    }

    protected abstract void onPacketReceived(byte[] data, int length);
    protected abstract void onReceiveError(Exception e);
    protected abstract void onSendError(Exception e);
}