public class UserMenu {

    Userhandler userHandler = new Userhandler();
    TextUI textUI = new TextUI(userHandler);
    public void setupUserMenu() {
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
