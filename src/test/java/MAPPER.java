import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MAPPER {

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
    public void testCreateAndGetUserUsingObjectMapper() throws Exception {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Step 1: Create the user using the POST request
        CreateUserRequest createUserRequest = new CreateUserRequest("John Doe", "Software Developer");

        // Convert the CreateUserRequest POJO to a JSON string
        String requestBody = objectMapper.writeValueAsString(createUserRequest);

        // Send POST request to create the user
        Response postResponse = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)  // Send JSON body in the POST request
                .post("https://reqres.in/api/users");

        // Deserialize the POST response to a UserResponse object
        UserResponse postUserResponse = objectMapper.readValue(postResponse.asString(), UserResponse.class);

        // Print POST response
        System.out.println("User Created - Name: " + postUserResponse.getName());
        System.out.println("User Created - Job: " + postUserResponse.getJob());
        System.out.println("User Created - ID: " + postUserResponse.getId());
        System.out.println("User Created - Created At: " + postUserResponse.getCreatedAt());

        // Assert that the response is correct
        Assert.assertEquals(postUserResponse.getName(), "John Doe");
        Assert.assertEquals(postUserResponse.getJob(), "Software Developer");

        // Step 2: Get the user details using the ID from the POST response
        String userId = postUserResponse.getId();

        // Send GET request to retrieve user details by ID
        Response getResponse = RestAssured.get("https://reqres.in/api/users/" + userId);

        // Deserialize the GET response to a UserResponse object
        UserResponse getUserResponse = objectMapper.readValue(getResponse.asString(), UserResponse.class);

        // Print GET response
        System.out.println("User Fetched - Name: " + getUserResponse.getName());
        System.out.println("User Fetched - Job: " + getUserResponse.getJob());

        // Assert that the GET response matches the POST request data
//        Assert.assertEquals(getUserResponse.getName(), "John Doe");
//        Assert.assertEquals(getUserResponse.getJob(), "Software Developer");
    }
}
