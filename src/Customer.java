import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer  {

    private String id;
    private int rights;
    private String name;
    private String lastname;
    private int phoneNumber;
    private String address;

    public Customer(String id, int rights, String name, String lastname, int phoneNumber, String address) {
        this.id = id;
        this.rights = rights;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public int getRights() {
        return rights;
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

    private static List<Customer> readCustomerFromDatabase() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM movies";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("id");
                int rights = rs.getint("right");
                String  = rs.getString("category");
                String rating = rs.getString("Rating");
                String id = rs.getString("id");
                //Create movie object and add to list
                Customer customers = new Customer();
                Customer.add(customers);
            }

            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return customers;
    }
}