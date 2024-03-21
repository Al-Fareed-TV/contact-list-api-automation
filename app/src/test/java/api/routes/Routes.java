package api.routes;

public class Routes {
    public static String USER_BASE_URI = "https://thinking-tester-contact-list.herokuapp.com/users";
    public static String CONTACT_BASE_URI = "https://thinking-tester-contact-list.herokuapp.com/contacts";

    public static String getUrl(String endpoint) {
        return switch (endpoint.toLowerCase()) {
            case "post" -> USER_BASE_URI;
            case "get", "update", "delete" -> USER_BASE_URI + "/me";
            case "login" -> USER_BASE_URI + "/login";
            case "logout" -> USER_BASE_URI + "/logout";
            case "contact" -> CONTACT_BASE_URI;
            case "contact_id" -> CONTACT_BASE_URI + "/{id}";
            default -> null;
        };
    }
}
