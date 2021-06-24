package CW.Task2;

/**
 * The Room class to store room data
 *
 * @author bhavan
 * @version Task 2 (Class Version)
 */
public class Room {
    private String customerName;

    public Room(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
