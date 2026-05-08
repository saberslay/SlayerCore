package com.saberslay.slayercore.spigot;

import com.saberslay.slayercore.core.logging.Level;
import com.saberslay.slayercore.core.logging.Logger;
import com.saberslay.slayercore.spigot.listeners.CustomItemListener;
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
        Logger.log(Level.INFO, "SlayerCore enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SaberCore disabled!");
    }

    public static SlayerCore getInstance() {
        return instance;
    }
}