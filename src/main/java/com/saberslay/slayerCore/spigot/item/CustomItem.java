package com.saberslay.saberCore.spigot.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomItem {

    String getId();              // Unique ID for the item
    ItemStack create();          // Create the ItemStack

    default void onRightClick(Player player) {}  // Optional behaviour
    default void onLeftClick(Player player) {}
}