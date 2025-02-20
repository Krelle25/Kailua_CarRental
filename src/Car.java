import java.time.LocalDate;

public class Car
{
    private int carID;
    private String brand;
    private String model;
    private FuelType fuelType;
    private String plate;
    private LocalDate fRegistration;
    private int odometer;
    private CarType carType;


    public Car()
    {
    }

    public Car(int carID, String brand, String model, FuelType fuelType, String plate, LocalDate fRegistration, int odometer, CarType carType)
    {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.plate = plate;
        this.fRegistration = fRegistration;
        this.odometer = odometer;
        this.carType = carType;
    }

    public int getCarID()
    {
        return carID;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public FuelType getFuelType()
    {
        return fuelType;
    }

    public String getPlate()
    {
        return plate;
    }

    public LocalDate getfRegistration()
    {
        return fRegistration;
    }

    public int getOdometer()
    {
        return odometer;
    }

    public CarType getCarType()
    {
        return carType;
    }
}
