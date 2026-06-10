package tests;
import org.testng.annotations.Test;
public class RepositoryApiE2ETests extends RepositoryApiBase {

    @Test
    public void repositoryFullE2ETests() {
        repositoryService.createRepo(repository).then().statusCode(201);
        repositoryService.getRepo(username, repository.getName()).then().statusCode(200);

        String newDescription = "Updated description for " + repository.getName();
        repositoryService.updateRepoDescription(username, repository.getName(), newDescription)
                .then().statusCode(200);

        repositoryService.deleteRepo(username, repository.getName()).then().statusCode(204);
        repositoryService.getRepo(username, repository.getName()).then().statusCode(404);
    }
}
