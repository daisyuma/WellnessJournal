package ui;


import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import model.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class WellnessJournal {
    private static Scanner scanner = new Scanner(System.in);
    private User myUser;
    private Plant myPlant;


    public WellnessJournal() throws IOException, ParseException {
        welcome();
        myUser = new User();
        myPlant = askPlant();
        myPlant.loadHeight();
        setUpUser(myUser);
        boolean complete = false;
        try {
            complete = askComplete();
        } catch (InvalidInputException e) {
            System.out.println("Your answer should be one of y or n");
        }
        myUser.setPlant(myPlant);
        myUser.loadPoint();
        myUser.addPoint(complete);
        myPlant.grow();
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
                "Please describe your HealthyGoal in one word"
        );
        String goal = scanner.nextLine();
        HealthyEntry myEntry = new HealthyEntry();
        System.out.println("How do you feel about your goal?");
        String journal = scanner.nextLine();
        try {
            myEntry.setGoal(goal);
            myEntry.setJournal(journal);
        } catch (EmptyInputException e) {
            System.out.println("You cannot add an empty journal");
        }
        return myEntry;
    }

    public void askLoadByGoal() throws InvalidInputException {
        System.out.println("Do you want to load all the entries?"
                + "if yes, please enter y, or else please specify a goal you would like to load from");
        String answer = scanner.next();
        if (answer.equals("y")) {
            for (HealthyEntry entry : myUser.getEntries()) {
                printEntry(entry.getGoal(), entry.getJournal());
            }
        } else {
            loadSpecificGoal(answer);
        }
    }

    public void loadSpecificGoal(String goal) throws InvalidInputException {
        myUser.setEntriesMap();
        if (!myUser.getEntriesMap().containsKey(goal)) {
            throw new InvalidInputException();
        } else {
            ArrayList<HealthyEntry> entries = myUser.getEntriesMap().get(goal);
            for (HealthyEntry entry : entries) {
                printEntry(goal, entry.getJournal());
            }
        }
    }

    public void setUpUser(User myUser) throws IOException {
        setUserName();
        HealthyEntry myEntry = setEntryOfTheDay();
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        this.myUser = myUser.loadEntry();
        try {
            askLoadByGoal();
        } catch (InvalidInputException e) {
            System.out.println("There are no entries for this goal");
        }
    }

    public void printEntry(String goal, String journal) {
        System.out.print("Goal: " + goal + " | ");
        System.out.println("Journal:" + journal);
    }


    public static void main(String[] args) throws IOException, ParseException {
        new WellnessJournal();
    }
}




