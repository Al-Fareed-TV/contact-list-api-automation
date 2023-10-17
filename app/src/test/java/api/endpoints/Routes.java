package api.endpoints;
public class Routes {
    public static String BASE_URI = "https://thinking-tester-contact-list.herokuapp.com/users";

    public static String getUrl(String endpoint) {
        switch (endpoint.toLowerCase()) {
            case "post":
                return BASE_URI;
            case "get":
            case "update":
            case "delete":
                return BASE_URI + "/me";
            case "login":
                return BASE_URI + "/login";
            case "logout":
                return BASE_URI + "/logout";
            default:
                return BASE_URI;
        }
    }
}
