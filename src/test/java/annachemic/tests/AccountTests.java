package annachemic.tests;

import org.junit.jupiter.api.Test;

import java.net.http.HttpRequest;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTest{
    @Test
    void getAccountInfoTest() {
        given()
                .header("Authorization", token)
                .when()
                .get("https://api.imqur.com/3/account/{username}", username)
                .then()
                .statusCode(200);

    }

    @Test
    void getAccountInfoWithLoggingTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .url()
                .when()
                .get("https://api.imqur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoWithAssertionsInGivenTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .url()
                .expect()
                .statusCode(200)
                .body("data.url", equalTo(username))
                .body("success", equalTo(true))
                .bpdy("status", equalTo(200))
                .contentType("application/json")
                .when()
                .get("https://api.imqur.com/3/account/{username}", username)
                .prettyPeek()
    }


}
