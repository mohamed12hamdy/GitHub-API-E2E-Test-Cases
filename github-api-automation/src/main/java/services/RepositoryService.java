package services;

import config.ConfigReader;
import config.EnvironmentVariables;
import endpoints.RepoEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Repository;

import static io.restassured.RestAssured.given;

public class RepositoryService {

    public Response createRepo(Repository repo) {
       return given()
                .baseUri(ConfigReader.get("base.url"))
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .contentType(ContentType.JSON)
                .body(repo)
                .when()
                .post(RepoEndpoints.CREATE_REPO);
    }

    public Response getRepo(String username,String repo) {
        return given()
                .baseUri(ConfigReader.get("base.url"))
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .when()
                .get(RepoEndpoints.GET_REPO);

    }
}
