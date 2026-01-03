package com.saberslay.slayerCore.spigot.item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class ItemRegistry {

    private static final Map<String, CustomItem> ITEMS = new HashMap<>();

    public static void register(CustomItem item) {
        ITEMS.put(item.getId(), item);
    }

    public static CustomItem get(String id) {
        return ITEMS.get(id);
    }

    public static Collection<CustomItem> all() {
        return ITEMS.values();
    }
}
