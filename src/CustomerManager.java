import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomerManager {
    public void insertCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast kundesn navn: ");
        String name = scanner.nextLine();
        System.out.println("Indtast telefonnummer på kunde: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Indtast E-Mail på kunde: ");
        String email = scanner.nextLine();
        System.out.println("Indtast kørekortnummer på kunde: ");
        String driverLicenseNumber = scanner.nextLine();
        System.out.println("Indtast udstedelsesdato på kørekort (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate driverSinceDate = LocalDate.parse(dateInput);
        System.out.println("Indtast vejnavn og nummer: ");
        String street = scanner.nextLine();
        System.out.println("Indtast postnummer: ");
        String zip = scanner.next();
        System.out.println("Indtast by: ");
        String city = scanner.next();

        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            int addressId = 0;

            String checkAddressSQL = "SELECT Address_id FROM Address WHERE Street = ? AND Zip = ? AND City = ?";
            PreparedStatement checkAddressStmt = connection.prepareStatement(checkAddressSQL);
            checkAddressStmt.setString(1, street);
            checkAddressStmt.setString(2, zip);
            checkAddressStmt.setString(3, city);
            ResultSet resultSet = checkAddressStmt.executeQuery();

            if (resultSet.next()) {
                addressId = resultSet.getInt("Address_id");
                System.out.println("Bruger eksisterende adresse med ID: " + addressId);
            } else {
                String insertAddressSQL = "INSERT INTO Address (Street, Zip, City) VALUES (?, ?, ?)";
                PreparedStatement insertAddressStmt = connection.prepareStatement(insertAddressSQL, Statement.RETURN_GENERATED_KEYS);
                insertAddressStmt.setString(1, street);
                insertAddressStmt.setString(2, zip);
                insertAddressStmt.setString(3, city);
                insertAddressStmt.executeUpdate();

                ResultSet generatedKeys = insertAddressStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    addressId = generatedKeys.getInt(1);
                    System.out.println("Oprettet ny addresse med ID: " + addressId);
                }
            }

            String insertCustomerSQL = "INSERT INTO Customer (Name, Address_id, PhoneNumber, Email, DriverLicenseNumber, DriverSinceDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?";
            PreparedStatement insertCustomerStmt = connection.prepareStatement(insertCustomerSQL);
            insertCustomerStmt.setString(1, name);
            insertCustomerStmt.setInt(2, addressId);
            insertCustomerStmt.setString(3, phoneNumber);
            insertCustomerStmt.setString(4, email);
            insertCustomerStmt.setString(5, driverLicenseNumber);
            insertCustomerStmt.setDate(6, Date.valueOf(driverSinceDate));

            int rowsAffected = insertCustomerStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kunde blev tilføjet til databasen!");
            } else {
                System.out.println("Fejl: Kunde ikke tilføjet til databasen.");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved indsættelse af kunde: " + e.getMessage());
        }
    }

    public void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vil du slette kunde ved hjælp af:");
        System.out.println("1. Customer-ID (Customer_id)");
        System.out.println("2. E-mail");
        System.out.println("Vælg en mulighed: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        String sqlDeleteContracts = "";
        String sqlDeleteCustomer = "";
        String input = "";

        if (valg == 1) {
            System.out.println("Indtast kundens ID (Customer_id): ");
            input = scanner.nextLine();
            sqlDeleteContracts = "DELETE FROM RentalContract WHERE Customer_id = ?";
            sqlDeleteCustomer = "DELETE FROM Customer WHERE Customer_id = ?";
        } else if (valg == 2) {
            System.out.println("Indtast kundens E-mail: ");
            input = scanner.nextLine();
            sqlDeleteContracts = "DELETE FROM RentalContract WHERE Customer_id = (SELECT Customer_id FROM Customer WHERE Email = ?)";
            sqlDeleteCustomer = "DELETE FROM Customer WHERE Email = ?";
        } else {
            System.out.println("Ugydligt valg - Annullerer sletning.");
            return;
        }

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            PreparedStatement deleteContractsStmt = connection.prepareStatement(sqlDeleteContracts);
            if (valg == 1) {
                deleteContractsStmt.setInt(1, Integer.parseInt(input));
            } else {
                deleteContractsStmt.setString(1, input);
            }
            deleteContractsStmt.executeUpdate(); // sletter lejekontrakten.

            PreparedStatement deleteCustomerStmt = connection.prepareStatement(sqlDeleteCustomer);
            if (valg == 1) {
                deleteCustomerStmt.setInt(1, Integer.parseInt(input));
            } else {
                deleteCustomerStmt.setString(1, input);
            }

            int rowsAffected = deleteCustomerStmt.executeUpdate(); // sletter kunden.

            if (rowsAffected > 0) {
                System.out.println("Kunden blev slettet fra databasen.");
            } else {
                System.out.println("Fejl: Ingen kunde fundet med det angive ID eller E-mail.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved sletning af kunde: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Kunde-ID skal være et tal.");
        }
    }

    public void updateCustomer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vil du opdatere en kunde ved hjælp af:");
        System.out.println("1. Kunde-ID (Customer_id)");
        System.out.println("2. E-mail");
        System.out.println("Vælg en mulighed: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        String sql = "";
        String input = "";

        if (valg == 1) {
            System.out.println("Indtast kunden ID (Customer_id): ");
            input = scanner.nextLine();
            sql = "UPDATE Customer SET Name = ?, PhoneNumber = ?, Email = ?, DriverLicenseNumber = ?, DriverSinceDate = ? WHERE Customer_id = ?";

        } else if (valg == 2) {
            System.out.println("Indtast kunden Email: ");
            input = scanner.nextLine();
            sql = "UPDATE Customer SET Name = ?, PhoneNumber = ?, Email = ?, DriverLicenseNumber = ?, DriverSinceDate = ? WHERE Email = ?";
        } else {
            System.out.println("Ugyldigt valg - Annullerer opdatering.");
            return;
        }

        System.out.println("Indtast nyt navn: ");
        String name = scanner.nextLine();
        System.out.println("Indtast nyt telefonnummer: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Indtast ny E-mail: ");
        String email = scanner.nextLine();
        System.out.println("Indtast nyt kørekortnummer: ");
        String driverLicenseNumber = scanner.nextLine();
        System.out.println("Indtast ny udstedelsesdato på kørekort (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate driverSinceDate = LocalDate.parse(dateInput);

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Kunne ikke oprette forbindelse til databasen.");
            return;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, driverLicenseNumber);
            preparedStatement.setDate(5, java.sql.Date.valueOf(driverSinceDate));

            if (valg == 1) {
                preparedStatement.setInt(6, Integer.parseInt(input));
            } else {
                preparedStatement.setString(6, input);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kunde blev opdateret!");
            } else {
                System.out.println("Fejl: Ingen kunde fundet med det angivne ID eller E-mail.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Fejl ved opdatering af kunde: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Kunde-ID skal være et tal.");
        }
    }
}

