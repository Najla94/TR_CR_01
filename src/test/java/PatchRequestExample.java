import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PatchRequestExample {

    @Test
    public void testPatchRequest() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // User ID to be updated
        int userId = 1;

        // Data to be updated in the request body (only the email)
        String requestBody = "{\n" +
                "  \"email\": \"newemail@example.com\"\n" +
                "}";

        // Sending PATCH request
        Response response =
                given()
                        .header("Content-Type", "application/json")  // Set Content-Type header
                        .body(requestBody)  // Request body (only email update)
                        .pathParam("userId", userId)  // Path parameter (userId)
                        .when()
                        .patch("/users/{userId}")  // PATCH request to partially update user
                        .then()
                        .statusCode(200)  // Verify the status code
                        .extract().response(); // Extract the response

        // Print the response body
        System.out.println("Response: " + response.asString());
    }
}
