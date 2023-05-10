public class Customer extends User {

    private String name;
    private String lastname;
    private int phoneNumber;
    private String address;
    public Customer(int id, String username, String password, String rights, String name, String lastname, int phoneNumber, String address) {
        super(id, username, password, rights);
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
