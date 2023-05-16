public class UserMenu {
    static int rights = 0;
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
                rights = 1;
                textUI.createUserMenu();
                userHandler.saveUsers(rights);
                break;
            default:
                System.out.println("Goodbye");
        }
        //  userHandler.saveUsers();
    }
}
