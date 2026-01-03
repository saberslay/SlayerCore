package com.saberslay.slayerCore.spigot;

import com.saberslay.saberCore.spigot.listeners.CustomItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlayerCore extends JavaPlugin {

    private static SlayerCore instance;

    @Override
    public void onEnable() {
        instance = this;

        // Register listener
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), this);

        getLogger().info("SaberCore enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SaberCore disabled!");
    }

    public static SlayerCore getInstance() {
        return instance;
    }
}