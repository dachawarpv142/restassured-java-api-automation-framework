# Rest Assured Java API Automation Framework

A lightweight API automation framework built with Java, Rest Assured, TestNG, Allure, and GitHub Actions.

## Features
- Rest Assured + TestNG based API validation
- Allure reporting integration
- Multi-environment support (`dev`, `qa`, `prod`) with runtime selection
- Dynamic environment config loading via `-Denv`
- Reusable utilities for request specification, response validation, and environment management
- CRUD sample tests against JSONPlaceholder API
- Optional API monitoring hooks for Sauce Labs / BrowserStack
- GitHub Actions CI pipeline with Allure results artifact upload

## Project Structure
- `pom.xml` - Maven dependencies and build plugins
- `testng.xml` - TestNG suite
- `src/test/resources/config/*.properties` - Environment-specific configs
- `src/test/java/com/framework/config/ConfigManager.java` - Environment + properties loader
- `src/test/java/com/framework/core/RequestSpecFactory.java` - Reusable request specification
- `src/test/java/com/framework/core/ResponseValidator.java` - Common response assertions
- `src/test/java/com/framework/core/BaseTest.java` - Global API test setup
- `src/test/java/com/framework/api/PostApi.java` - Posts endpoint wrapper
- `src/test/java/com/tests/PostCrudTests.java` - CRUD tests for GET/POST/PUT/DELETE
- `src/test/java/com/framework/integrations/CloudApiHooks.java` - Optional provider hooks
- `.github/workflows/api-tests.yml` - CI/CD workflow

## Prerequisites
- Java 17+
- Maven 3.9+

## Run Tests
Default (dev):
```powershell
mvn clean test
```

Run against specific environment:
```powershell
mvn clean test -Denv=qa
mvn clean test -Denv=prod
```

You can also override property values at runtime:
```powershell
mvn clean test -Denv=qa -Dbase.url=https://jsonplaceholder.typicode.com
```

## Environment Configuration
Config files are located in:
- `src/test/resources/config/dev.properties`
- `src/test/resources/config/qa.properties`
- `src/test/resources/config/prod.properties`

`ConfigManager` resolves active env using:
1. `-Denv=<name>` runtime parameter
2. fallback to `dev`

## Allure Reporting
Allure raw results are generated under:
- `target/allure-results`

To generate/view report locally (requires Allure CLI):
```powershell
allure serve target/allure-results
```

## CI/CD (GitHub Actions)
Workflow file:
- `.github/workflows/api-tests.yml`

Pipeline behavior:
- Runs on push, pull request, and manual dispatch
- Executes tests with env matrix (`dev`, `qa`, `prod`) unless manually passed
- Uploads `target/allure-results` as build artifacts

## Optional Sauce Labs / BrowserStack Hooks
`CloudApiHooks` checks for:
- `SAUCE_USERNAME`
- `BROWSERSTACK_USERNAME`

If present, framework prints hook logs for API checks. You can extend this class to call provider APIs for custom monitoring events.

## Notes
- JSONPlaceholder is a dummy API, so POST/PUT/DELETE actions are mock responses.
- This framework is designed as a starter and can be extended with schema validators, auth layers, and data-driven test strategies.

