package model;

//reference the Transcript example from lecture
//reference lab4 -- have like a reward system that goes towards your flower

import exceptions.InvalidGoalException;
import exceptions.InvalidInputException;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class User implements Loadable, Saveable {
    private static Scanner scanner = new Scanner(System.in);
    private String name;
    private List<HealthyEntry> entries;//how to make Goal some kind of enumeration //change goal to a class later
    private int points;
    public static int POINTS_FOR_GOAL = 2;


    //constructor
    public User() {
        this.entries = new ArrayList<>();
        this.points = 0;

    }

    //getters


    //EFFECTS: returns the user's name
    public String getUserName() {
        return this.name;
    }

    //EFFECTS: given an index, return the entry of that index in the entries list
    public HealthyEntry getEntry(int i) {
        return this.entries.get(i);
    }

    public List<HealthyEntry> getEntries() {
        return this.entries;
    }

    //EFFECTS: return the number of points user have
    public int getPoints() {
        return this.points;
    }


    //setters

    //MODIfIES: this
    //EFFECTS: set name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: set point
    public void setPoint(int point) {
        this.points = point;
    }


    //REQUIRES: the goal should be one of : Exercise, Drink Water, Eat Healthy
    //EFFECTS: add HealthyGoal to entries
    public void addEntry(HealthyEntry newEntry) {
        this.entries.add(newEntry);
    }

    // EFFECTS: Returns the number of goals in the list
    public int entrySize() {
        return entries.size();
    }

    //MODIFIES: this
    //EFFECTS: if user completed their goal of the day
    //      - add POINTS_FOR_GOAL
    //else don't add point
    public void addPoint(boolean complete) {
        if (complete) {
            this.points = points + POINTS_FOR_GOAL;
        }
    }

    public void savePoint() throws IOException {
        List<String> points = Files.readAllLines(Paths.get("points.txt")); //there's only one line in this file
        Integer point = this.points;
        String pointString = Integer.toString(point);
        points.clear();
        points.add(pointString);
        PrintWriter writer = new PrintWriter("points.txt", "UTF-8");
        writer.println(pointString);
        writer.close();
    }

    public void loadPoint() throws IOException {
        List<String> points = Files.readAllLines(Paths.get("points.txt"));//there's only one line in this file
        String pointSoFar = points.get(0);
        int point = Integer.valueOf(pointSoFar);
        System.out.println("You have " + pointSoFar + " points so far");
        this.points = point;
    }


    public void saveEntry() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        List<HealthyEntry> entries = getEntries();
        for (HealthyEntry entry : entries) {
            String goal = entry.getGoal();
            String journal = entry.getJournal();
            lines.add(goal + " " + journal);
            PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close(); //note -- if you miss this, the file will not be written at all.
        }
    }

    public User loadEntry() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        User myUser = new User();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnFirstSpace(line);
            HealthyEntry entry = new HealthyEntry();
            try {
                entry.setGoal(partsOfLine.get(0));
            } catch (InvalidGoalException e) {
                System.out.println("an invalid goal is found in the file");
                e.printStackTrace();
            }
            String journal = (partsOfLine.get(1));
            entry.setJournal(journal);
            myUser.addEntry(entry);
            System.out.print("Goal: " + partsOfLine.get(0) + " | ");
            System.out.println("Journal:" + journal);
        }
        return myUser;
    }

    public static ArrayList<String> splitOnFirstSpace(String line) {
        ArrayList<String> splitOnFirstSpace = new ArrayList<>();
        int i = line.indexOf(" ");
        String goal = line.substring(0, i);
        String entry = line.substring(i++);
        splitOnFirstSpace.add(goal);
        splitOnFirstSpace.add(entry);
        return splitOnFirstSpace;
    }
}








    







