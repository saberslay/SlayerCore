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

public class KoFiClient {

    private final String apiKey;
    private final KoFiMembershipCache cache = new KoFiMembershipCache();

    public KoFiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public SupporterInfo getSupporterInfo(String emailToCheck) {

        // 1. Use cache if valid
        if (!cache.isExpired()) {
            return cache.getCachedInfo();
        }

        SupporterInfo info = new SupporterInfo();

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

            if (!root.isObject()) {
                cache.update(info);
                return info;
            }

            JsonObject obj = root.asObject();
            JsonArray supporters = obj.getArray("data");

            if (supporters == null) {
                cache.update(info);
                return info;
            }

            // Loop supporters
            for (JsonValue v : supporters) {
                if (!v.isObject()) continue;

                JsonObject s = v.asObject();

                String email = s.getString("email");
                boolean isMonthly = s.getBoolean("is_subscription");
                double amount = s.getNumber("amount");
                String tierName = s.getString("tier_name");

                if (email != null &&
                        email.equalsIgnoreCase(emailToCheck) &&
                        isMonthly) {

                    info.isSupporter = true;
                    info.amount = amount;
                    info.tierName = tierName != null ? tierName : "";
                    info.isMonthly = true;

                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Save to cache
        cache.update(info);

        return info;
    }
}