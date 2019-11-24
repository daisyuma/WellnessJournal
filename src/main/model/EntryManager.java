package model;

import exceptions.InvalidInputException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntryManager {
    private ArrayList<HealthyEntry> entries;
    private HashMap<String, ArrayList<HealthyEntry>> entriesMap;



    public EntryManager() {
        this.entries = new ArrayList<>();
        this.entriesMap = new HashMap<>();
    }

    public ArrayList<HealthyEntry> getEntries() {
        return this.entries;
    }

    //EFFECTS: return entry at the given index
    public HealthyEntry getEntry(int i) {
        return entries.get(i);
    }


    //MODIFIES: this
    //EFFECTS: adds a HealthyEntry to entries
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
