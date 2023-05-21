import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MechanicHandler {
    static List<Mechanic> mechanics = readMechanicStatus();

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
                String username = rs.getString("username");
                //Create mechanic object and add to list
                Mechanic mechanic = new Mechanic(id,status,assignment,username);
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
            System.out.println("You can book the following mechanics:");
            for (int i = 0; i < mechanics.size(); i++) {
                Mechanic mechanic = mechanics.get(i);
                if (mechanic.isStatus() == true) {
                    System.out.println((i + 1) + ". " + mechanics.get(i).getUsername());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("can't access the mechanics list");
        }
    }

    // a function over Mechanics that aren't available and what assignment they are working on
    /*public ArrayList<String> getWorkingMechanics() {
        ArrayList<String> workingMechanics = new ArrayList<>();
        for (int i = 0; i < mechanics.size(); i++) {
            Mechanic mechanic = mechanics.get(i); // initialize mechanic
            if (mechanic.isStatus() == false) {
                //names all the unavailable mechanics with id and their assignments
                String mechanicInfo = (i + 1) + ". " + mechanics.get(i).getId() + mechanic.getUsername()+ ": " + Car.();
                 // HERE call assignment function or set assignment??
                workingMechanics.add(mechanicInfo); // and adding it to the workingMechanics array
                System.out.println(mechanicInfo);
            } else {
                System.out.println("All mechanics are available");
                textUI.backToMenu();
            }

        }
        return workingMechanics;
    }*/

    public void selectMechanic(){
        Scanner mechanicScanner = new Scanner(System.in);

        // the user to enter the number of the mechanic they want to select
        System.out.print("Please enter the number of the mechanic you'd like to select: ");
        int selection = mechanicScanner.nextInt();
        mechanicScanner.nextLine();

        // If user uses a unvalidated number
        if (selection < 1 || selection > mechanics.size()) {
            System.out.println("Invalid mechanic number.");
            return;
        }

        // gets the selected mechanic from the arrayList based on the users selection
        Mechanic selectedMechanic = mechanics.get(selection - 1);
        System.out.println("Selected Mechanic: " + selectedMechanic.getId());

    }

    public void bookMechanic() {
        try {
            scan = new Scanner(System.in);

            // creates an instance of the car class
            CarHandler carHandler = new CarHandler();
            // reads from car database
            carHandler.readCarFromDatabase();

            carHandler.showAllCarsToForeman();

            // the user to enter the number of the mechanic they want to select
            System.out.print("Please enter the number of the car you'd like to select: ");
            int selection = scan.nextInt();
            scan.nextLine();

            // If user uses a unvalidated number
            if (selection < 1 || selection > CarHandler.cars.size()) {
                System.out.println("Invalid Car number.");
                return;
            }

            // gets the selected car from cars arrayList based on the users selection
            Car selectedCar = CarHandler.cars.get(selection - 1);
            String carRegNr = selectedCar.getRegnr();

            for (int i = 0; i < mechanics.size(); i++) {
                Mechanic mechanic = mechanics.get(i);
                if (mechanic.isStatus() == true) {
                    // setter for mechanicStatus and assignments + regNr
                    mechanic.setAssignment(carRegNr);
                    // Changes status on the mechanic
                    mechanic.setStatus(false);
                    System.out.println(mechanics.get(i).getId() + ". " + "You have now assigned " + mechanics.get(i).getUsername() + " to car: " + carRegNr);
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


}
