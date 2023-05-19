import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MechanicHandler {
    static List<Mechanic> mechanics = readMechanicStatus();

    private TextUI textUI;

    private static String id;

    private Mechanic mechanic;
    private CarHandler carHandler;

    Scanner scan;


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
                String assignment = rs.getString("assignment");
                //Create mechanic object and add to list
                Mechanic mechanic = new Mechanic(id,status,assignment);
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
        try {
            for (int i = 0; i < mechanics.size(); i++) {
                Mechanic mechanic = mechanics.get(i);
                if (mechanic.isStatus() == true) {
                    System.out.println((i + 1) + ". " + mechanics.get(i).getId() + "," + mechanics.get(i).isStatus());
                }
            }
            if (mechanics.isEmpty()) {
                System.out.println("No mechanics are available right now");
                textUI.backToMenu();
            }
        } catch (NullPointerException e) {
            System.out.println("can't access the mechanics list");
        }
    }

    // a function over Mechanics that aren't available and what assignment they are working on
    public ArrayList<String> getWorkingMechanics() {
        ArrayList<String> workingMechanics = new ArrayList<>();
        for (int i = 0; i < mechanics.size(); i++) {
            Mechanic mechanic = mechanics.get(i); // initialize mechanic
            if (mechanic.isStatus() == false) {
                //names all the unavailable mechanics with id and their assignments
                String mechanicInfo = (i + 1) + ". " + mechanics.get(i).getId() + mechanic.getUsername()+ ": " + mechanic.getAssignment();
                 // HERE call assignment function or set assignment??
                workingMechanics.add(mechanicInfo); // and adding it to the workingMechanics array
                System.out.println(mechanicInfo);
            } else {
                System.out.println("All mechanics are available");
                textUI.backToMenu();
            }

        }
        return workingMechanics;
    }

    public void selectMechanic(){
        Scanner mechanicScanner = new Scanner(System.in);
        System.out.print("Please enter the number of the mechanic you'd like to select: ");
        int selection = mechanicScanner.nextInt();
        mechanicScanner.nextLine();

        if (selection < 1 || selection > mechanics.size()) {
            System.out.println("Invalid mechanic number.");
            return;
        }

        Mechanic selectedMechanic = mechanics.get(selection - 1);
        System.out.println("Selected Mechanic: " + selectedMechanic.getId());

        if (selectedMechanic.getId().contains("")) {
            System.out.println(getId());
        }
    }

    public void bookMechanic() {
        try {
            scan = new Scanner(System.in);

            // call the car db and add a mechanic to a car(connect a mechanic id with a car regnr)
            // Create an instance of the CarHandler class
            CarHandler carHandler = new CarHandler();
            carHandler.readCarFromDatabase();
            carHandler.showAllCarsToForeman();
            System.out.print("Please enter the number of the car you'd like to select: ");
            int selection = scan.nextInt();
            scan.nextLine();

            if (selection < 1 || selection > CarHandler.cars.size()) {
                System.out.println("Invalid Car number.");
                return;
            }

            Car selectedCar = CarHandler.cars.get(selection - 1);
            String carId = selectedCar.getId();

            for (int i = 0; i < mechanics.size(); i++) {
                Mechanic mechanic = mechanics.get(i);
                if (mechanic.isStatus() == true) {
                    // setter for mechanicStatus and assignments
                    mechanic.setAssignment(carId);
                    // Changes status on the mechanic
                    mechanic.setStatus(false);
                    System.out.println((i + 1) + ". " + carId + "," + mechanics.get(i).isStatus());
                    // System.out.println(mechanic.getId() + "Mechanic: " + mechanic.getUsername() + " is booked for the assignment: " + assignment);
                }
            }
            // Sync the changes back to the database
            syncMechanicStatus();
        } catch (NullPointerException e) {
            System.out.println("can't access the mechanics list");
        }
    }

    public void syncMechanicStatus() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            // Update the status for each mechanic in the database
            for (Mechanic mechanic : mechanics) {
                String sql = "UPDATE status SET status = ?, assignment = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setBoolean(1, mechanic.isStatus());
                stmt.setString(2, mechanic.getAssignment());
                stmt.setString(3, mechanic.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            // Close the connection
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }



    public static String getId() {
        return id;
    }

}
