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
    public ArrayList<String> getWorkingMechanics() {
        ArrayList<String> workingMechanics = new ArrayList<>();
        for (Mechanic mechanics : this.mechanics) {
            if(mechanics.getMechanicStatus() == false) {
                mechanics.getUsername();
                // call function that names all the unavailable mechanics and their assignments
                String mechanicName = mechanics.getUsername();
                String assignment = mechanics.getAssignment(); // Combine this functions with mustafas or Nicos
                String mechanicInfo = mechanicName + " - " + assignment; // Combines the name and assignment
                workingMechanics.add(mechanicInfo); // and adding it to the workingMechanics array
            } else {
                System.out.println("All mechanics are available");
            }

        }
        return workingMechanics;
    }

    /*private ArrayList<Mechanic> readMechanicsFromDatabase() {
        // reads from database here
    }*/



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
            /*
            * We need a setter for mechanicStatus and assignments*/


            // Changes status on the mechanic

            /*
            // Getting the selected mechanic
            Mechanic selectedMechanic = availableMechanics.get(mechanicNumber - 1);


            // Foreman provides information about the car and the assignment
            System.out.println("Enter the model of the car:");
            String carModel = scanner.nextLine();

            System.out.println("Enter the make of the car:");
            String carMake = scanner.nextLine();

            System.out.println("Enter the error of the car:");
            String carError = scanner.nextLine();

            // Updating the assignment and status of the selected mechanic
            String assignment = "Car Model: " + carModel + ", Make: " + carMake + ", Error: " + carError;
            selectedMechanic.setAssignment(assignment);
            selectedMechanic.setMechanicStatus(false);

            System.out.println("Mechanic " + selectedMechanic.getUsername() + " is booked for the assignment: + assignment");

            */


        }


}
