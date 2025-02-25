import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APIDeleteTest {

    @Test
    void deleteUserTest() {
        // Send DELETE request to delete a user with a specific ID
        int userId = 2;  // Example user ID to delete
        Response response = RestAssured
                .delete("https://reqres.in/api/users/" + userId);

        // Print out the status code and response body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // You can also assert the status code to verify if the deletion was successful
        response.then().statusCode(204); // Status code 204 indicates successful deletion without body
    }
}
