package com.saberslay.slayerCore.spigot.listeners;

import com.saberslay.slayerCore.spigot.CustomItem;
import com.saberslay.slayerCore.spigot.ItemRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class CustomItemListener implements Listener {

    private final NamespacedKey key;

    public CustomItemListener(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "custom_item");
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;

        String id = item.getItemMeta()
                .getPersistentDataContainer()
                .get(key, PersistentDataType.STRING);

        if (id == null) return;

        CustomItem custom = ItemRegistry.get(id);
        if (custom != null) {
            Action action = event.getAction();
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                custom.onRightClick(event.getPlayer());
            } else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                custom.onLeftClick(event.getPlayer());
            }
        }
    }
}