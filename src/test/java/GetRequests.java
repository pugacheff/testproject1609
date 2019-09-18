
import io.restassured.module.jsv.JsonSchemaValidator;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class GetRequests extends TestConfig {

  /*  @Test
    public void verifySuccessResponse() {
        try {
            given()
                    .when()
                    .log().all()
                    .get(Resources.getAllEmployeeData())
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .log().all()
                    .body("id", equalTo(719))
                    .log().all();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
        }
    }*/

    @Test
    public void verifyResponseUserDataSchema() {
        try {
            List<UserData> userData = given()
                    .when()
                    .log().all()
                    .get(Resources.getAllEmployeeData())
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_OK).log().all().extract().body()//.as(UserData.class)
                    .jsonPath().getList(".", UserData.class);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
        }
    }
}


