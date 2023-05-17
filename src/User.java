public class User {
    private String Id;
    private String username;
    private String password;
    private int rights;

    public User() {

    }


    public User(String username, String password, int rights) {
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