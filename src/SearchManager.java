import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchManager
{
    public void searchAfter()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Søg efter: ");
        System.out.println("1. Kunde");
        System.out.println("2. Bil");
        System.out.println("3. Lejekontrakt");
        System.out.println("Vælg en mulighed: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        String sql = "";
        String input = "";

        if (valg == 1) {
            System.out.println("Søg efter kunde ved hjælp af: ");
            System.out.println("1. Kunde-ID (Customer_id)");
            System.out.println("2. E-mail");
            System.out.println("Vælg en mulighed: ");
            int kundeValg = scanner.nextInt();
            scanner.nextLine();

            if (kundeValg == 1) {
                System.out.println("Indtast Kunde-ID (Customer_id): ");
                input = scanner.nextLine();
                sql = "SELECT * FROM Customer WHERE Customer_id = ?";
            } else if (kundeValg == 2) {
                System.out.println("Indtast E-mail: ");
                input = scanner.nextLine();
                sql = "SELECT * FROM Customer WHERE Email = ?";
            } else {
                System.out.println("Ugyldigt valg.");
                return;
            }

        } else if (valg == 2) {
            System.out.println("Søg efter bil ved hjælp af: ");
            System.out.println("1. Bil-ID (Car_id)");
            System.out.println("2. Mærke");
            System.out.println("Vælg en mulighed: ");
            int bilValg = scanner.nextInt();
            scanner.nextLine();

            if (bilValg == 1) {
                System.out.println("Indtast Bil-ID (Car_id): ");
                input = scanner.nextLine();
                sql = "SELECT * FROM Car WHERE Car_id = ?";
            } else if (bilValg == 2) {
                System.out.println("Indtast mærke: ");
                input = scanner.nextLine();
                sql = "SELECT * FROM Car WHERE Brand = ?";
            } else {
                System.out.println("Ugyldigt valg.");
                return;
            }

        } else if (valg == 3) {
            System.out.println("Søg efter lejekontrakt ved hjælp af: ");
            System.out.println("1. Lejekontakt-ID (Rental_id)");
            System.out.println("2. Kunde-ID (Customer_id)");
            System.out.println("Vælg en mulighed: ");
            int lejekontraktValg = scanner.nextInt();
            scanner.nextLine();

            if (lejekontraktValg == 1) {
                System.out.println("Indtast lejekontrak-ID (Rental_id): ");
                input = scanner.nextLine();
                sql = "SELECT * FROM RentalContract WHERE Rental_id = ?";
            } else if (lejekontraktValg == 2) {
                System.out.println("Indtast Kunde-ID (Customer_id): ");
                input = scanner.nextLine();
                sql = "SELECT * FROM RentalContract WHERE Customer_id = ?";
            } else {
                System.out.println("Ugyldigt valg.");
                return;
            }
        } else {
            System.out.println("Ugyldigt valg - Annullerer søgning.");
            return;
        }

            Connection connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("Kunne ikke oprette forbindelse til databasen.");
                return;
            }

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                try {
                    int id = Integer.parseInt(input);
                    preparedStatement.setInt(1, id);
                } catch (NumberFormatException e) {
                    preparedStatement.setString(1, input);
                }

                ResultSet resultSet = preparedStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Ingen resultater fundet.");
                    return;
                }

                System.out.println("\n🔍 SØGERESULTATER:");
                while (resultSet.next()) {
                    if (valg == 1) {
                        System.out.println("ID: " + resultSet.getInt("Customer_id") +
                                           " | Navn: " + resultSet.getString("Name") +
                                           " | E-mail: " + resultSet.getString("Email"));
                    } else if (valg == 2) {
                        System.out.println("ID: " + resultSet.getInt("Car_id") +
                                           " | Mærke: " + resultSet.getString("Brand") +
                                           " | Model: " + resultSet.getString("Model"));
                    } else if (valg == 3) {
                        System.out.println("ID: " + resultSet.getInt("Rental_id") +
                                           " | Kunde-ID: " + resultSet.getInt("Customer_id") +
                                           " | Bil-ID: " + resultSet.getInt("Car_id") +
                                           " | Status: " + resultSet.getString("Status"));
                    }
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Fejl ved søgning: " + e.getMessage());
            }
        }
    }