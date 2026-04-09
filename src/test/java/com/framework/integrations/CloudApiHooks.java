package com.framework.integrations;

import java.util.Optional;

public final class CloudApiHooks {
    private CloudApiHooks() {
    }

    public static Optional<String> monitorProvider() {
        String sauce = System.getenv("SAUCE_USERNAME");
        String browserStack = System.getenv("BROWSERSTACK_USERNAME");
        if (sauce != null && !sauce.isBlank()) {
            return Optional.of("SauceLabs");
        }
        if (browserStack != null && !browserStack.isBlank()) {
            return Optional.of("BrowserStack");
        }
        return Optional.empty();
    }

    public static void publishApiCheck(String checkName, boolean passed) {
        monitorProvider().ifPresent(provider ->
                System.out.printf("[%s Hook] API Check: %s | Passed: %s%n", provider, checkName, passed));
    }
}

