package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void baseSetUp() {
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(ConfigReader.get("base.url"))
                        .setContentType(ContentType.JSON)
                        .build();

    }
}
