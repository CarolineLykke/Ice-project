public class Customer extends User {

    private String name;
    private String lastname;
    private int phoneNumber;
    private String address;
    public Customer(int id, String username, String password, String rights, String name, String lastname, int phoneNumber, String address) {
        super(id, username, password, rights);

    }
}
