package api.tests;

import api.endpoints.ContactEndpoints;
import api.endpoints.UserEndpoints;
import api.payload.Contact;
import api.Generator.PayloadGenerator;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactTests {
    Contact contactPayload;
    String token;
    User userPayload;
    PayloadGenerator payloadGenerator;
    String id;
    @BeforeClass
    public void setup(){
        payloadGenerator = new PayloadGenerator();
        contactPayload = payloadGenerator.ContactPayloadGenerator();
        userPayload = payloadGenerator.userPayloadGenerator();

        Response response = UserEndpoints.createUser(userPayload);
        token = response.jsonPath().getString("token");

    }

    @Test(priority = 0)
    public void addContactTest() {
        Response response = ContactEndpoints.addContact(contactPayload, token);
        id = response.jsonPath().getString("_id");
        response.then().log().body().statusCode(201);
    }
    @Test(priority = 1)
    public void getContactsList(){
        Response response = ContactEndpoints.getContactList(token);
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 2)
    public void getContact() {
        System.out.println("--Get Contact through Id--");
        Response response = ContactEndpoints.getContact(token, id);
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 3)
    public void updateContactsUsingPutMethod() {
        Contact updateContactPayload = payloadGenerator.updateContactPayload();
        Response response = ContactEndpoints.updateContactPutMethod(token, updateContactPayload, id);
        response.then().statusCode(200);
    }

    @Test(priority = 4)
    public void updateContactUsingPatchMethod() {
        Contact updateContactPayload = payloadGenerator.payloadForPatchMethod();
        Response response = ContactEndpoints.updateContactPatchMethod(token, updateContactPayload, id);
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 5)
    public void deleteContact() {
        Response response = ContactEndpoints.DeleteContact(token, id);
        response.then().statusCode(200);
    }
}
