public class Main {
    public static void main(String[] args) {
        Userhandler userHandler = new Userhandler();
        TextUI textUI = new TextUI(userHandler);

            userHandler.loadUsers();

            String userInput = textUI.getUserInput();
            switch (userInput) {
                case "1":
                    textUI.loginMenu();
                    break;
                case "2":
                    textUI.createUserMenu();

                    userHandler.saveUsers();
                    break;
                default:
                    System.out.println("Goodbye");
            }
            //  userHandler.saveUsers();
        }
    }
