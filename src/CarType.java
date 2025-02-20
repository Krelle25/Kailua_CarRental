public class CarType
{
    private int carTypeID;
    private String name;
    private String description;

    public CarType()
    {
    }

    public CarType(int carTypeID, String name, String description)
    {
        this.carTypeID = carTypeID;
        this.name = name;
        this.description = description;
    }

    public int getCarTypeID()
    {
        return carTypeID;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}