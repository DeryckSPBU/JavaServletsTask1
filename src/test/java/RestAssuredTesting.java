import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssuredTesting {

    @Test
    public void getMethod() {
        when().get("/").then().statusCode(200);
    }

    @Test
    public void postMethod() {
        when().post("/").then().statusCode(200);
    }
}
