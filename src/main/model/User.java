package model;

//reference the Transcript example from lecture
//reference lab4 -- have like a reward system that goes towards your flower

import exceptions.EmptyInputException;
import exceptions.InvalidInputException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


public class User implements Loadable, Saveable {
    private String name;
    private ArrayList<HealthyEntry> entries;
    private HashMap<String, ArrayList<HealthyEntry>> entriesMap;
    private int points;
    private Plant plant = null;
    public static int POINTS_FOR_GOAL = 2;


    //constructor
    public User() {
        this.entries = new ArrayList<>();
        this.entriesMap = new HashMap<>();
        this.points = 0;
    }

    //getters


    //EFFECTS: returns the user's name
    public String getUserName() {
        return this.name;
    }

    public ArrayList<HealthyEntry> getEntries() {
        return this.entries;
    }

    //EFFECTS: return the number of points user have
    public int getPoints() {
        return this.points;
    }

    //EFFECTS: return this user's plant
    public Plant getPlant() {
        return this.plant;
    }

    //EFFECTS: return entry at the given index
    public HealthyEntry getEntry(int i) {
        return entries.get(i);
    }


    //setters

    //MODIfIES: this
    //EFFECTS: set name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: assign a plant to this and assign this to that plant
    public void setPlant(Plant plant) {
        if (this.plant == null) {
            this.plant = plant;
            plant.setUser(this);
        }
    }

    //MODIFIES: this
    //EFFECTS: set point
    public void setPoint(int point) {
        this.points = point;
    }


    public void addEntry(HealthyEntry entry) {
        entries.add(entry);
    }


    //MODIFIES; this
    //EFFECTS: add HealthyEntry to corresponding goal, if goal does not exist, create new key of that goal
    //         and add entry to that goal
    public void setEntriesMap() {
        for (HealthyEntry entry : entries) {
            String goal = entry.getGoal();
            if (entriesMap.containsKey(goal)) {
                ArrayList<HealthyEntry> entries = entriesMap.get(goal);
                entries.add(entry);
            } else {
                entriesMap.put(goal, new ArrayList<>());
                ArrayList<HealthyEntry> entries = entriesMap.get(goal);
                entries.add(entry);
            }
        }
    }

    //EFFECTS: Returns the user's entriesMap
    public HashMap<String, ArrayList<HealthyEntry>> getEntriesMap() {
        return entriesMap;
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
        List<String> points = Files.readAllLines(Paths.get("./data/points.txt")); //there's only one line in this file
        Integer point = this.points;
        String pointString = Integer.toString(point);
        points.clear();
        points.add(pointString);
        PrintWriter writer = new PrintWriter("./data/points.txt", "UTF-8");
        writer.println(pointString);
        writer.close();
    }

    public void loadPoint() throws IOException {
        List<String> points = Files.readAllLines(Paths.get("./data/points.txt"));//there's only one line in this file
        String pointSoFar = points.get(0);
        int point = Integer.valueOf(pointSoFar);
        System.out.println("You have " + pointSoFar + " points so far");
        this.points = point;
    }


    public void saveEntry() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/outputfile.txt"));
        List<HealthyEntry> entries = getEntries();
        for (HealthyEntry entry : entries) {
            String goal = entry.getGoal();
            String journal = entry.getJournal();
            lines.add(goal + " " + journal);
            PrintWriter writer = new PrintWriter("./data/outputfile.txt", "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close(); //note -- if you miss this, the file will not be written at all.
        }
    }

    public User loadEntry() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/outputfile.txt"));
        User myUser = new User();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnFirstSpace(line);
            HealthyEntry entry = new HealthyEntry();
            String goal = (partsOfLine.get(0));
            String journal = (partsOfLine.get(1));
            try {
                entry.setGoal(goal);
                entry.setJournal(journal);
            } catch (InvalidInputException e) {
                System.out.println("An InvalidInputException is found in the file");
            }
            myUser.addEntry(entry);
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








    







