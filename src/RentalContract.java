import java.time.LocalDateTime;

public class RentalContract
{
    private int rentalID;
    private Customer customer;
    private Car car;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxKM;
    private int kmStart;
    private Status status;

    public RentalContract()
    {
    }

    public RentalContract(int rentalID, Customer customer, Car car, LocalDateTime startDate, LocalDateTime endDate, int maxKM, int kmStart, Status status)
    {
        this.rentalID = rentalID;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxKM = maxKM;
        this.kmStart = kmStart;
        this.status = status;
    }

    public int getRentalID()
    {
        return rentalID;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Car getCar()
    {
        return car;
    }

    public LocalDateTime getStartDate()
    {
        return startDate;
    }

    public LocalDateTime getEndDate()
    {
        return endDate;
    }

    public int getMaxKM()
    {
        return maxKM;
    }

    public int getKmStart()
    {
        return kmStart;
    }

    public Status getStatus()
    {
        return status;
    }
}
