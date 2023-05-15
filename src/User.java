public class User {
    private String Id;
    private String username;
    private String password;
    private int rights;

    public User(String username, String password, String id, int rights) {
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

    public int getRights() {
        return rights;
    }
}
