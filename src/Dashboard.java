public class Dashboard {
    static int rights = 0;
        //// Login Part
        static Userhandler userHandler = new Userhandler();
    static User user = new User();
        static TextUI textUI = new TextUI(userHandler);
        static MechanicHandler mechanicHandler = new MechanicHandler();
        static CustomerHandler customerHandler = new CustomerHandler();
        static CarHandler carHandler = new CarHandler();
        static Bookingsystem bookingSystem = new Bookingsystem();


        public static void setupDashboardAdmin(){


            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":
                        customerHandler.search();

                        //tasks
                        break;

                    case "2":
                        mechanicHandler.readMechanicStatus();
                        mechanicHandler.showMechanics();
                        break;

                    case "3":
                        customerHandler.createCustomer();
                        customerHandler.saveUsers();
                        break;
                    case "4":
                        customerHandler.getCustomerCar();
                        customerHandler.showAllCustomerCar();
                    case "5":
                        customerHandler.getCustomerCar();
                        customerHandler.showAllCarCustomer();
                    case "6":
                        rights = 2;
                        textUI.createUserMenu();
                        userHandler.saveUsers(rights);
                        break;
                    case "7":
                        customerHandler.readCustomerFromDatabase();
                        customerHandler.showAllCustomers();
                        break;

                    case "8":
                        carHandler.readCarFromDatabase();
                        carHandler.showAllCars();
                        ;
                        break;
                    case "9":
                        bookingSystem.bookingAMechanic();
                        break;
                    case "10":
                        mechanicHandler.getWorkingMechanics();
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
}
