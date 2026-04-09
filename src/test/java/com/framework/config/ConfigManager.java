package com.framework.config;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class ConfigManager {
    private static final String DEFAULT_ENV = "dev";
    private static final Properties PROPERTIES = new Properties();
    private static final String ACTIVE_ENV;

    static {
        ACTIVE_ENV = System.getProperty("env", DEFAULT_ENV).toLowerCase();
        String resourcePath = String.format("config/%s.properties", ACTIVE_ENV);
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Config file not found for env: " + ACTIVE_ENV);
            }
            PROPERTIES.load(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load config for env: " + ACTIVE_ENV, e);
        }
    }

    private ConfigManager() {
    }

    public static String getEnv() {
        return ACTIVE_ENV;
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return Objects.toString(PROPERTIES.getProperty(key), "").trim();
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value.isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }
}

