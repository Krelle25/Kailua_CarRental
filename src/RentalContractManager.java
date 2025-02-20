import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class RentalContractManager
{
    public void insertRentalContract() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast Kunde-ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast Bil-ID: ");
        int carID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast startdato (YYYY-MM-DD HH:MM:SS): ");
        String startDate = scanner.nextLine();
        System.out.println("Indtast slutdato (YYYY-MM-DD HH:MM:SS): ");
        String endDate = scanner.nextLine();
        System.out.println("Indtast maks. KM: ");
        int maxKm = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast start KM: ");
        int kmStart = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast status (Active, Completed, Cancelled): ");
        String status = scanner.nextLine();

        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        String sql = "INSERT INTO RentalContract (Customer_id, Car_id, StartDate, EndDate, MaxKM, StartKM, Status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, carID);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setInt(5, maxKm);
            preparedStatement.setInt(6, kmStart);
            preparedStatement.setString(7, status);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Lejekontrakt tilføjet til databasen!");
            } else {
                System.out.println("Fejl: Lejekontrakt ikke tilføjet til databasen.");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved indsættelse af lejekontrakt: " + e.getMessage());
        }
    }

    public void deleteRentalContract() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Indtast RentalContract-ID: ");
            int rentalID = scanner.nextInt();

            Connection connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("Kunne ikke oprette forbindelse til databasen.");
                return;
            }
            try {
                String sql = "DELETE FROM RentalContract WHERE Rental_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, rentalID);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Lejekontrakt slettet fra databasen.");
                } else {
                    System.out.println("Fejl: Ingen lejekontrakt fundet med det angivne ID.");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Fejl ved sletning af lejekontrakt: " + e.getMessage());
            }
    }

    public void updateRentalContract() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast lejekontakt-ID (RentalContract-ID: ");
        int rentalID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast ny startdato (YYYY-MM-DD HH:MM:SS): ");
        String startDate = scanner.nextLine();
        System.out.println("Indtast ny slutdato (YYYY-MM-DD HH:MM:SS): ");
        String endDate = scanner.nextLine();
        System.out.println("Indtast nyt maks. KM: ");
        int maxKM = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast ny start KM: ");
        int startKM = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast ny status: ");
        String status = scanner.nextLine();

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            String sql = "UPDATE RentalContract SET StartDate = ?, EndDate = ?, MaxKM = ?, KMStart = ?, Status = ? WHERE Rental_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, rentalID);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setInt(4, maxKM);
            preparedStatement.setInt(5, startKM);
            preparedStatement.setString(6, status);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Lejekontrakt opdateret!");
            } else {
                System.out.println("Fejl: Ingen lejekontrakt fundet med det angivne ID.");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved opdatering af lejekontakt." + e.getMessage());
        }
    }
}
