import java.sql.*;
import java.util.ArrayList;

public class Mechanic {

    boolean Status;
    String id;
    String username;
    String password;
    int rights;
    String assignment;

    public Mechanic(String id, String username, String password, int rights, boolean status) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.rights=rights;
        this.Status=status;
    }

    public Mechanic(String id, boolean status, String assignment, String username) {
        this.id=id;
        this.Status=status;
        this.assignment = assignment;
        this.username = username;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getId() {
        return id;
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

    public String getAssignment() {
        return assignment;
    }

    public String setAssignment(String assignment) {
        return assignment;
    }
}
