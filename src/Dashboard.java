public class Dashboard {
    static int rights = 0;
        //// Login Part
        static Userhandler userHandler = new Userhandler();
    static User user = new User();
        static TextUI textUI = new TextUI(userHandler);
        static MechanicHandler mechanicHandler = new MechanicHandler();
        static CustomerHandler customerHandler = new CustomerHandler();
        static CarHandler carHandler = new CarHandler();


        public static void setupDashboardAdmin(){


            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":
                        customerHandler.search();
                        break;
                    case "2":
                        //tasks
                        break;

                    case "3":
                        mechanicHandler.readMechanicStatus();
                        mechanicHandler.showMechanics();
                        break;

                    case "4":
                        customerHandler.createCustomer();
                        customerHandler.saveUsers();
                        break;
                    case "5":
                        customerHandler.getCustomerCar();
                        customerHandler.showAllCustomerCar();
                    case "6":
                        customerHandler.getCustomerCar();
                        customerHandler.showAllCarCustomer();
                    case "7":
                        rights = 2;
                        textUI.createUserMenu();
                        userHandler.saveUsers(rights);
                        break;
                    case "8":
                        customerHandler.readCustomerFromDatabase();
                        customerHandler.showAllCustomers();
                        break;

                    case "9":
                        carHandler.readCarFromDatabase();
                        carHandler.showAllCars();
                        ;
                        break;
                }
                textUI.backToMenu();
            }
        }

    public static void setupDashboardMechanic() {
        ////Dashboard
        String userChoice = textUI.startMenuMechanic();
        while (true) {
            switch (userChoice) {
                case "1":
                    ;
                    break;

                case "2":
                    ;
                    break;

                case "3":
                    ;
                    break;
                case "4":
                    ;
                    break;
                case "5":
                    ;
                    break;
            }
            textUI.backToMenu();
        }
    }

    public static void setupDashboardCustomer() {
        ////Dashboard
        String userChoice = textUI.startMenuCustomer();
        while (true) {
            switch (userChoice) {
                case "1":
                    System.out.println("See the status on your car:");
                    System.out.println("The status of your car is: " );
                    break;
                case "2":
                    System.out.println("See your Car informations");
                    System.out.println("Here is your car informations");

                    break;
            }
            textUI.backToMenu();
        }
    }
}
