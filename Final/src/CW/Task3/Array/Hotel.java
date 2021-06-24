package CW.Task3.Array;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * The Hotel program implements an application that
 * does the following operations
 * add/view/delete/find/store/load/viewByOrder
 *
 * @author bhavan
 * @version Task 3 (Array Version)
 */
public class Hotel {
    // static Scanner object to get user input
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // int Array guestsInRoom to store guests in rooms
        int[] guestsInRoom = new int[8];
        /* String 2D Array hotel to store
         * firstname, surname, creditCardNumber for 8 rooms */
        String[][] person = new String[8][3];

        // initializing each room to the default value "empty"
        initialise(guestsInRoom, person);

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
                case "A" -> add(guestsInRoom, person);
                case "V" -> view(guestsInRoom, person);
                case "E" -> viewEmpty(guestsInRoom);
                case "D" -> delete(guestsInRoom, person);
                case "F" -> find(person);
                case "S" -> store(guestsInRoom, person);
                case "L" -> load(guestsInRoom, person);
                case "O" -> viewByOrder(person);
                case "X" -> System.out.println("Task Ended!\n");
                default -> System.out.println("Invalid Input! Try Again!\n");
            }
        } while(!choice.equals("X"));
    }

    /**
     * initialise method to set all rooms in hotel array to "empty"
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    private static void initialise(int[] guests, String[][] person) {
        for (int i = 0; i < person.length; i++) {
            guests[i] = 0;
            for (int j = 0; j < person[i].length; j++) {
                person[i][j] = "empty";
            }
        }
        System.out.println("All Rooms are initialized!\n");
    }

    /**
     * add method which adds a customer to the room
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void add(int[] guests, String[][] person) {
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
            if(person[n][0].equals("empty") || guests[n] == 0){
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
                if (guestsInRoom > 0){
                    guests[n] = guestsInRoom;
                    person[n][0] = firstName;
                    person[n][1] = surName;
                    person[n][2] = creditCardNumber;
                    System.out.println("Customer Added!");
                } else {
                    System.out.println("Guests in room is invalid!!!\nCustomer is not added!");
                }
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
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void view(int[] guests, String[][] person) {
        /* looping through the array to get every room
         * then displaying the details like a table */
        System.out.println("Firstname\tSurname\t\tCreditCard\tGuests");
        for (int i = 0; i < guests.length; i++) {
            System.out.println(
                person[i][0]  + "\t\t" +
                person[i][1]  + "\t\t" +
                person[i][2]  + "\t\t" +
                guests[i]
            );
        }
    }

    /**
     * viewEmpty method which view only the empty rooms
     * @param guests - array which holds the guests in room
     */
    public static void viewEmpty(int[] guests) {
        // looping through the array to find the room number
        int emptyRooms = 0;
        for (int i = 0; i < guests.length; i++) {
            if (guests[i] == 0) {
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
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void delete(int[] guests, String[][] person){
        try{
            System.out.print("Enter Room number to Remove the Customer -> ");
            int roomNum = input.nextInt();
            int n = roomNum - 1;

            /* checking if the room is already empty
             * else we are making the room "empty" */
            if (guests[n] == 0) {
                System.out.println("Room is already empty");
            } else {
                guests[n] = 0;
                person[n][0] = "empty";
                person[n][1] = "empty";
                person[n][2] = "empty";
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
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void find(String[][] person) {
        System.out.print("Enter Customer First Name -> ");
        String customer = input.next();
        int customerCount = 0;
        /* looping through the array & checking whether it is
         * equal to the entered customer name  */
        for (int i = 0; i < person.length; i++) {
            if(person[i][0].equalsIgnoreCase(customer)){
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
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void store(int[] guests, String[][] person){
        try {
            // Using Formatter to write data to the text file
            Formatter formatter = new Formatter("src/store2.txt");
            // looping through the array to store all room details
            for (int i = 0; i < guests.length; i++) {
                formatter.format("Room number %d is Occupied by %s %s [%s] with %d guests\n",
                        (i + 1),
                        person[i][0],
                        person[i][1],
                        person[i][2],
                        guests[i]);
            }
            formatter.close();
            System.out.println("Hotel data Stored Successfully!");
        } catch (Exception e){
            System.out.println("Could not store the data!!!");
        }
    }

    /**
     * load method which loads the customer names to the program
     * @param guests - array which holds the guests in room
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void load(int[] guests, String[][] person) {
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

                person[n][0] = firstName;
                person[n][1] = surName;
                person[n][2] = creditCardNumber;
                guests[n] = guestsInRoom;
            }
            file.close();
            System.out.println("Hotel data Loaded Successfully!");
        } catch (Exception e){
            System.out.println("Could not load the data!!!");
        }
    }

    /**
     * viewByOrder to print customer names in alphabetical order
     * @param person - 2D array to hold firstname, surname, creditCardNumber
     */
    public static void viewByOrder(String[][] person) {
        /* We are copying the content to another array because
         * if we use the same array it will change the array indexes */
        String[] sortedHotel = new String[person.length];
        for (int i = 0; i < person.length; i++) {
            sortedHotel[i] = person[i][0] + " " + person[i][1];
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
            if (!sortedHotel[j].equals("empty empty")){
                System.out.println(sortedHotel[j]);
            }
        }
    }
}