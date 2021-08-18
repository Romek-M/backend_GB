package annachemic.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.unregisterParser;
import static org.hamcrest.Matchers.notNullValue;

public class ImageTests extends BaseTest {
    private final String PATH_TO_IMAGE = "scr/test/resources/sobaka.jpg";
    static String encodedFile;
    String uploadedImageId;

    @BeforeEach
    void beforeTest() {
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
    }

    @Test
    void uploadFileTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("Image", encodedFile)
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash")

    }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash", "testprogmath", uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);

    }


}
