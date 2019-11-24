package model;

//reference the Transcript example from lecture
//reference lab4 -- have like a reward system that goes towards your flower


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class User implements Loadable, Saveable {
    private String name;
    private EntryManager entryManager;
    private PointKeeper pointKeeper;
    private Plant plant = null;


    //constructor
    public User() {
        entryManager = new EntryManager();
        pointKeeper = new PointKeeper();
    }

    //getters

    //EFFECTS: returns the user's name
    public String getUserName() {
        return this.name;
    }

    public ArrayList<HealthyEntry> getEntries() {
        return entryManager.getEntries();
    }

    //EFFECTS: return the number of points user have
    public int getPoints() {
        return pointKeeper.getPoints();
    }

    //EFFECTS: return this user's plant
    public Plant getPlant() {
        return this.plant;
    }

    //EFFECTS: return entry at the given index
    public HealthyEntry getEntry(int i) {
        return entryManager.getEntry(i);
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
        pointKeeper.setPoint(point);
    }


    public void addEntry(HealthyEntry entry) {
        entryManager.addEntry(entry);
    }


    //MODIFIES; this
    //EFFECTS: add HealthyEntry to corresponding goal, if goal does not exist, create new key of that goal
    //         and add entry to that goal
    public void setEntriesMap() {
        entryManager.setEntriesMap();
    }

    //EFFECTS: Returns the user's entriesMap
    public HashMap<String, ArrayList<HealthyEntry>> getEntriesMap() {
        return entryManager.getEntriesMap();
    }

    // EFFECTS: Returns the number of goals in the list
    public int entrySize() {
        return entryManager.entrySize();
    }

    //MODIFIES: this
    //EFFECTS: if user completed their goal of the day
    //      - add POINTS_FOR_GOAL
    //else don't add point
    public void addPoint(boolean complete) {
        pointKeeper.addPoint(complete);
    }

    public void savePoint() throws IOException {
        pointKeeper.savePoint();
    }

    public void loadPoint() throws IOException {
        pointKeeper.loadPoint();
    }


    public void saveEntry() throws IOException {
        entryManager.saveEntry();
    }

    public User loadEntry() throws IOException {
        return entryManager.loadEntry();
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








    







