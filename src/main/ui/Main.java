package ui;

import model.User;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    static void greeting(String name) {
        System.out.println("Hello " + name + "!");
    }

    static void welcome() {
        String appName = "WellnessJournal";
        System.out.println("Welcome to " + appName);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  //create a scanner object
        System.out.println("Please enter your name");
        String userName = scanner.nextLine();

        System.out.println("Please enter your weight in kg");
        double userWeight = scanner.nextDouble();
        System.out.println("Lastly, please enter your height in cm!");
        double userHeight = scanner.nextDouble();
        greeting(userName);
        welcome();
        User myUser = new User(userName, userWeight, userHeight);
        System.out.println("What is your goal for today?");
        String goal = scanner.nextLine();
        myUser.addGoal(goal);
        System.out.println("Please record your diary entry for today");
        String entry = scanner.nextLine();
        myUser.addEntry(entry);
        System.out.println("Congrats! You have accomplished your goal today!");
        //flower will grow
        //find some way to arrange the sout


    }
}

//System.out.println("Enter username");
//
//    String userName = myObj.nextLine();  // Read user input
//    System.out.println("Username is: " + userName);  // Output user input



