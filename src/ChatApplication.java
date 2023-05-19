import java.sql.*;
import java.util.Scanner;

public class ChatApplication {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://e30.dk:3306/edk_ice", "edk_mustafa", "xLsMc1P}$l[y");

            // Create scanner object for user input
            Scanner scanner = new Scanner(System.in);

            // User login
            System.out.print("Enter user ID (1 or 2): ");
            int userId = scanner.nextInt();

            // Item ID
            int itemId = 1; // Assuming the item ID is 1

            // Prepare and execute the SQL query to retrieve chat messages
            String sql = "SELECT * FROM chat_messages WHERE conversation_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int messageId = resultSet.getInt("id");
                int conversationId = resultSet.getInt("conversation_id");
                int senderId = resultSet.getInt("user_id");
                String message = resultSet.getString("message");
                Timestamp sentAt = resultSet.getTimestamp("sent_at");

                // Print the retrieved data
                System.out.println("Message ID: " + messageId);
                System.out.println("Conversation ID: " + conversationId);
                System.out.println("Sender ID: " + senderId);
                System.out.println("Message: " + message);
                System.out.println("Sent At: " + sentAt);
                System.out.println("-----------------------");
            }

            // User 1 or User 2 can send a message about Item 1
            if (userId == 1 || userId == 2) {
                System.out.print("Enter message: ");
                scanner.nextLine(); // Consume newline character
                String message = scanner.nextLine();

                // Prepare and execute the SQL query to insert the new message
                sql = "INSERT INTO chat_messages (conversation_id, user_id, message, sent_at) VALUES (?, ?, ?, NOW())";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, itemId);
                statement.setInt(2, userId);
                statement.setString(3, message);
                statement.executeUpdate();

                System.out.println("Message sent successfully!");
            } else {
                System.out.println("Invalid user ID!");
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
