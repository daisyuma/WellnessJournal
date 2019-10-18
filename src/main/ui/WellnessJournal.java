package ui;


import exceptions.EmptyInputException;
import exceptions.InvalidGoalException;
import exceptions.InvalidInputException;
import model.*;

import java.io.File;
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
        myPlant.loadHeight();
        run(myUser);
        boolean complete = false;
        try {
            complete = askComplete();
        } catch (InvalidInputException e) {
            System.out.println("Your answer should be one of y or n");
        }
        myUser.addPoint(complete);
        int leftOverPoints = myPlant.grow(myUser.getPoints());
        myUser.setPoint(leftOverPoints);
        myUser.savePoint();
        myPlant.changeStage();
        myPlant.saveHeight();
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
    public boolean askComplete() throws InvalidInputException {
        System.out.println("Did you complete your goal for today?"
                + " if yes, please answer y, if not, please answer n ");
        String answer = scanner.next();
        boolean complete = true;
        if (answer.equals("y")) {
            complete = true;
        } else if (answer.equals("n")) {
            complete = false;
        } else {
            throw new InvalidInputException();
        }
        return complete;
    }

    public void setUserName() {
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        myUser.setName(name);

    }

    public HealthyEntry setEntryOfTheDay() {
        System.out.println(
                "Please enter your HealthyGoal as one of: exercise, drink_water, or eat_healthy"
        );
        String goal = scanner.nextLine();
        HealthyEntry myEntry = new HealthyEntry();
        myEntry.setDate();
        System.out.println("How do you feel about your goal?");
        String journal = scanner.nextLine();
        try {
            myEntry.setGoal(goal);
            myEntry.setJournal(journal);
        } catch (InvalidGoalException e) {
            System.out.println("Your input is not one of exercise, drink_water, or eat_healthy");
        } catch (EmptyInputException e) {
            System.out.println("You cannot add an empty journal");
        }
        return myEntry;
    }

    public void run(User myUser) throws IOException {
        setUserName();
        HealthyEntry myEntry = setEntryOfTheDay();
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        myUser.loadEntry();
    }


    public static void main(String[] args) throws IOException {
        new WellnessJournal();
        // try doing the keep going thing
    }
}




