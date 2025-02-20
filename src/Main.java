import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        while (true)
        {
            displayMenu();
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg)
            {
                case 1:
                    addCar();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    addRentalContract();
                    break;
                case 4:
                    deleteCar();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 6:
                    deleteRentalContract();
                    break;
                case 7:
                    updateCar();
                    break;
                case 8:
                    updateCustomer();
                    break;
                case 9:
                    updateRentalContract();
                    break;
                case 10:
                    searchAfter();
                    break;
                case 11:
                    System.out.println("Afslutter programmet.");
                    return;
                default:
                    System.out.println("Ugyldigt valg - prøv igen.");
            }
        }
    }

    private static void displayMenu()
    {
        System.out.println("\n============================");
        System.out.println("\uD83D\uDE97 --- KAILUA CAR RENTAL --- \uD83D\uDE97");
        System.out.println("============================");
        System.out.println("1. \uD83C\uDD95 Tilføj en bil");
        System.out.println("2. \uD83C\uDD95 Tilføj en kunde");
        System.out.println("3. \uD83C\uDD95 Tilføj en lejekontakt");
        System.out.println("4. ❌ Slet en bil");
        System.out.println("5. ❌ Slet en kunde");
        System.out.println("6. ❌ Slet en lejekontrakt");
        System.out.println("7. \uD83D\uDD04 Opdater en bil");
        System.out.println("8. \uD83D\uDD04 Opdater en kunde");
        System.out.println("9. \uD83D\uDD04 Opdater en lejekontakt");
        System.out.println("10. \uD83D\uDD0D Søg efter bil/kunde/lejekontrakt");
        System.out.println("11. \uD83D\uDC4B Afslut programmet");
        System.out.println("\uD83D\uDCCC Vælg en handling: ");
    }

    private static void addCar()
    {
        CarManager carManager = new CarManager();
        carManager.insertCar();
    }

    private static void addCustomer()
    {
        CustomerManager customerManager = new CustomerManager();
        customerManager.insertCustomer();
    }

    private static void addRentalContract()
    {
        RentalContractManager rentalContractManager = new RentalContractManager();
        rentalContractManager.insertRentalContract();
    }

    private static void deleteCar()
    {
        CarManager carManager = new CarManager();
        carManager.deleteCar();
    }

    private static void deleteCustomer()
    {
        CustomerManager customerManager = new CustomerManager();
        customerManager.deleteCustomer();
    }

    private static void deleteRentalContract()
    {
        RentalContractManager rentalContractManager = new RentalContractManager();
        rentalContractManager.deleteRentalContract();
    }

    private static void updateCar()
    {
        CarManager carManager = new CarManager();
        carManager.updateCar();
    }

    private static void updateCustomer()
    {
        CustomerManager customerManager = new CustomerManager();
        customerManager.updateCustomer();
    }

    private static void updateRentalContract()
    {
        RentalContractManager rentalContractManager = new RentalContractManager();
        rentalContractManager.updateRentalContract();
    }

    private static void searchAfter()
    {
        SearchManager searchManager = new SearchManager();
        searchManager.searchAfter();
    }
}
