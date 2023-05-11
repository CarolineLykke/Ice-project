public abstract class User {
    private String Id;
    private String username;
    private String password;
    private String rights;

    public User(String id, String username, String password, String rights) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.rights = rights;
    }

    public String getId() {
        return Id;
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
