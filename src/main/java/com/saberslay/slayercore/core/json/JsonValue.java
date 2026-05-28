package com.saberslay.slayercore.core.json;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class JsonValue {

    public enum Type { OBJECT, ARRAY, STRING, NUMBER, BOOLEAN, NULL }

    public final Type type;
    public final Object value;

    public JsonValue(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public JsonObject asObject() { return (JsonObject) value; }
    public JsonArray asArray() { return (JsonArray) value; }
    public String asString() { return (String) value; }
    public double asNumber() { return ((Number) value).doubleValue(); }
    public boolean asBoolean() { return (boolean) value; }

    public boolean isObject() { return type == Type.OBJECT; }
    public boolean isArray() { return type == Type.ARRAY; }
    public boolean isString() { return type == Type.STRING; }
    public boolean isNumber() { return type == Type.NUMBER; }
    public boolean isBoolean() { return type == Type.BOOLEAN; }
    public boolean isNull() { return type == Type.NULL; }
}