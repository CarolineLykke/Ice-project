import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.ArrayList;

public class CustomerHandler {
    static ArrayList<Customer> customers = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    public void createCustomer() {

        System.out.println("Please enter a email: ");
        String email = scan.nextLine();
        System.out.println("Please enter a name: ");
        String name = scan.nextLine();
        System.out.println("Please enter a lastName: ");
        String lastName = scan.nextLine();
        System.out.println("Please enter a phoneNumber: ");
        int phoneNumber = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter a address: ");
        String address = scan.nextLine();
        customers.add(new Customer(email,name,lastName,phoneNumber,address));
    }
    public void saveUsers() {
        //UserHandler userHandler = new UserHandler();

        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading saveusers");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            // the mysql insert statement
            String sql = "INSERT INTO customer (email,forName,lastname,phoneNumber,address) VALUES (?, ?, ?, ?, ?)";

            //INSERT INTO streaming.users (UserName,password) VALUES (?, ?)

            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
            for(Customer customer:customers){
                stmt.setString ( 1,customer.getEmail());
                stmt.setString ( 2,customer.getName());
                stmt.setString ( 3,customer.getLastname());
                stmt.setInt ( 4,customer.getPhoneNumber());
                stmt.setString ( 5,customer.getAddress());
            }


            // execute the preparedstatement
            stmt.executeUpdate();


            conn.close();
            //DashBoard.setupDashboard();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
