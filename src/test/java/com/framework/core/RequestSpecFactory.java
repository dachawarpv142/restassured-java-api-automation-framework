package com.framework.core;

import com.framework.config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {
    private RequestSpecFactory() {
    }

    public static RequestSpecification defaultSpec() {
        RestAssuredConfig config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", ConfigManager.getInt("connect.timeout.ms", 5000))
                        .setParam("http.socket.timeout", ConfigManager.getInt("socket.timeout.ms", 5000)));

        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(config)
                .addFilter(new AllureRestAssured())
                .build();
    }
}

