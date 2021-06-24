package CW.Task2;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * The Hotel program implements an application that
 * does the following operations (with Room)
 * add/view/delete/find/store/load/viewByOrder
 *
 * @author bhavan
 * @version Task 2 (Class Version)
 */
public class Hotel {
    // static Scanner object to get user input
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Room Array hotel to store customer names for 8 rooms
        Room[] rooms = new Room[8];

        // initializing each room to the default value "empty"
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
     * initialise method to set all rooms in hotel array to "empty"
     * @param hotel - array which holds the room data
     */
    private static void initialise(Room[] hotel) {
        for (int i = 0; i < hotel.length; i++) {
            hotel[i] = new Room("empty");
        }
        System.out.println("All Rooms are initialized!\n");
    }

    /**
     * add method which adds a customer to the room
     * @param hotel - array which holds the room data
     */
    public static void add(Room[] hotel) {
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
            if(hotel[n].getCustomerName().equals("empty")){
                System.out.print("Enter Customer name for Room number " + roomNum + " -> ");
                String customerName = input.next();
                hotel[n].setCustomerName(customerName);
                System.out.println("Customer Added!");
            } else {
                System.out.println("Room is already occupied! Try Again!!!");
            }
        } catch (Exception exception){
            /* If the user enters a invalid value for roomNum
             * we are displaying a error message */
            System.out.println("Room number is Invalid!!!");
        }
    }

    /**
     * view method which is to view all room details
     * @param hotel - array which holds the room data
     */
    public static void view(Room[] hotel) {
        /* looping through the array to get every room
         * if the room is empty we are displaying "...is empty"
         * if the room is not empty we are displaying "...is occupied by"
         * we are using (i+1) because array index starts from 0 */
        for (int i = 0; i < hotel.length; i++) {
            if(hotel[i].getCustomerName().equals("empty")){
                System.out.println("Room number " + (i + 1) + " is empty");
            } else {
                System.out.println("Room number " + (i + 1) + " is occupied by " + hotel[i].getCustomerName());
            }
        }
    }

    /**
     * viewEmpty method which view only the empty rooms
     * @param hotel - array which holds the room data
     */
    public static void viewEmpty(Room[] hotel) {
        // looping through the array to find the room number
        int emptyRooms = 0;
        for (int i = 0; i < hotel.length; i++) {
            if (hotel[i].getCustomerName().equals("empty")) {
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
     * @param hotel - array which holds the room data
     */
    public static void delete(Room[] hotel) {
        try{
            System.out.print("Enter Room number to Remove the Customer -> ");
            int roomNum = input.nextInt();
            int n = roomNum - 1;

            /* checking if the room is already empty
             * else we are making the room "empty" */
            if (hotel[n].getCustomerName().equals("empty")) {
                System.out.println("Room is already empty");
            } else {
                hotel[n].setCustomerName("empty");
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
     * @param hotel - array which holds the room data
     */
    public static void find(Room[] hotel) {
        System.out.print("Enter customer name -> ");
        String customer = input.next();
        int customerCount = 0;
        /* looping through the array & checking whether it is
         * equal to the entered customer name  */
        for (int i = 0; i < hotel.length; i++) {
            if(hotel[i].getCustomerName().equalsIgnoreCase(customer)){
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
     * @param hotel - array which holds the room data
     */
    public static void store(Room[] hotel) {
        try {
            // Using Formatter to write data to the text file
            Formatter formatter = new Formatter("src/store1.txt");
            // looping through the array to store all room details
            for (int i = 0; i < hotel.length; i++) {
                formatter.format("Room number %d is Occupied by %s\n", (i + 1), hotel[i].getCustomerName());
            }
            formatter.close();
            System.out.println("Hotel data Stored Successfully!");
        } catch (Exception e){
            System.out.println("Could not store the data!!!");
        }
    }

    /**
     * load method which loads the customer names to the program
     * @param hotel - array which holds the room data
     */
    public static void load(Room[] hotel) {
        try{
            String path = "src/load1.txt"; // file path
            // Scanner to read the data
            Scanner file = new Scanner(new File(path));
            // looping until we reach the end of the file
            while (file.hasNext()){
                int n = file.nextInt();
                String customerName = file.next();
                hotel[n].setCustomerName(customerName);
            }
            file.close();
            System.out.println("Hotel data Loaded Successfully!");
        } catch (Exception e){
            System.out.println("Could not load the data!!!");
        }
    }

    /**
     * viewByOrder to print customer names in alphabetical order
     * @param hotel - array which holds the room data
     */
    public static void viewByOrder(Room[] hotel) {
        /* We are copying the content to a string array because
         * if we use the same array it will change the array indexes */
        String[] sortedHotel = new String[hotel.length];
        for (int i = 0; i < hotel.length; i++) {
            sortedHotel[i] = hotel[i].getCustomerName();
        }

        // Using bubble sort algorithm to sort the customer names
        System.out.println("-----Guests in Alphabetical order-----");

        for (int j = 0; j < sortedHotel.length; j++) {
            for (int i = j + 1; i < sortedHotel.length; i++) {
                if (sortedHotel[i].compareTo(sortedHotel[j]) < 0) {
                    String temp = sortedHotel[j];
                    sortedHotel[j] = sortedHotel[i];
                    sortedHotel[i] = temp;
                }
            }
            // printing all rooms except empty rooms
            if (!sortedHotel[j].equals("empty")) {
                System.out.println(sortedHotel[j]);
            }
        }
    }
}