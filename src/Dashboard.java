public class Dashboard {

        //// Login Part
        static Userhandler userHandler = new Userhandler();
        static TextUI textUI = new TextUI(userHandler);
        //static MovieHandler movieHandler = new MovieHandler();
        static CustomerHandler customerHandler = new CustomerHandler();
        static CarHandler carHandler = new CarHandler();



    public static void setupDashboard(){

            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":
                        carHandler.createCar();
                        carHandler.savecars();
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
