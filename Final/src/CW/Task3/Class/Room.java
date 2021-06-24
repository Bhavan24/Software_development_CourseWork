package CW.Task3.Class;

/**
 * The Room class to store room data
 *
 * @author bhavan
 * @version Task 3 (Class Version)
 */
public class Room {
    int guestsInRoom;
    Person payingGuest;

    // Room constructor to initialize room data
    public Room() {
        this.guestsInRoom = 0;
        this.payingGuest = new Person("empty","empty","empty");
    }

    public int getGuestsInRoom() {
        return guestsInRoom;
    }

    public void setGuestsInRoom(int guestsInRoom) {
        this.guestsInRoom = guestsInRoom;
    }

    public Person getPayingGuest() {
        return payingGuest;
    }

    public void setPayingGuest(Person payingGuest) {
        this.payingGuest = payingGuest;
    }
}
