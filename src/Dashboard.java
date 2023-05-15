public class Dashboard {

        //// Login Part
        static Userhandler userHandler = new Userhandler();
        static TextUI textUI = new TextUI(userHandler);
        //static MovieHandler movieHandler = new MovieHandler();
        static CustomerHandler customerHandler = new CustomerHandler();


        public static void setupDashboard(){

            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":

                        break;

                    case "2":
                        ;
                        break;

                    case "3":
                        customerHandler.createCustomer();
                        customerHandler.saveUsers();
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
