package model;

//reference the Transcript example from lecture
//reference lab4 -- have like a reward system that goes towards your flower

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class User implements Loadable, Saveable {
    private static Scanner scanner = new Scanner(System.in);
    private List<String> lines;
    private String name;
    private List<HealthyEntry> entries;//how to make Goal some kind of enumeration //change goal to a class later
    private int point;
    public static int POINTS_FOR_GOAL = 2;
    public static int POINTS_FOR_GROWTH = 20;
    public static int GROWTH_REWARD = 1;


    //constructor
    public User() {
        this.entries = new ArrayList<>();
        this.point = 0;

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
    public int getPoint() {
        return this.point;
    }




    //setters

    //MODIfIES: this
    //EFFECTS: set name
    public void setName(String name) {
        this.name = name;
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
    //      - add POINTS_FOR_GOAL and then,
    //        - !!!if eligible, subtract point and allow growth
    //else don't add point
    public void addPoint(boolean complete) {
        if (complete) {
            this.point = point + POINTS_FOR_GOAL;
        }
    }

    public void run() throws IOException {
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        setName(name);
        System.out.println(
                "Please enter your HealthyGoal. "
                        + "Your goal should be one of: exercise, drink water, or eat healthy"
        );
        String goal = scanner.nextLine();
        HealthyEntry myEntry = new HealthyEntry();
        myEntry.setDate();
        myEntry.setGoal(goal);
        System.out.println("Please journal");
        String journal = scanner.nextLine();
        myEntry.setGoal(goal);
        myEntry.setJournal(journal);
        addEntry(myEntry);
        save();
        load();
    }

    public void save() throws IOException {
        this.lines = Files.readAllLines(Paths.get("outputfile.txt"));
        List<HealthyEntry> entries = getEntries();
        for (HealthyEntry entry : entries) {
            String goal = entry.getGoal();
            String journal = entry.getJournal();
            lines.add(goal + " " + journal);
        }


    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void load() throws IOException {
        PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("Goal:" + partsOfLine.get(0) + " | ");
            System.out.println("Journal: " + partsOfLine.get(1));
            writer.println(line);
        }
        writer.close(); //note -- if you miss this, the file will not be written at all.

    }





}








    







