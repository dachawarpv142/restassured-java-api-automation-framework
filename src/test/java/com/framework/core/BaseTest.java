package com.framework.core;

import com.framework.config.ConfigManager;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public abstract class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.requestSpecification = RequestSpecFactory.defaultSpec();
        System.setProperty("allure.results.directory", "target/allure-results");
        System.out.println("Running tests on env: " + ConfigManager.getEnv() + " | Base URL: " + ConfigManager.get("base.url"));
    }
}

