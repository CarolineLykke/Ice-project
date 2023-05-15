public class Mechanic extends User{

    boolean Status;
    public Mechanic(String username, String password, int rights, boolean status) {
        super(username, password, rights);
        this.Status=status;
    }

}
