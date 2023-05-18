import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCar {

    private String customerId;
    private String forName;
    private String lastName;
    private String carID;
    private String carMake;
    private String carModel;
    private String carRegnr;
    private String carKm;
    static List<CustomerCar> ccjoin = getCustomerCar();

    public CustomerCar(String customerId, String forName, String lastName, String carID, String carMake, String carModel, String carRegnr, String carKm) {
        this.customerId = customerId;
        this.forName = forName;
        this.lastName = lastName;
        this.carID = carID;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carRegnr = carRegnr;
        this.carKm = carKm;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getForName() {
        return forName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCarID() {
        return carID;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarRegnr() {
        return carRegnr;
    }

    public String getCarKm() {
        return carKm;
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
}
