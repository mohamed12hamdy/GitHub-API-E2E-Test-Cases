package tests;

import config.ConfigReader;
import datareader.JsonReader;
import models.Repository;
import org.testng.annotations.BeforeClass;
import services.RepositoryService;

public class RepositoryApiBase extends BaseTest {
    protected Repository repository;
    protected RepositoryService repositoryService;
    protected String username;

    @BeforeClass
    public void setUp() {
        repositoryService = new RepositoryService();
        JsonReader repoData = new JsonReader("Repository");
        repository = new Repository(
                repoData.getJsonData("name"),
                repoData.getJsonData("description"),
                Boolean.parseBoolean(repoData.getJsonData("private")),
                Boolean.parseBoolean(repoData.getJsonData("auto_init"))
        );
        username = ConfigReader.get("username");
    }
}
