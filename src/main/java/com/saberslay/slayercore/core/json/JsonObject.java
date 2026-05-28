package com.saberslay.slayercore.core.json;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.util.LinkedHashMap;

public class JsonObject extends LinkedHashMap<String, JsonValue> {

    public JsonValue getValue(String key) {
        return get(key);
    }

    public String getString(String key) {
        JsonValue v = get(key);
        return v != null && v.isString() ? v.asString() : null;
    }

    public double getNumber(String key) {
        JsonValue v = get(key);
        return v != null && v.isNumber() ? v.asNumber() : 0;
    }

    public boolean getBoolean(String key) {
        JsonValue v = get(key);
        return v != null && v.isBoolean() && v.asBoolean();
    }

    public JsonObject getObject(String key) {
        JsonValue v = get(key);
        return v != null && v.isObject() ? v.asObject() : null;
    }

    public JsonArray getArray(String key) {
        JsonValue v = get(key);
        return v != null && v.isArray() ? v.asArray() : null;
    }
}