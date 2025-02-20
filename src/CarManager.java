import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CarManager {
    public void insertCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast mærke: ");
        String brand = scanner.nextLine();
        System.out.println("Indtast model: ");
        String model = scanner.nextLine();
        System.out.println("Intast brændstofs-ID (1 = Diesel | 2 = Gas | 3 = Petrol | 4 = Electric | 5 = Hybrid): ");
        int fuelTypeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast nummerplade: ");
        String plate = scanner.nextLine();
        System.out.println("Indtast første registreringsdato (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate fRegistration = LocalDate.parse(dateInput);
        System.out.println("Indtast kilometertal (Odometer): ");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast biltypeID (1 = Luxury | 2 = Family | 3 = Sport): ");
        int carTypeID = scanner.nextInt();

        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        // Opretter SQL INSERT-forespørgsel
        String sql = "INSERT INTO Car (Brand, Model, FuelType_id, Plate, FRegistration, Odometer, CarType_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, fuelTypeId);
            preparedStatement.setString(4, plate);
            preparedStatement.setDate(5, Date.valueOf(fRegistration));
            preparedStatement.setInt(6, odometer);
            preparedStatement.setInt(7, carTypeID);

            // Kør SQL-forespørgsel
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bil tilføjet til databasen!");
            } else {
                System.out.println("Fejl: Bil ikke tilføjet til databasen.");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved indsættelse af bil: " + e.getMessage());
        }
    }

    public void deleteCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vil du slette bil ved hjælp af:");
        System.out.println("1. Bil-ID (Car_id)");
        System.out.println("2. Nummerplade (Plate)");
        System.out.println("Vælg en mulighed: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        String sql = "";
        String input = "";

        if (valg == 1) {
            System.out.println("Indtast bilens ID (Car_id): ");
            input = scanner.nextLine();
            sql = "DELETE FROM Car WHERE Car_id = ?";
        } else if (valg == 2) {
            System.out.println("Indtast bilens nummerplade (Plate): ");
            input = scanner.nextLine();
            sql = "DELETE FROM Car WHERE Plate = ?";
        } else {
            System.out.println("Ugyldigt valg - Annullerer sletning af bil.");
            return;
        }

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (valg == 1) {
                preparedStatement.setInt(1, Integer.parseInt(input));
            } else {
                preparedStatement.setString(1, input);
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bil slettet fra databasen.");
            } else {
                System.out.println("Fejl: Ingen bil fundet med det angivne ID eller nummerplade.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved sletning af bil: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Bil-ID skal være et tal.");
        }
    }

    public void updateCar()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vil du opdatere en bil ved hjælp af:");
        System.out.println("1. Bil-ID (Car_id)");
        System.out.println("2. Nummerplade (Plate)");
        System.out.println("Vælg en mulighed: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        String sql = "";
        String input = "";

        if (valg == 1) {
            System.out.println("Indtast bilens ID (Car_id): ");
            input = scanner.nextLine();
            sql = "UPDATE Car SET Brand = ?, Model = ?, FuelType_id = ?, Plate = ?, FRegistration = ?, Odometer = ?, CarType_id = ?, WHERE Car_id = ?";
        } else if (valg == 2) {
            System.out.println("Indtast bilens nummerplade (Plate): ");
            input = scanner.nextLine();
            sql = "UPDATE Car SET Brand = ?, Model = ?, FuelType_id = ?, Plate = ?, FRegistration = ?, Odometer = ?, CarType_id = ?, WHERE Plate = ?";
        } else {
            System.out.println("Ugyldigt valg - Annullerer opdatering af bil.");
            return;
        }

        System.out.println("Indtast nyt mærke: ");
        String brand = scanner.nextLine();
        System.out.println("Indtast ny model: ");
        String model = scanner.nextLine();
        System.out.println("Intast ny brændstofs-ID (1 = Diesel | 2 = Gas | 3 = Petrol | 4 = Electric | 5 = Hybrid): ");
        int fuelTypeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast ny nummerplade: ");
        String plate = scanner.nextLine();
        System.out.println("Indtast ny registreringsdato (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate fRegistration = LocalDate.parse(dateInput);
        System.out.println("Indtast nyt kilometertal (Odometer): ");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indtast ny biltypeID (1 = Luxury | 2 = Family | 3 = Sport): ");
        int carTypeID = scanner.nextInt();

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, fuelTypeId);
            preparedStatement.setString(4, plate);
            preparedStatement.setDate(5, java.sql.Date.valueOf(fRegistration));
            preparedStatement.setInt(6, odometer);
            preparedStatement.setInt(7, carTypeID);

            if (valg == 1) {
                preparedStatement.setInt(8, Integer.parseInt(input));
            } else {
                preparedStatement.setString(8, input);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bilen blev opdateret!");
            } else {
                System.out.println("Fejl: Ingen bil fundet med det angivne ID eller nummerplade.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved opdatering af bil: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Bil-ID skal være et tal.");
        }
    }
}