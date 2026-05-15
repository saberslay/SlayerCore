package com.saberslay.slayercore.core.net;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.crypto.SecureSCDatabaseChannel;
import com.saberslay.slayercore.core.serialization.SCDatabase;

import java.net.InetAddress;

public abstract class SecureSCDatabaseServer extends SecureUDPServerBase {

    private final SecureSCDatabaseChannel channel;

    public SecureSCDatabaseServer(int listenPort, int timeoutMs, byte[] masterKey) throws Exception {
        super(listenPort, timeoutMs, masterKey);

        this.channel = new SecureSCDatabaseChannel(masterKey, new SecureSCDatabaseChannel.Listener() {
            @Override
            public void onDatabaseReceived(SCDatabase db) {
                // Forward to subclass
                SecureSCDatabaseServer.this.onDatabaseReceived(db, lastSenderAddress, lastSenderPort);
            }

            @Override
            public void onDecryptionFailed(Exception e) {
                SecureSCDatabaseServer.this.onDecryptionFailed(e);
            }
        });
    }

    private InetAddress lastSenderAddress;
    private int lastSenderPort;

    @Override
    protected void onPacketDecrypted(byte[] data, InetAddress address, int port) {
        this.lastSenderAddress = address;
        this.lastSenderPort = port;
        channel.decode(data);
    }

    public void sendDatabase(SCDatabase db, InetAddress address, int port) {
        byte[] packet = channel.encode(db);
        sendSecure(packet, address, port);
    }

    protected abstract void onDatabaseReceived(SCDatabase db, InetAddress address, int port);
    protected abstract void onDecryptionFailed(Exception e);
}