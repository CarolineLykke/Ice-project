import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Bookingsystem {

    /* Different functions for different rights
     Mechanic rights/functions:
     - be booked by the foreman for different assignment for a car
     - A function where the mechanic can make a booking complete
     - Showing the mechanics assignments

     Foreman rights/functions:
      - A list over Mechanics that are available
      - A list over Mechanics that aren't available and what assignment they are working on with
        an approx. time when the mechanic is finished
      - To book a mechanic for a specific assignment on a car

    */

    Scanner scanner;

    Foreman foreman;


    private ArrayList<Mechanic> mechanics = readMechanicsFromDatabase();


    // A function that shows all the available mechanics
    public ArrayList<Mechanic> getAvailableMechanics(String mechanicName) {
        ArrayList<Mechanic> availableMechanics = new ArrayList<>();
        for (Mechanic mechanics : this.mechanics) {
            if (mechanics.getMechanicStatus() == true) {
                String name = mechanics.getUsername();
                if (name.toLowerCase().contains(mechanicName.toLowerCase())) {
                    availableMechanics.add(mechanics);
                }
            } else if(!mechanics.getMechanicStatus()) {
                System.out.println("No available mechanics");
            }
        }
            return availableMechanics;
        }

        // a function over Mechanics that aren't available and what assignment they are working on
    public ArrayList<Mechanic> getWorkingMechanics() {
        ArrayList<Mechanic> workingMechanics = new ArrayList<>();
        for (Mechanic mechanics : this.mechanics) {
            if(mechanics.getMechanicStatus() == false) {
                mechanics.getUsername();
                // call assignmentInfoMsg
                foreman.assignmentInfoMsg();
            } else {
                System.out.println("All mechanics are available");
            }

        }
        return workingMechanics;
    }

    private ArrayList<Mechanic> readMechanicsFromDatabase() {
        // reads from database here
    }



        public void bookingAMechanic() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("The following mechanics are available:");
            String mechanicName = scanner.nextLine();

            // Creating a new arrayList
            ArrayList<Mechanic> availableMechanics = getAvailableMechanics(mechanicName);

            // show a list of the available mechanics here
            getAvailableMechanics(mechanicName);

            // Foreman chooses one of the available mechanics
            System.out.println("Enter the number of the mechanic you want to book:");
            int mechanicNumber = scanner.nextInt();

            // If foreman chooses a number that isn't displayed
            if(mechanicNumber < 1 || mechanicNumber > availableMechanics.size()) {
                System.out.println("Invalid number");
            }
            scanner.nextLine();

            // Foreman gives information: model, make, the error of the car etc.
                foreman.assignmentInfoMsg();

            // Changes status on the mechanic

        }


}
