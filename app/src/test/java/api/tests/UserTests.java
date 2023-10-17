package api.tests;

import api.endpoints.UserEndpoints;
import api.payload.LoginPayload;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayload;
    LoginPayload loginPayload;
    String BearerToken;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
    }

    @Test(priority = 1)
    public void addUserTest() {
        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 201);
        BearerToken = response.jsonPath().getString("token");
    }

    @Test(priority = 2)
    public void getUserTest() {
        Response response = UserEndpoints.getUser(BearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void updateUserTest() {
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        Response response = UserEndpoints.updateUser(userPayload, BearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void logOutTest() {
        Response response = UserEndpoints.logOutUser(BearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void logInTest() {
        loginPayload = new LoginPayload();
        loginPayload.setEmail(userPayload.getEmail());
        loginPayload.setPassword(userPayload.getPassword());
        Response response = UserEndpoints.logInUser(loginPayload);
        BearerToken = response.jsonPath().getString("token");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6)
    public void deleteUserTest() {
        Response response = UserEndpoints.deleteUser(BearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
