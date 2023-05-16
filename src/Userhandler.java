import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class Userhandler {
    static ArrayList<User> users = new ArrayList<>();
    File file;

    private static String currentUser;
    private static String currentId;
    private static int currentRights;

    public boolean login(String username, String password, String id, int rights) {
        if (username == null || password == null) {
            return false;
        }
        for (User user : users) {
            if (user.getUsername() != null && user.getPassword() != null
                    && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user.getUsername();
                currentId = user.getId();
                currentRights = user.getRights();
                return true;
            }
        }
        return false;
    }

    public static boolean createUser(String username, String password, String Id, int rights) {
        if(!isValid(password)){
            return false;
        } if(!isUserNameValid(username)){
            return false;
        }
        for(User user: users){
            if(user.getUsername().equals(username)) {
                return false;

            }
        }

        users.add(new User(username, password, rights));
        return true;
    }


    public void loadUsers(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database loading users");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM users";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String username = rs.getString("username");
                String password = rs.getString("password");
                String id = rs.getString("id");
                int rights = rs.getInt("rights");
                users.add(new User(username,password,rights));
            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void saveUsers(int rights) {
        //UserHandler userHandler = new UserHandler();

        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading saveusers");
            conn = DriverManager.getConnection(dbconection.DB_URL, dbconection.USER, dbconection.PASS);

            // the mysql insert statement
            String sql = "INSERT INTO users (UserName,password) VALUES (?, ?,?)";

            //INSERT INTO streaming.users (UserName,password) VALUES (?, ?)

            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
            for(User user:users){
                stmt.setString ( 1,user.getUsername());
                stmt.setString ( 2,user.getPassword());
                stmt.setInt ( 3,rights);
            }


            // execute the preparedstatement
            stmt.executeUpdate();


            conn.close();
            //DashBoard.setupDashboard();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }


    public static boolean isUserNameValid(String username) {
        if(username == null || username.length() > 20 || username.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isValid(String password){
        if(password == null || password.length() < 8 || password.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }
    public static String getName() {
        return currentUser;
    }
    public static String getId() {
        return currentId;
    }

    public static int getRights(){
        return currentRights;
    }



}
