import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostRequests extends TestConfig {

    @Test
    public void UserDataPost() {
        try {
            given()
                    .header("Content-Type", "application/json")
                    .body(userDataPost)
                    .when()
                    .log().all()
                    .post(Resources.postCreateNerwRecordInDatabase())
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);


        }

    }

    @Test
    public void verifyResponseUserDataSchema() {
        try {
            UserDataPost body = given()
                    .header("Content-Type", "application/json")
                    //.body(userDataPost)
                    .when()
                    .log().all()
                    .post(Resources.postCreateNerwRecordInDatabase())
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_OK).log().all().extract().body()//.as(UserData.class)
                    .jsonPath().getObject("", UserDataPost.class);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
        }
    }
}
