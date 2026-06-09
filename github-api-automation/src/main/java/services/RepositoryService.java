package services;

import config.ConfigReader;
import config.EnvironmentVariables;
import endpoints.RepoEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Repository;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RepositoryService {

    public Response createRepo(Repository repo) {
       return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .contentType(ContentType.JSON)
                .body(repo)
                .when()
                .post(RepoEndpoints.CREATE_REPO);
    }

    public Response getRepo(String username,String repo) {
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .when()
                .get(RepoEndpoints.GET_REPO);

    }

    public Response updateRepoDescription(String username, String repo, String newDescription) {
        Map<String, String> body = new HashMap<>();
        body.put("description", newDescription);
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .body(body)
                .when()
                .patch(RepoEndpoints.UPDATE_REPO);
    }

    public Response deleteRepo(String username, String repo) {
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .when()
                .delete(RepoEndpoints.DELETE_REPO);
    }

}
