package in.reqres;

public enum Endpoints {
    LIST_USERS("/api/users?page=2"),
    SINGLE_USER("/api/users/2"),
    CREATE("/api/users"),
    DELETE("/api/users/2"),
    UPDATE("/api/users/2");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
