public enum Status
{
    ACTIVE("Active"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String theStatus;

    Status(String theStatus)
    {
        this.theStatus = theStatus;
    }
}
