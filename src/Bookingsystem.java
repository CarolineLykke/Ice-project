import java.util.Scanner;

public class Bookingsystem {
    public void bookingAMechanic() {
        MechanicHandler mechanicHandler = new MechanicHandler();

        mechanicHandler.showMechanics();
        mechanicHandler.selectMechanic();
        mechanicHandler.bookMechanic();
    }
}
