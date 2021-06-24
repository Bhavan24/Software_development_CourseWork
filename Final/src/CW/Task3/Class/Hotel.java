package CW.Task3.Class;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * The Hotel program implements an application that
 * does the following operations (with Room & Person)
 * add/view/delete/find/store/load/viewByOrder
 *
 * @author bhavan
 * @version Task 3 (Class Version)
 */
public class Hotel {
    // static Scanner object to get user input
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Room Array hotel to store customer names for 8 rooms
        Room[] rooms = new Room[8];

        // initializing each room to it's default value "empty"
        initialise(rooms);

        // Program Menu
        String menu = (
                "+ - - - - - - - - - - - - - - - - - - - - - - +\n" +
                "|               HOTEL  PROGRAM                |\n" +
                "+ - - - - - - - - - - - - - - - - - - - - - - +\n" +
                "|   A.ADD CUSTOMER TO ROOM                    |\n" +
                "|   V.VIEW ALL ROOMS                          |\n" +
                "|   E.DISPLAY EMPTY ROOMS                     |\n" +
                "|   D.DELETE CUSTOMER FROM ROOM               |\n" +
                "|   F.FIND ROOM FROM CUSTOMER NAME            |\n" +
                "|   S.STORE PROGRAM DATA INTO FILE            |\n" +
                "|   L.LOAD PROGRAM DATA FROM FILE             |\n" +
                "|   O.VIEW GUESTS IN ALPHABETICAL ORDER       |\n" +
                "|   X.EXIT PROGRAM                            |\n" +
                "+ - - - - - - - - - - - - - - - - - - - - - - +"
        );
        System.out.println(menu);

