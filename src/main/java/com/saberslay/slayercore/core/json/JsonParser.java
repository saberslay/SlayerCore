package com.saberslay.slayercore.core.json;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class JsonParser {

    private String text;
    private int index;

    public JsonValue parse(String json) {
        this.text = json;
        this.index = 0;
        skipWhitespace();
        return parseValue();
    }

    private JsonValue parseValue() {
        skipWhitespace();
        char c = peek();

        if (c == '{') return new JsonValue(JsonValue.Type.OBJECT, parseObject());
        if (c == '[') return new JsonValue(JsonValue.Type.ARRAY, parseArray());
        if (c == '"') return new JsonValue(JsonValue.Type.STRING, parseString());
        if (c == 't' || c == 'f') return new JsonValue(JsonValue.Type.BOOLEAN, parseBoolean());
        if (c == 'n') { parseNull(); return new JsonValue(JsonValue.Type.NULL, null); }
        return new JsonValue(JsonValue.Type.NUMBER, parseNumber());
    }

    private JsonObject parseObject() {
        JsonObject obj = new JsonObject();
        expect('{');
        skipWhitespace();

        if (peek() == '}') { index++; return obj; }

        while (true) {
            skipWhitespace();
            String key = parseString();
            skipWhitespace();
            expect(':');
            skipWhitespace();
            JsonValue value = parseValue();
            obj.put(key, value);
            skipWhitespace();

            char c = expect(',', '}');
            if (c == '}') break;
        }

        return obj;
    }

    private JsonArray parseArray() {
        JsonArray arr = new JsonArray();
        expect('[');
        skipWhitespace();

        if (peek() == ']') { index++; return arr; }

        while (true) {
            skipWhitespace();
            arr.add(parseValue());
            skipWhitespace();

            char c = expect(',', ']');
            if (c == ']') break;
        }

        return arr;
    }

    private String parseString() {
        expect('"');
        StringBuilder sb = new StringBuilder();

        while (true) {
            char c = next();
            if (c == '"') break;
            if (c == '\\') {
                char esc = next();
                switch (esc) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    default: sb.append(esc); break;
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private boolean parseBoolean() {
        if (text.startsWith("true", index)) { index += 4; return true; }
        if (text.startsWith("false", index)) { index += 5; return false; }
        throw new RuntimeException("Invalid boolean");
    }

    private void parseNull() {
        if (!text.startsWith("null", index))
            throw new RuntimeException("Invalid null");
        index += 4;
    }

    private Number parseNumber() {
        int start = index;
        while (index < text.length() && "-0123456789.eE".indexOf(text.charAt(index)) >= 0)
            index++;
        return Double.parseDouble(text.substring(start, index));
    }

    private char peek() { return text.charAt(index); }
    private char next() { return text.charAt(index++); }

    private void skipWhitespace() {
        while (index < text.length() && Character.isWhitespace(text.charAt(index)))
            index++;
    }

    private char expect(char... chars) {
        char c = next();
        for (char x : chars) if (c == x) return c;
        throw new RuntimeException("Expected one of " + new String(chars) + " but got " + c);
    }
}