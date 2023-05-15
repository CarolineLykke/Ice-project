public class Mechanic extends User{

    boolean Status;
    public Mechanic(String id, String username, String password, int rights, boolean status) {
        super(id, username, password, rights);
        this.Status=status;
    }

}
