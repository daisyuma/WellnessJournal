package ui;

import model.User;

import java.util.Scanner;

public class WellnessJournal {
    private static Scanner scanner;

    public WellnessJournal() {
    }


    public void start() {
        welcome();
        User myUser = new User();
        setUp(myUser);
        //flower will grow
        //find some way to arrange the sout

    }

    public void welcome() {
        String appName = "WellnessLog";
        System.out.println("Welcome to " + appName);
    }

    public void setUp(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        user.setName(name);
        System.out.println("Please enter your weight in kg");
        double weight = scanner.nextDouble();
        user.setWeight(weight);
        System.out.println("Lastly, please enter your height in meters!");
        double height = scanner.nextDouble();
        user.setHeight(height);
    }


    public static void main(String[] args) {
        WellnessJournal myJournal = new WellnessJournal();
        myJournal.start();
    }
}

//System.out.println("Enter username");
//
//    String userName = myObj.nextLine();  // Read user input
//    System.out.println("Username is: " + userName);  // Output user input



