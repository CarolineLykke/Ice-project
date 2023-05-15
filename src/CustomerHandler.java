import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class CustomerHandler {
    private static String email;
    private static String name;
    private static String lastName;
    private static int phoneNumber;
    private static String address;
    static List<Customer> customers = readCustomerFromDatabase();

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

    public static List<Customer> readCustomerFromDatabase() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM customer";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String email = rs.getString("email");
                String name = rs.getString("forName");
                String lastName = rs.getString("lastName");
                int phoneNumber = rs.getInt("phoneNumber");
                String address = rs.getString("address");
                //Customer customers = new Customer(email, name, lastName, phoneNumber, address);
                Customer customer = new Customer(email,name,lastName,phoneNumber,address);
                customers.add(customer);
                //customers.add(new Customer(email,name,lastName,phoneNumber,address));
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

    public void showAllCustomers() {
        Scanner movieScanner = new Scanner(System.in);
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println((i + 1) + ". " + customers.get(i).getName() + " , " + customers.get(i).getLastname());
        }

        System.out.print("Please enter the number of the Customer you'd like to select: ");
        int selection = movieScanner.nextInt();
        movieScanner.nextLine();

        if (selection < 1 || selection > customers.size()) {
            System.out.println("Invalid Customer number.");
            return;
        }

        Customer selectedMovies = customers.get(selection - 1);
        System.out.println("Selected Customer: " + selectedMovies.getName());

        if (selectedMovies.getName().contains("")) {
            System.out.println("TEST");
            //MovieMenu movieMenu = new MovieMenu();
            //movieMenu.displayMenu(selectedMovies);
        }
    }

    /*
    public static String getEmail() {
        return email;
    }

    public static String getName() {
        return name;
    }

    public static String getLastName() {
        return lastName;
    }

    public static int getPhoneNumber() {
        return phoneNumber;
    }

    public static String getAddress() {
        return address;
    }

    public void displayCustomers(){
        for (Customer customer : customers){
            email = getEmail();
            name = getName();
            lastName = getLastName();
            phoneNumber = getPhoneNumber();
            address = getAddress();
            System.out.println(this);

        }
    }

    @Override
    public String toString(){
        return "email: " +  getEmail() + " name: " + getName();
    }*/
}
