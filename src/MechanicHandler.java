import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MechanicHandler {
    static ArrayList<Mechanic> mechanics = new ArrayList<>();

    private static String id;
    private static boolean status;

    public void loadMechanicStatus(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database loading mechanic status");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM status";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String id = rs.getString("id");
                boolean status = rs.getBoolean("status");
                mechanics.add(new Mechanic(id, status));
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
    }

    public static List<Mechanic> readMechanicStatus() {
        List<Mechanic> mechanics = new ArrayList<>();
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
            String sql = "SELECT * FROM status";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("id");
                boolean status = rs.getBoolean("status");
                //Create movie object and add to list
                Mechanic mechanic = new Mechanic(id,status);
                mechanics.add(mechanic);
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

        return mechanics;
    }

    public void showMechanics() {
        Scanner movieScanner = new Scanner(System.in);
        for (int i = 0; i < mechanics.size(); i++) {
            Mechanic mechanic = mechanics.get(i);
            System.out.println((i + 1) + ". " + mechanics.get(i).isStatus());
        }

        System.out.print("Please enter the number of the movie you'd like to select: ");
        int selection = movieScanner.nextInt();
        movieScanner.nextLine();

        if (selection < 1 || selection > mechanics.size()) {
            System.out.println("Invalid movie number.");
            return;
        }

        Mechanic selectedMovies = mechanics.get(selection - 1);
        System.out.println("Selected movie: " + selectedMovies.getId());

        if (selectedMovies.getId().contains("")) {
            System.out.println("TEST");
            //MovieMenu movieMenu = new MovieMenu();
            //movieMenu.displayMenu(selectedMovies);
        }
    }

    /* public void displayMechanicStatus(){
        for (Mechanic mechanic : mechanics) {
            id = getId();
            status= isStatus();
            // Perform operations with each mechanic
            System.out.println(this);
        }
    }*/

    public static String getId() {
        return id;
    }

    public static boolean isStatus() {
        return status;
    }


    @Override
    public String toString(){
        return "Mechanic Id: " + getId() + "and the status is: " + isStatus();
    }
}