        /* Using do-while loop because the user needs to input at least once.
         * using switch case to decide which method to call.
         * loop runs until a user inputs "X".
         * storing the user preference in choice variable. */
        String choice;
        do {
            System.out.print("\n*** Enter Your Preference -> ");
            // getting the user input & making it uppercase
            choice = input.next().toUpperCase();
            switch (choice) {
                case "A" -> add(rooms);
                case "V" -> view(rooms);
                case "E" -> viewEmpty(rooms);
                case "D" -> delete(rooms);
                case "F" -> find(rooms);
                case "S" -> store(rooms);
                case "L" -> load(rooms);
                case "O" -> viewByOrder(rooms);
                case "X" -> System.out.println("Task Ended!\n");
                default -> System.out.println("Invalid Input! Try Again!");
            }
        } while(!choice.equals("X"));
    }

    /**
     * initialise method to initialise all rooms
     * @param rooms - array which holds the room data
     */
    private static void initialise(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room();
        }
        System.out.println("All Rooms are initialized!\n");
    }

    /**
     * add method which adds a customer to the room
     * @param rooms - array which holds the room data
     */
    public static void add(Room[] rooms) {
        try{
            // getting room number from user and storing it to the variable
            System.out.print("Enter Room number(1 - 8) -> ");
            int roomNum = input.nextInt();

            /* Array indexes start from 0 but room numbers start from 1,
             * so we are subtracting 1 from the given room number,
             * this concept is used throughout the program.*/
            int n = roomNum - 1;

            /* if the room is empty we are adding the customer
             * else we are displaying "Room is already occupied!" */
            if(rooms[n].getGuestsInRoom() == 0){
                addCustomer(rooms[n]);
            } else {
                System.out.println("Room is already occupied! Try Again!!!");
            }
        } catch (Exception exception){
            /* If the user enters a invalid value for roomNum
             * we are displaying a error message */
            System.out.println("Room number / Guests in Room is Invalid!!!");
        }
    }

    /**
     * view method which is to view all room details
     * @param rooms - array which holds the room data
     */
    public static void view(Room[] rooms) {
        /* looping through the array to get every room
         * then displaying the details like a table */
        System.out.println("Firstname\tSurname\t\tCreditCard\tGuests");
        for (Room room : rooms) {
            System.out.println(
                room.getPayingGuest().getFirstName() + "\t\t" +
                room.getPayingGuest().getSurName() + "\t\t" +
                room.getPayingGuest().getCreditCardNumber()+ "\t\t" +
                room.getGuestsInRoom()
            );
        }
    }

    /**
     * viewEmpty method which view only the empty rooms
     * @param rooms - array which holds the room data
     */
    public static void viewEmpty(Room[] rooms) {
        // looping through the array to find the room number
        int emptyRooms = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getGuestsInRoom() == 0)  {
                System.out.println("Room number " + (i + 1) + " is empty");
                emptyRooms++;
            }
        }
        // if we could not find any empty room we are displaying a message
        if(emptyRooms == 0){
            System.out.println("No empty rooms, All Rooms are occupied!!!");
        }
    }

    /**
     * delete method which deletes a customer in the room
     * @param rooms - array which holds the room data
     */
    public static void delete(Room[] rooms) {
        try{
            System.out.print("Enter Room number to Remove the Customer -> ");
            int roomNum = input.nextInt();
            int n = roomNum - 1;

            /* checking if the room is already empty
             * else we are making the room "empty" */
            if (rooms[n].getGuestsInRoom() == 0) {
                System.out.println("Room is already empty");
            } else {
                setRoomData(rooms[n],0,"empty","empty","empty");
                System.out.println("Customer Removed!");
            }
        } catch (Exception exception){
            /* If the user enters a invalid value for roomNum
             * we are displaying a error message */
            System.out.println("Room number is Invalid!!!");
        }
    }

    /**
     * find method which finds a customer in the room
     * @param rooms - array which holds the room data
     */
    public static void find(Room[] rooms) {
        System.out.print("Enter Customer First name -> ");
        String customer = input.next();
        int customerCount = 0;
        /* looping through the array & checking whether it is
         * equal to the entered customer name  */
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i].getPayingGuest().getFirstName().equalsIgnoreCase(customer)){
                System.out.println("Room number is " + (i + 1));
                customerCount++;
            }
        }
        // if we could not find the customer we are displaying a message
        if(customerCount == 0){
            System.out.println("Could not find Customer!!!");
        }
    }

    /**
     * store method to store the current customer data to a text file
     * @param rooms - array which holds the room data
     */
    public static void store(Room[] rooms) {
        try {
            // Using Formatter to write data to the text file
            Formatter formatter = new Formatter("src/store2.txt");
            // looping through the array to store all room details
            for (int i = 0; i < rooms.length; i++) {
                formatter.format("Room number %d is Occupied by %s %s [%s] with %d guests\n",
                        (i + 1),
                        rooms[i].getPayingGuest().getFirstName(),
                        rooms[i].getPayingGuest().getSurName(),
                        rooms[i].getPayingGuest().getCreditCardNumber(),
                        rooms[i].getGuestsInRoom()
                );
            }
            formatter.close();
            System.out.println("Hotel data Stored Successfully!");
        } catch (Exception e){
            System.out.println("Could not store the data!!!");
        }
    }

    /**
     * load method which loads the customer names to the program
     * @param rooms - array which holds the room data
     */
    public static void load(Room[] rooms) {
        try{
            String path = "src/load2.txt"; // file path
            // Scanner to read the data
            Scanner file = new Scanner(new File(path));
            // looping until we reach the end of the file
            while (file.hasNext()){
                int n = file.nextInt();
                String firstName = file.next();
                String surName  = file.next();
                String creditCardNumber = file.next();
                int guestsInRoom = file.nextInt();

                setRoomData(rooms[n],guestsInRoom,firstName,surName,creditCardNumber);
            }
            file.close();
            System.out.println("Hotel data Loaded Successfully!");
        } catch (Exception e){
            System.out.println("Could not load the data!!!");
        }
    }

    /**
     * viewByOrder to print customer names in alphabetical order
     * @param rooms - array which holds the room data
     */
    public static void viewByOrder(Room[] rooms) {
        /* We are copying the content to a string array because
         * if we use the same array it will change the array indexes */
        String[] sortedCustomers = new String[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            sortedCustomers[i] = rooms[i].getPayingGuest().getFirstName() + " " + rooms[i].getPayingGuest().getSurName();
        }

        // Using bubble sort algorithm to sort the customer names
        System.out.println("-----Guests in Alphabetical order-----");

        for (int j = 0; j < sortedCustomers.length; j++) {
            for (int i = j + 1; i < sortedCustomers.length; i++) {
                if (sortedCustomers[i].compareTo(sortedCustomers[j]) < 0) {
                    String temp = sortedCustomers[j];
                    sortedCustomers[j] = sortedCustomers[i];
                    sortedCustomers[i] = temp;
                }
            }
            // printing all rooms except empty rooms
            if (!sortedCustomers[j].equals("empty empty")) {
                System.out.println(sortedCustomers[j]);
            }
        }
    }

    /**
     * addCustomer to prompt & get user input
     * @param room - array which holds the room data
     */
    public static void addCustomer(Room room) {
        // getting all data from user using prompt
        System.out.print("Enter First name of the Paying Guest -> ");
        String firstName = input.next();

        System.out.print("Enter Surname of the Paying Guest -> ");
        String surName = input.next();

        System.out.print("Enter Credit Card number -> ");
        String creditCardNumber = input.next();

        System.out.print("Enter Guests In Room ( > 0) -> ");
        int guestsInRoom = input.nextInt();

        // checking if the Guests In Room > 0
        if (guestsInRoom > 0) {
            setRoomData(room,guestsInRoom,firstName,surName,creditCardNumber);
            System.out.println("Customer Added!");
        } else {
            System.out.println("Guests in room is invalid!!!\nCustomer is not added!");
        }
    }

    /**
     * setRoomData to set the room
     * @param room - array which holds the room data
     */
    public static void setRoomData(Room room, int guestsInRoom,
                                   String firstName, String surName, String creditCardNumber) {

        room.setGuestsInRoom(guestsInRoom);
        Person person = new Person(firstName,surName,creditCardNumber);
        room.setPayingGuest(person);
    }
}