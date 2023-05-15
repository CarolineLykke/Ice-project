public class Mechanic{

    private String Id;
    private String username;
    private String password;
    private int rights;
    User user;
    private boolean status = true;
    public Mechanic(String id, String username, String password, int rights) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.rights = rights;
    }

    // status of mechanic
   public boolean getMechanicStatus() {
        if(status) {
           System.out.println(user.getUsername() + " er ledig");
       } else if (!status) {
            System.out.println(user.getUsername() + " er ikke ledig");
        }
        return status;
   }

   public String getUsername() {
        return username;
   }
}
