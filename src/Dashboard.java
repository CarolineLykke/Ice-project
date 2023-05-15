public class Dashboard {
    public boolean login(String username, String password, String id) {
        if (username == null || password == null) {
            return false;
        }
        for (User user : users) {
            if (user.getName() != null && user.getPassword() != null
                    && user.getName().equals(username) && user.getPassword().equals(password)) {
                currentUser = user.getName();
                currentId = user.getId();
                return true;
            }
        }
        return false;
    }

}
