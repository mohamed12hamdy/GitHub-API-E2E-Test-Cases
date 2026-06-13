package services;

import config.EnvironmentVariables;
import endpoints.LabelsAndMilestonesEndpoints;
import io.restassured.response.Response;
import models.Label;
import models.MileStone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class LabelsAndMilestonesService {

    public Response createLabel(String username , String repo, Label label) {
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .when()
                .body(label)
                .post(LabelsAndMilestonesEndpoints.CREATE_LABEL);
    }

    public Response assignLabelToIssue(String username , String repo, List<String> labels , int issueNumber) {
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .pathParam("issueNumber",issueNumber)
                .when()
                .body(labels)
                .post(LabelsAndMilestonesEndpoints.ASSIGN_LABEL_TO_ISSUE);
    }

    public Response createMileStone(String username, String repo, MileStone mileStone) {
        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .when()
                .body(mileStone)
                .post(LabelsAndMilestonesEndpoints.CREATE_MILESTONE);
    }

    public Response assignMilestoneToIssue(String username, String repo,int milestoneNumber, int issueNumber) {

        Map<String, Object> body = new HashMap<>();
        body.put("milestone", milestoneNumber);

        return given()
                .header("Authorization", "Bearer " + EnvironmentVariables.GITHUB_TOKEN)
                .pathParam("username", username)
                .pathParam("repo", repo)
                .pathParam("issueNumber",issueNumber)
                .when()
                .body(body)
                .patch(LabelsAndMilestonesEndpoints.ASSIGN_MILESTONE_TO_ISSUE);
    }


}
