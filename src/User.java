public abstract class User {
    private int id;
    private String username;
    private String password;
    private String rights;

    public User(int id, String username, String password, String rights) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rights = rights;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRights() {
        return rights;
    }
}
