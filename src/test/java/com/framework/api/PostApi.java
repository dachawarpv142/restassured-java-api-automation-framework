package com.framework.api;

import com.framework.models.Post;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostApi {

    public Response getPost(int postId) {
        return RestAssured.given()
                .when()
                .get("/posts/{id}", postId)
                .then()
                .extract()
                .response();
    }

    public Response createPost(Post payload) {
        return RestAssured.given()
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .extract()
                .response();
    }

    public Response updatePost(int postId, Post payload) {
        return RestAssured.given()
                .body(payload)
                .when()
                .put("/posts/{id}", postId)
                .then()
                .extract()
                .response();
    }

    public Response deletePost(int postId) {
        return RestAssured.given()
                .when()
                .delete("/posts/{id}", postId)
                .then()
                .extract()
                .response();
    }
}

