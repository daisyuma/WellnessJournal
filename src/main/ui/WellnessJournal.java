package ui;


import model.*;

import java.io.IOException;
import java.util.Scanner;


public class WellnessJournal {
    private static Scanner scanner = new Scanner(System.in);
    private User myUser;
    private Plant myPlant;

    public WellnessJournal() throws IOException {
        welcome();
        myUser = new User();
        myUser.loadPoint();
        myPlant = askPlant();
        run(myUser);
        boolean complete = askComplete();
        myUser.addPoint(complete);
        int leftOverPoints = myPlant.grow(myUser.getPoints());
        myUser.setPoint(leftOverPoints);
        myUser.savePoint();
        myPlant.changeStage();
    }


    public void welcome() {
        String appName = "Bloom";
        System.out.println("Welcome to " + appName);
    }

    public Plant askPlant() {
        System.out.println("What kind of plant would you like to grow? Your default is a flower"
                + " if you want a tomato instead type 2");
        int plantAnswer = scanner.nextInt();
        Plant myPlant = new Flower();
        if (plantAnswer == 2) {
            myPlant = new Tomato();
        }
        return myPlant;
    }


    //EFFECTS: returns true if user completed their goal of the day
    //      - else return false
    public boolean askComplete() {
        System.out.println("Did you complete your goal for today?"
                + " if yes, please answer y, if not, please answer n ");
        String answer = scanner.next();
        boolean complete = true;
        if (answer.equals("y")) {
            complete = true;
        } else if (answer.equals("n")) {
            complete = false;
        }
        return complete;
    }

    public void run(User myUser) throws IOException {
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        myUser.setName(name);
        System.out.println(
                "Please enter your HealthyGoal. "
                        + "Your goal should be one of: exercise, drink_water, or eat_healthy"
        );
        String goal = scanner.nextLine();
        HealthyEntry myEntry = new HealthyEntry();
        myEntry.setDate();
        myEntry.setGoal(goal);
        System.out.println("How do you feel about your goal?");
        String journal = scanner.nextLine();
        myEntry.setGoal(goal);
        myEntry.setJournal(journal);
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        myUser.loadEntry();
    }


    public static void main(String[] args) throws IOException {
        new WellnessJournal();
        // try doing the keep going thing
    }
}




