package api.routes;

public class Routes {
    public static String USER_BASE_URI = "https://thinking-tester-contact-list.herokuapp.com/users";
    public static String CONTACT_BASE_URI = "https://thinking-tester-contact-list.herokuapp.com/contacts";

    public static String getUrl(String endpoint) {
        switch (endpoint.toLowerCase()) {
            case "post":
                return USER_BASE_URI;
            case "get":
            case "update":
            case "delete":
                return USER_BASE_URI + "/me";
            case "login":
                return USER_BASE_URI + "/login";
            case "logout":
                return USER_BASE_URI + "/logout";
            case "contact":
                return CONTACT_BASE_URI;
            case "contact_id":
                return CONTACT_BASE_URI + "/{id}";
            default:
                return null;
        }
    }
}
