import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APITest {

    @Test
    void testWithPojo() {
        // Send GET request and deserialize the response into the UsersResponse POJO
        UsersResponse usersResponse = RestAssured
                .get("https://reqres.in/api/users")
                .as(UsersResponse.class);  // Deserialize JSON into the UsersResponse class

        // Print out the details of the users
        System.out.println("Page: " + usersResponse.getPage());
        System.out.println("Per Page: " + usersResponse.getPer_page());
        System.out.println("Total Users: " + usersResponse.getTotal());
        System.out.println("Total Pages: " + usersResponse.getTotal_pages());

        // Iterate over the list of users and print their details
        for (User user : usersResponse.getData()) {
            System.out.println("User ID: " + user.getId());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Name: " + user.getFirst_name() + " " + user.getLast_name());
            System.out.println("Avatar URL: " + user.getAvatar());
            System.out.println("--------------");
        }

        // Print support info
        System.out.println("Support URL: " + usersResponse.getSupport().getUrl());
        System.out.println("Support Text: " + usersResponse.getSupport().getText());
    }
}
