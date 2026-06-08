package tests;

import config.ConfigReader;
import datareader.JsonReader;
import io.restassured.response.Response;
import models.Repository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.RepositoryService;
import utils.TimeUtils;

public class RepositoryTests extends BaseTest{

    private RepositoryService repositoryService;

    private Repository repoModel;

    private Repository createdRepo;

    private Repository fetchedRepo;

    @BeforeClass
    public void setUp() {
      repositoryService = new RepositoryService();
      repoModel = JsonReader.getJson("Repository", Repository.class);
      repoModel.setName(repoModel.getName() + "-" + TimeUtils.getSafeTimestamp());
    }

    @Test
    public void testCreateRepository() {
        Response response = repositoryService.createRepo(repoModel);
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for repository creation");
        createdRepo = response.as(Repository.class);
        Assert.assertEquals(createdRepo.getName(), repoModel.getName(), "Repository name should match the input");
    }

    @Test(dependsOnMethods = "testCreateRepository")
    public void testGETRepository() {
        Response response = repositoryService.getRepo(ConfigReader.get("username"), createdRepo.getName());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for getting repository");
        fetchedRepo = response.as(Repository.class);
        Assert.assertEquals(fetchedRepo.getName(), createdRepo.getName(), "Fetched repository name should match the created repository");
    }

}
