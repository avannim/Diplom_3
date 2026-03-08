package enums;

public enum APIEndpoints {
    //User
    CREATE_USER("/api/auth/register"),
    ACTIONS_USER("/api/auth/user"),
    LOGIN_USER("/api/auth/login");

    private final String path;

    APIEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
