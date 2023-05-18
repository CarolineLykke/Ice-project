import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarHandler {
    private static String make;
    private static String model;
    private static String regnr;
    private static int km;

    static List<Car> cars = readCarFromDatabase();

    Scanner scan = new Scanner(System.in);

    public void createCar() {

        System.out.println("Please enter a make: ");
        String make = scan.nextLine();
        System.out.println("Please enter a model: ");
        String model = scan.nextLine();
        System.out.println("Please enter a regnr: ");
        String regnr = scan.nextLine();
        System.out.println("Please enter a km: ");
        int km = Integer.parseInt(scan.nextLine());
        cars.add(new Car(make,model,regnr,km));
    }

    public void savecars() {
        //UserHandler userHandler = new UserHandler();

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading saveusers");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            // the mysql insert statement
            String sql = "INSERT INTO cars (make,model,regnr,km) VALUES (?, ?, ?, ?)";

            //INSERT INTO streaming.users (UserName,password) VALUES (?, ?)

            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
            for (Car car : cars) {
                stmt.setString(1, car.getMake());
                stmt.setString(2, car.getModel());
                stmt.setString(3, car.getRegnr());
                stmt.setInt(4, car.getKm());

            }


            // execute the preparedstatement
            stmt.executeUpdate();


            conn.close();
            //DashBoard.setupDashboard();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public static List<Car> readCarFromDatabase() {
        List<Car> cars = new ArrayList<>();
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
            String sql = "SELECT * FROM cars";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String make = rs.getString("make");
                String model = rs.getString("model");
                String regnr = rs.getString("regnr");
                int km = rs.getInt("km");
                Car car = new Car(make, model, regnr, km);
                cars.add(car);
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

        return cars;
    }

    public void showAllCars() {
        Scanner carScanner = new Scanner(System.in);
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.println((i + 1) + ". " + cars.get(i).getMake() + " , " + cars.get(i).getModel()+ " , " + cars.get(i).getRegnr()+ " , " + cars.get(i).getKm());
        }

        System.out.print("Please enter the number of the car you'd like to select: ");
        int selection = carScanner.nextInt();
        carScanner.nextLine();

        if (selection < 1 || selection > cars.size()) {
            System.out.println("Invalid Customer number.");
            return;
        }

        Car selectedCar = cars.get(selection - 1);
        System.out.println("Selected Car: " + selectedCar.getRegnr());
        //+  "\n" + "The customer for name and last name is: " + selectedCar.getForName() +" "+ selectedCustomer.getLastName());

        if (selectedCar.getRegnr().contains("")) {
            System.out.println("Choose between the following options:");
            System.out.println("1. Add car to customer");
            System.out.println("2. Reapir something on the car");
            System.out.println("3. The car is ready?");
            //MovieMenu movieMenu = new MovieMenu();

            //movieMenu.displayMenu(selectedMovies);
        }
    }
}
