import java.sql.*;
import java.util.ArrayList;

public class Mechanic {

    boolean Status;
    String id;
    String username;
    String password;
    int rights;

    public Mechanic(String id, String username, String password, int rights, boolean status) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.rights=rights;
        this.Status=status;
    }

    public Mechanic(String id, boolean status) {
        this.id=id;
        this.Status=status;
    }






}
