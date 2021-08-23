package hw_4;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;

    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");
    }


    positiveResponseSpecification = new ResponseSpecBuilder()
        .expectBody("status", equalTo(200))
        .expectBody("success", is(true))
    .expectResponceTime(lessThanOrEqualsTo(500L))
    .expectContentType(ContentType.JSON)
        .expectStatusCode(200)
        .build();
}
