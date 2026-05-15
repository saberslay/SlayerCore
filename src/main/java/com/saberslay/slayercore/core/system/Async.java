package com.saberslay.slayercore.core.system;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class Async {

    public static void run(Runnable task) {
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    public static void run(Runnable task, Runnable onDone) {
        Thread t = new Thread(() -> {
            try {
                task.run();
            } finally {
                if (onDone != null) onDone.run();
            }
        });
        t.setDaemon(true);
        t.start();
    }
}