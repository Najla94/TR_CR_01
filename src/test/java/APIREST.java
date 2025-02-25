import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIREST {

    @Test
    public void createUserTest() {
        // Create the request body (POJO)
        CreateUserRequest createUserRequest = new CreateUserRequest("John Doe", "Software Developer");

        // Send POST request with the request body (as JSON)
        CreateUserResponse createUserResponse = RestAssured
                .given()
                .contentType("application/json")  // Set content type as JSON
                .body(createUserRequest)          // Send the request body
                .post("https://reqres.in/api/users")  // API endpoint
                .as(CreateUserResponse.class);    // Deserialize response to CreateUserResponse class

        // Print out the response to verify the creation
        System.out.println("Response Name: " + createUserResponse.getName());
        System.out.println("Response Job: " + createUserResponse.getJob());
        System.out.println("Response ID: " + createUserResponse.getId());
        System.out.println("Response Created At: " + createUserResponse.getCreatedAt());

        // Optional: Assert the response for validation
        Assert.assertEquals(createUserResponse.getName(), "John Doe");
        Assert.assertEquals(createUserResponse.getJob(), "Software Developer");
    }
}
