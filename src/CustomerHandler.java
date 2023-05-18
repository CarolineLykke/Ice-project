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
    static List<CustomerCar> ccjoin = getCustomerCar();

    Scanner scan = new Scanner(System.in);
    CarHandler carHandler = new CarHandler();

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
        Scanner customerScanner = new Scanner(System.in);
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println((i + 1) + ". " + customers.get(i).getName() + " , " + customers.get(i).getLastname());
        }

        System.out.print("Please enter the number of the Customer you'd like to select: ");
        int selection = customerScanner.nextInt();
        customerScanner.nextLine();

        if (selection < 1 || selection > customers.size()) {
            System.out.println("Invalid Customer number.");
            return;
        }

        Customer selectedCustomer = customers.get(selection - 1);
        System.out.println("Selected Customer: " + selectedCustomer.getName());
        //System.out.println("The car make and model is: " + selectedCar.getCarMake() +" "+ selectedCustomer.getCarModel());

        if (selectedCustomer.getName().contains("")) {
           Scanner scanner = new Scanner(System.in);

            System.out.println("Choose between the following options:");
            System.out.println("1. Add a new car");
            System.out.println("2. Show the car that belongs to " + " " + selectedCustomer.getName() +" "+ selectedCustomer.getLastname());

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                carHandler.createCar();
                carHandler.savecars();
            } else if (choice.equals("2")) {
               // System.out.println(getCustomerCar());

            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
            //System.out.println("The car make and model is: " + selectedCustomer.getCarMake() +" "+ selectedCustomer.getCarModel());
            //MovieMenu movieMenu = new MovieMenu();
            //movieMenu.displayMenu(selectedMovies);
        }

    public static List<CustomerCar> getCustomerCar() {
        List<CustomerCar> ccjoin = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database loading...");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "select * from ccjoin inner join customer on ccjoin.customerId=customer.id inner join cars on customer.id=cars.id ";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String customerID = rs.getString("id");
                String customerName = rs.getString("forName");
                String customerLastName = rs.getString("lastName");
                String carID = rs.getString("id");
                String carMake = rs.getString("make");
                String carModel = rs.getString("model");
                String carRegnr = rs.getString("regnr");
                String carKm = rs.getString("km");


                CustomerCar customercar = new CustomerCar(customerID,customerName,customerLastName,carID,carMake,carModel,carRegnr,carKm);
                ccjoin.add(customercar);


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

        }
        return ccjoin;
    }

    public void showAllCustomerCar() {
        Scanner customerScanner = new Scanner(System.in);
        for (int i = 0; i < ccjoin.size(); i++) {
            CustomerCar customercar = ccjoin.get(i);
            System.out.println((i + 1) + ". " + ccjoin.get(i).getForName() + " , " + ccjoin.get(i).getCarMake());
        }

        System.out.print("Please enter the number of the Customer you'd like to select to see the car information: ");
        int selection = customerScanner.nextInt();
        customerScanner.nextLine();

        if (selection < 1 || selection > ccjoin.size()) {
            System.out.println("Invalid Customer number.");
            return;
        }

        CustomerCar selectedCustomer = ccjoin.get(selection - 1);
        System.out.println("Selected Customer: " + selectedCustomer.getForName());
        System.out.println("The car make and model is: " + selectedCustomer.getCarMake() +" "+ selectedCustomer.getCarModel());

        if (selectedCustomer.getForName().contains("")) {
            System.out.println("The car make and model is: " + selectedCustomer.getCarMake() +" "+ selectedCustomer.getCarModel());
            //MovieMenu movieMenu = new MovieMenu();
            //movieMenu.displayMenu(selectedMovies);
        }
    }

    /*public void showAllCarCustomer() {
        Scanner customerScanner = new Scanner(System.in);
        for (int i = 0; i < ccjoin.size(); i++) {
            CustomerCar customercar = ccjoin.get(i);
            System.out.println((i + 1) + ". " + ccjoin.get(i).getCarMake() + " , " + ccjoin.get(i).getCarModel() + " , " + ccjoin.get(i).getCarRegnr());
        }

        System.out.print("Please enter the number of the Car you'd like to select to see the customer informations: ");
        int selection = customerScanner.nextInt();
        customerScanner.nextLine();

        if (selection < 1 || selection > ccjoin.size()) {
            System.out.println("Invalid Car number.");
            return;
        }

        CustomerCar selectedCustomer = ccjoin.get(selection - 1);
        System.out.println("Selected Car: " + selectedCustomer.getCarRegnr() +  "\n" + "The customer for name and last name is: " + selectedCustomer.getForName() +" "+ selectedCustomer.getLastName());

        if (selectedCustomer.getForName().contains("")) {
            System.out.println("TEST");
            //MovieMenu movieMenu = new MovieMenu();
            //movieMenu.displayMenu(selectedMovies);
        }
    }*/
//se hvilken bil kunden har
    public void search() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a customer name to search: ");
        String search = scanner.nextLine();

        List<Customer> matching = searchCustomerByName(search);

        if (matching.size() == 0) {
            System.out.println("No matching found.");
            return;
        }

        System.out.println("Matching customer:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName());
        }

        System.out.print("Enter the number of the customer you want to select: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (movieIndex < 1 || movieIndex > customers.size()) {
            System.out.println("Invalid customer number.");
            return;
        }

        Customer selected = customers.get(movieIndex - 1);
        System.out.println("Selected customer: " + selected.getName());

        // Call your function on selectedMovie here
        if (selected.getName().contains("")) {
            System.out.println("Test");
        }
    }

    public List<Customer> searchCustomerByName(String sName) {
        List<Customer> matchingNames = new ArrayList<>();
        for (Customer customer : this.customers) {
            String name = customer.getName();
            if (name.toLowerCase().contains(sName.toLowerCase())) {
                matchingNames.add(customer);
            }
        }
        return matchingNames;
    }
}
