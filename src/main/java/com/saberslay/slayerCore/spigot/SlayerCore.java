package com.saberslay.slayerCore.spigot;

import com.saberslay.slayerCore.Logger;
import com.saberslay.slayerCore.spigot.listeners.CustomItemListener;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public final class SlayerCore extends JavaPlugin {

    private static SlayerCore instance;

    @Override
    public void onEnable() {
        instance = this;
        // Register listener
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), this);

        Logger.info("SaberCore enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SaberCore disabled!");
    }

    public static SlayerCore getInstance() {
        return instance;
    }
}