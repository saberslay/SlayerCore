package com.saberslay.slayercore.core.api.kofi;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.json.JsonParser;
import com.saberslay.slayercore.core.json.JsonValue;
import com.saberslay.slayercore.core.json.JsonObject;
import com.saberslay.slayercore.core.json.JsonArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Ko‑fi API client for checking supporters / memberships.
 * Designed for use across all SaberSlay applications.
 */
public class KoFiClient {

    private final String apiKey;

    public KoFiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Checks if a given email belongs to a monthly Ko‑fi supporter.
     *
     * @param emailToCheck The email of the user to verify.
     * @return true if the user is a monthly supporter.
     */
    public boolean isMonthlySupporter(String emailToCheck) {
        try {
            URL url = new URL("https://ko-fi.com/api/v1/supporters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // Build JSON body manually
            String body = "{\"api_key\":\"" + apiKey + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) sb.append(line);

            // Parse JSON using SlayerCore JSON parser
            JsonParser parser = new JsonParser();
            JsonValue root = parser.parse(sb.toString());

            if (!root.isObject()) return false;

            JsonObject obj = root.asObject();
            JsonArray supporters = obj.getArray("data");
            if (supporters == null) return false;

            for (JsonValue v : supporters) {
                if (!v.isObject()) continue;

                JsonObject s = v.asObject();

                String email = s.getString("email");
                boolean isMonthly = s.getBoolean("is_subscription");

                if (email != null &&
                        email.equalsIgnoreCase(emailToCheck) &&
                        isMonthly) {
                    return true;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}