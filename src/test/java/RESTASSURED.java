import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RESTASSURED {

    // POJO class for creating a user (POST request body)
    public static class CreateUserRequest {
        private String name;
        private String job;

        // Constructor
        public CreateUserRequest(String name, String job) {
            this.name = name;
            this.job = job;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }

    // POJO class for the response (used for both POST and GET)
    public static class UserResponse {
        private String name;
        private String job;
        private String id;
        private String createdAt;
        private String updatedAt;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    @Test
    public void testCreateAndGetUser() {
        // Step 1: Create a new user using POST request
        CreateUserRequest createUserRequest = new CreateUserRequest("John Doe", "Software Developer");

        // Send POST request to create a new user
        UserResponse createUserResponse = RestAssured
                .given()
                .contentType("application/json")
                .body(createUserRequest)  // Send the request body
                .post("https://reqres.in/api/users")
                .as(UserResponse.class);  // Convert the response JSON into UserResponse POJO

        // Print out the response from POST request
        System.out.println("User Created - Name: " + createUserResponse.getName());
        System.out.println("User Created - Job: " + createUserResponse.getJob());
        System.out.println("User Created - ID: " + createUserResponse.getId());
        System.out.println("User Created - Created At: " + createUserResponse.getCreatedAt());

        // Assert that the response is correct (e.g., name and job)
        Assert.assertEquals(createUserResponse.getName(), "John Doe");
        Assert.assertEquals(createUserResponse.getJob(), "Software Developer");

        // Step 2: Use the ID from POST response to make a GET request to fetch the user details
        String userId = createUserResponse.getId();

        // Send GET request to retrieve the user details using the user ID
        Response getUserResponse = RestAssured
                .get("https://reqres.in/api/users/" + userId);

        // Print out the response from GET request
        System.out.println("User Fetched - Name: " + getUserResponse.jsonPath().getString("data.first_name"));
        System.out.println("User Fetched - Job: " + getUserResponse.jsonPath().getString("data.job"));

        // Assert that the GET response matches the data sent in the POST request
//        Assert.assertEquals(getUserResponse.jsonPath().getString("data.first_name"), "John Doe");
//        Assert.assertEquals(getUserResponse.jsonPath().getString("data.job"), "Software Developer");
    }
}
