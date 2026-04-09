package com.tests;

import com.framework.api.PostApi;
import com.framework.core.BaseTest;
import com.framework.core.ResponseValidator;
import com.framework.integrations.CloudApiHooks;
import com.framework.models.Post;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Feature("JSONPlaceholder Posts API")
public class PostCrudTests extends BaseTest {

    private final PostApi postApi = new PostApi();

    @Test(description = "Validate GET /posts/{id}")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fetches a post by id and validates key fields")
    public void shouldGetPostById() {
        Response response = postApi.getPost(1);

        ResponseValidator.assertStatusCode(response, 200);
        ResponseValidator.assertBodyFieldEquals(response, "id", 1);
        ResponseValidator.assertBodyFieldEquals(response, "userId", 1);

        CloudApiHooks.publishApiCheck("GET_POST", true);
    }

    @Test(description = "Validate POST /posts")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a post and validates response")
    public void shouldCreatePost() {
        Post payload = new Post();
        payload.setUserId(10);
        payload.setTitle("copilot-title");
        payload.setBody("copilot-body");

        Response response = postApi.createPost(payload);

        ResponseValidator.assertStatusCode(response, 201);
        ResponseValidator.assertBodyFieldEquals(response, "title", "copilot-title");
        ResponseValidator.assertBodyFieldEquals(response, "body", "copilot-body");

        CloudApiHooks.publishApiCheck("CREATE_POST", true);
    }

    @Test(description = "Validate PUT /posts/{id}")
    @Severity(SeverityLevel.NORMAL)
    @Description("Updates an existing post and validates response")
    public void shouldUpdatePost() {
        Post payload = new Post();
        payload.setId(1);
        payload.setUserId(1);
        payload.setTitle("updated-title");
        payload.setBody("updated-body");

        Response response = postApi.updatePost(1, payload);

        ResponseValidator.assertStatusCode(response, 200);
        ResponseValidator.assertBodyFieldEquals(response, "id", 1);
        ResponseValidator.assertBodyFieldEquals(response, "title", "updated-title");

        CloudApiHooks.publishApiCheck("UPDATE_POST", true);
    }

    @Test(description = "Validate DELETE /posts/{id}")
    @Severity(SeverityLevel.NORMAL)
    @Description("Deletes an existing post and validates response")
    public void shouldDeletePost() {
        Response response = postApi.deletePost(1);

        ResponseValidator.assertStatusCode(response, 200);

        CloudApiHooks.publishApiCheck("DELETE_POST", true);
    }
}

