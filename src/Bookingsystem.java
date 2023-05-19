import java.util.Scanner;

public class Bookingsystem {
    private Mechanic mechanic;

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
    Scanner scan;

    public void bookingAMechanic() {
        MechanicHandler mechanicHandler = new MechanicHandler();

        // shows all available mechanic and foreman can choose one for an assignment
        mechanicHandler.showMechanics();
        mechanicHandler.selectMechanic();

        // Foreman provides information about the car and the assignment
        System.out.println("Enter the model of the car:");
        String carModel = scan.nextLine();

        System.out.println("Enter the make of the car:");
        String carMake = scan.nextLine();

        System.out.println("Enter the error of the car:");
        String carError = scan.nextLine();

         // setter for mechanicStatus and assignments
        String assignment = "Car Model: " + carModel + ", Make: " + carMake + ", Error: " + carError;
        mechanic.setAssignment(assignment);

        // Changes status on the mechanic
        mechanic.setStatus(false);

        System.out.println(mechanic.getId() + "Mechanic: " + mechanic.getUsername() + " is booked for the assignment: " + assignment);
    }



}
