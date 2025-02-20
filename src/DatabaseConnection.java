import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Kailua_CarRental";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Krelle251102dbaxgsMySQL-KEA";

    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Fejl ved forbindelse til databasen: " + e.getMessage());
            return null;
        }
    }
}