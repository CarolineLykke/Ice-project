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

        System.out.println("You can book the following mechanics:");

        // shows all available mechanic and foreman can choose one for an assignment

        mechanicHandler.showMechanics();
        mechanicHandler.selectMechanic();
        mechanicHandler.bookMechanic();

    }

}
