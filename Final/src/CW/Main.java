package CW;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String menu = (
            "+ - - - - - - - - - - - - - - - - - - - - - - +\n" +
            "|               NAVIGATION MENU               |\n" +
            "+ - - - - - - - - - - - - - - - - - - - - - - +\n" +
            "|   A.TASK 1                                  |\n" +
            "|   B.TASK 2                                  |\n" +
            "|   C.TASK 3 (CLASS)                          |\n" +
            "|   D.TASK 3 (ARRAY)                          |\n" +
            "|   E.TASK 4                                  |\n" +
            "|   X.EXIT PROGRAM                            |\n" +
            "+ - - - - - - - - - - - - - - - - - - - - - - +"
        );
        System.out.println(menu);

        String t1 = (
            "+---------------------------------------------+\n" +
            "|                   TASK 1                    |\n" +
            "+---------------------------------------------+\n"
        );
        String t2 = (
            "+---------------------------------------------+\n" +
            "|                   TASK 2                    |\n" +
            "+---------------------------------------------+\n"
        );
        String t3C = (
            "+---------------------------------------------+\n" +
            "|               TASK 3 (CLASS)                |\n" +
            "+---------------------------------------------+\n"
        );
        String t3A = (
            "+---------------------------------------------+\n" +
            "|               TASK 3 (ARRAY)                |\n" +
            "+---------------------------------------------+\n"
        );
        String t4 = (
            "+---------------------------------------------+\n" +
            "|                   TASK 4                    |\n" +
            "+---------------------------------------------+\n"
        );

        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.print("\nEnter Task -> ");
            choice = input.next().toUpperCase();
            switch (choice) {
                case "A" -> {
                    System.out.println(t1);
                    CW.Task1.Hotel.main(args);
                    System.out.println("-----------------------------------------------");
                }
                case "B" -> {
                    System.out.println(t2);
                    CW.Task2.Hotel.main(args);
                    System.out.println("-----------------------------------------------");
                }
                case "C" -> {
                    System.out.println(t3C);
                    CW.Task3.Class.Hotel.main(args);
                    System.out.println("-----------------------------------------------");
                }
                case "D" -> {
                    System.out.println(t3A);
                    CW.Task3.Array.Hotel.main(args);
                    System.out.println("-----------------------------------------------");
                }
                case "E" -> {
                    System.out.println(t4);
                    CW.Task4.Hotel.main(args);
                    System.out.println("-----------------------------------------------");
                }
                case "X" -> System.out.println("Program Ended! \nThank you!");
                default -> System.out.println("Invalid Input! Try Again!");
            }
        } while(!choice.equals("X"));
    }
}
