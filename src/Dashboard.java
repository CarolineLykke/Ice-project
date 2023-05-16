public class Dashboard {

        //// Login Part
        static Userhandler userHandler = new Userhandler();
        static TextUI textUI = new TextUI(userHandler);
        //static MovieHandler movieHandler = new MovieHandler();


   // public static void setupDashboard(){
        public static void setupDashboardAdmin(){


            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":
                        carHandler.createCar();
                        carHandler.savecars();
                        break;

                    case "3":
                        ;
                        break;
                    case "4":
                        rights = 2;
                        textUI.createUserMenu();
                        userHandler.saveUsers(rights);



                        break;
                    case "5":
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
                    ;
                    break;

                    case "2":
                        carHandler.readCarFromDatabase();
                        carHandler.showAllCars();
                        break;

                    case "3":
                        customerHandler.createCustomer();
                        customerHandler.saveUsers();
                        break;
                    case "4":
                        ;
                        break;
                    case "5":
                        customerHandler.readCustomerFromDatabase();
                        customerHandler.showAllCustomers();
                        break;
                }
                textUI.backToMenu();
            }
        }

    }
