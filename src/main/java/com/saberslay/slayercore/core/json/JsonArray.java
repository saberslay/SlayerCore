package com.saberslay.slayercore.core.json;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.util.ArrayList;

public class JsonArray extends ArrayList<JsonValue> {

    public JsonObject getObject(int index) {
        JsonValue v = get(index);
        return v != null && v.isObject() ? v.asObject() : null;
    }

    public String getString(int index) {
        JsonValue v = get(index);
        return v != null && v.isString() ? v.asString() : null;
    }

    public double getNumber(int index) {
        JsonValue v = get(index);
        return v != null && v.isNumber() ? v.asNumber() : 0;
    }

    public boolean getBoolean(int index) {
        JsonValue v = get(index);
        return v != null && v.isBoolean() && v.asBoolean();
    }
}