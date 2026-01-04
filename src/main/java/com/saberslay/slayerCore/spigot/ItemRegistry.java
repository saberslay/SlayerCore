package com.saberslay.slayerCore.spigot;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public final class ItemRegistry {

    private static final Map<String, CustomItem> ITEMS = new HashMap<>();

    private ItemRegistry() {
        // Utility class
    }

    // Register a single item
    private static void register(CustomItem item) {
        Objects.requireNonNull(item, "CustomItem cannot be null");
        Objects.requireNonNull(item.getId(), "CustomItem ID cannot be null");

        String id = item.getId().toLowerCase();

        if (ITEMS.containsKey(id)) {
            throw new IllegalStateException("Duplicate CustomItem ID: " + id);
        }
        ITEMS.put(id, item);
    }

    public static void registerAll(CustomItem... items) {
        for (CustomItem item : items) {
            register(item);
        }
    }

    // Safe lookup
    public static CustomItem get(String id) {
        if (id == null) return null;
        return ITEMS.get(id.toLowerCase());
    }

    // Read-only view
    public static Collection<CustomItem> all() {
        return Collections.unmodifiableCollection(ITEMS.values());
    }
}