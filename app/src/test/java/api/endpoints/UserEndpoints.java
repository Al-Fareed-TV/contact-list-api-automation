package api.endpoints;

import api.payload.LoginPayload;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static Response createUser(User payload) {
        return given()
                 .contentType(ContentType.JSON)
                 .accept(ContentType.JSON)
                 .body(payload)
                .when()
                 .post(Routes.getUrl("post"));
    }
    public static Response getUser(String token){
        return given()
                .header("Authorization",token)
                .get(Routes.getUrl("get"));
    }
    public static Response updateUser(User payload,String token) {
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(Routes.getUrl("update"));
    }
    public static Response logInUser(LoginPayload payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .post(Routes.getUrl("login"));
    }
    public static Response logOutUser(String token){
        return given()
                .header("Authorization",token)
                .post(Routes.getUrl("logout"));
    }
    public static Response deleteUser(String token){
        return given()
                .header("Authorization",token)
                .delete(Routes.getUrl("delete"));
    }
}
