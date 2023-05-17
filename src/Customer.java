import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer  {

    private String email;
    private String name;
    private String lastName;
    private int phoneNumber;
    private String address;

    public Customer(String email, String name, String lastName, int phoneNumber, String address) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public  String getName() {
        return name;
    }

    public  String getLastname() {
        return lastName;
    }

    public  int getPhoneNumber() {
        return phoneNumber;
    }

    public  String getAddress() {
        return address;
    }

    //se kunder


    //se ændringer på bilen
    public static void getChangesToTheCar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database loading SavedMovies");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "select * from movies join watchedmovies on movies.id=watchedmovies.moviesid";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String userID = rs.getString("UserID");
                String movieID = rs.getString("name");

                if (Userhandler.getId().equals(userID)) {
                    System.out.println(movieID);
                }
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
    }

}