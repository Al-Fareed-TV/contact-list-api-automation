package api.endpoints;

import api.payload.Contact;
import api.routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ContactEndpoints {
    public static Response addContact(Contact contactPayload,String token){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .body(contactPayload)
                .when()
                .post(Routes.getUrl("contact"));
    }
    public static Response getContact(String token,String id){
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .get(Routes.getUrl("contact_id"));
    }
    public static Response getContactList(String token){
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(Routes.getUrl("contact"));
    }
    public static Response updateContactPutMethod(String token,Contact payload,String id){
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .body(payload)
                .put(Routes.getUrl("contact_id"));
    }
    public static Response updateContactPatchMethod(String token,Contact payload,String id){
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .body(payload)
                .patch(Routes.getUrl("contact_id"));
    }
    public static Response DeleteContact(String token,String id){
        return given()
                .header("Authorization",token)
                .pathParam("id",id)
                .delete(Routes.getUrl("contact_id"));
    }
}
