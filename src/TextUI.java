import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class TextUI {
    Scanner scanner;
    Userhandler userhandler;

    public TextUI(Userhandler userHandler) {
        this.userhandler = userHandler;
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        System.out.println("Hello. Would you like to: " + "\n" + "1) Log in or" + "\n" + "2) Create user?" + "\n" + "Please write 1 or 2 and press Enter:");
        return scanner.nextLine();
    }
    //DashBoard dashBoard = new DashBoard();
    public void loginMenu() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        String id = Userhandler.getId();
        int rights = Userhandler.getRights();
        if (userhandler.login(username, password, id, rights)) {
            System.out.println("Welcome " + username);
            if (Userhandler.getRights()==1){
                System.out.println("Du er admin");
                Dashboard.setupDashboard();
            } else if (Userhandler.getRights() == 2) {
                System.out.println("Du er mekaniker");
            } else if (Userhandler.getRights() == 3) {
                System.out.println("Du er kunde");
            }

        } else {
            System.out.println("Sorry, the username or password is incorrect");
            loginMenu();
        }

    }

    public void createUserMenu() {
        System.out.println("Please enter a username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter a password: ");
        String password = scanner.nextLine();
        String id = Userhandler.getId();
        int rights = Userhandler.getRights();
        if (userhandler.login(username, password, id, rights)) {
            System.out.println("Welcome " + username);
            if (Userhandler.getRights()==1){
                System.out.println("Du har oprettet en admin");
            } else if (Userhandler.getRights() == 2) {
                System.out.println("Du har oprettet en mekaniker");
            } else if (Userhandler.getRights() == 3) {
                System.out.println("Du har oprettet en kunde");
            }
        } else {
            System.out.println("Sorry, the username or password can not be used try agin:");
            createUserMenu();
        }
    }

    public String startMenuAdmin() {
        System.out.println("Welcome to Mustafa's garage, you can now choose one of the options" + "\n" + "1: See tasks" + "\n" + "2: Mechanic status" + "\n" + "3: Add Customer" + "\n" + "4: Add Mechanic" + "\n" + "5: Show all Customers");
        return scanner.nextLine();
    }

    public void backToMenu() {
        System.out.println("Press 1 to go back to the start menu, or 2 to close the application.");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                Dashboard.setupDashboard();
                break;
            case "2":
                System.out.println("Closing the application...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                backToMenu();
                break;
        }
    }

}
