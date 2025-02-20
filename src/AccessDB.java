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
                        int car_id = resultSet.getInt("Car_id");
                        System.out.println("Car ID:" + car_id);
                    }
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}