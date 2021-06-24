package CW.Task4;

/**
 * The Person class to store person data
 *
 * @author bhavan
 * @version Task 4
 */
public class Person {
    private String firstName;
    private String surName;
    private String creditCardNumber;

    public Person(String firstName, String surName, String creditCardNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.creditCardNumber = creditCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
