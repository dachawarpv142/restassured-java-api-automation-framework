package com.framework.core;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public final class ResponseValidator {
    private ResponseValidator() {
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode())
                .as("Unexpected status code")
                .isEqualTo(expectedStatusCode);
    }

    public static void assertBodyFieldEquals(Response response, String fieldName, Object expectedValue) {
        Object actualValue = response.jsonPath().get(fieldName);
        assertThat(actualValue)
                .as("Unexpected value for field: %s", fieldName)
                .isEqualTo(expectedValue);
    }
}

