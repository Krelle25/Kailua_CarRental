import java.sql.*;

public class AccessDB
{
    public static void main(String[] args)
    {
            try {
                Connection connection = DatabaseConnection.getConnection();

                if (connection != null) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Car");

                    while (resultSet.next()) {
                        String model = resultSet.getString("Model");
                        System.out.println("Model:" + model);
                    }
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
