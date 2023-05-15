public class Dashboard {

        //// Login Part
        static Userhandler userHandler = new Userhandler();
        static TextUI textUI = new TextUI(userHandler);
        //static MovieHandler movieHandler = new MovieHandler();


        public static void setupDashboardAdmin(){

            ////Dashboard
            String userChoice = textUI.startMenuAdmin();
            while (true) {
                switch (userChoice) {
                    case "1":
                        System.out.println("Hej");;
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
