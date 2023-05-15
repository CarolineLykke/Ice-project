import java.util.Scanner;

public class Foreman {
    private String Id;
    private String username;
    private String password;
    private int rights;
    User user;
    public Foreman(String id, String username, String password, int rights) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.rights = rights;
    }

    public String assignmentInfoMsg() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the necessary info like model, make, the error and the approx time of the assignment.");
        String msg = scanner.nextLine();
        return msg;
    }
}
