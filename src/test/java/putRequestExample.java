import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class putRequestExample {

    @Test
    public void testPutRequest() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Resource ID to be updated
        int userId = 2; // Example: Updating user with ID 2

        // Data to be updated in the request body
        String requestBody = "{\n" +
                "  \"name\": \"Jackson Doe\",\n" +
                "  \"job\": \"Software Engineer QA\"\n" +
                "}";

        // Sending PUT request and capturing the response
        Response response =
                given()
                        .header("Content-Type", "application/json")  // Set Content-Type header
                        .body(requestBody)  // Set the body with updated data
                        .pathParam("userId", userId)  // Set the userId parameter
                        .when()
                        .put("/users/{userId}")  // PUT request on the resource
                        .then()
                        .statusCode(200)  // Verify that the status code is 200 (OK)
                        .extract().response(); // Extract the response object

        // Print the response (optional)
        System.out.println("Response: " + response.asString());

        // Additional assertions on the response
        assertThat(response.statusCode(), is(200)); // Assert the status code
        assertThat(response.jsonPath().getString("name"), equalTo("Jackson Doe"));
        assertThat(response.jsonPath().getString("job"), equalTo("Software Engineer QA"));
    }
}
