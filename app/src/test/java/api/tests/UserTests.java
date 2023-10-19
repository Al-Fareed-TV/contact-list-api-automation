package api.tests;

import api.Generator.PayloadGenerator;
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
    PayloadGenerator payloadGenerator;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        payloadGenerator = new PayloadGenerator();
        userPayload = payloadGenerator.userPayloadGenerator();
    }

    @Test(priority = 1)
    public void addUserTest() {
        System.out.println("--Adding user--");
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().body().statusCode(201);
        BearerToken = response.jsonPath().getString("token");
    }

    @Test(priority = 2)
    public void getUserTest() {
        System.out.println("--Retrieving user--");
        Response response = UserEndpoints.getUser(BearerToken);
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 3)
    public void updateUserTest() {
        System.out.println("--Updating user--");
        userPayload.setFirstName("fName");
        userPayload.setLastName("lName");
        Response response = UserEndpoints.updateUser(userPayload, BearerToken);
        response.then().log().body().statusCode(200);

        Assert.assertEquals(userPayload.getFirstName(),"fName");
        Assert.assertEquals(userPayload.getLastName(),"lName");
    }

    @Test(priority = 4)
    public void logOutTest() {
        System.out.println("--Logging out the user--");
        Response response = UserEndpoints.logOutUser(BearerToken);
        response.then().statusCode(200);
    }

    @Test(priority = 5)
    public void logInTest() {
        System.out.println("--Logging In user--");
        loginPayload = new LoginPayload();
        loginPayload.setEmail(userPayload.getEmail());
        loginPayload.setPassword(userPayload.getPassword());
        Response response = UserEndpoints.logInUser(loginPayload);
        BearerToken = response.jsonPath().getString("token");
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 6)
    public void deleteUserTest() {
        System.out.println("--Deleting the user--");
        Response response = UserEndpoints.deleteUser(BearerToken);
        response.then().statusCode(200);
    }
}
