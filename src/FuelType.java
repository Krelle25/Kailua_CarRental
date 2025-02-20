public class FuelType
{
    private int fuelTypeID;
    private String fuelTypeName;

    public FuelType()
    {
    }

    public FuelType(int fuelTypeID, String fuelTypeName)
    {
        this.fuelTypeID = fuelTypeID;
        this.fuelTypeName = fuelTypeName;
    }

    public int getFuelTypeID()
    {
        return fuelTypeID;
    }

    public String getFuelTypeName()
    {
        return fuelTypeName;
    }
}
