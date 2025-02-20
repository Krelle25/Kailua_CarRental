import java.time.LocalDate;

public class Customer
{
    private int customerID;
    private String name;
    private Address address;
    private String phoneNumber;
    private String email;
    private String driverLicenseNumber;
    private LocalDate driverSinceDate;

    public Customer()
    {
    }

    public Customer(int customerID, String name, Address address, String phoneNumber, String email, String driverLicenseNumber, LocalDate driverSinceDate)
    {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSinceDate = driverSinceDate;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public String getName()
    {
        return name;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getDriverLicenseNumber()
    {
        return driverLicenseNumber;
    }

    public LocalDate getDriverSinceDate()
    {
        return driverSinceDate;
    }
}
