package model;

//reference the Transcript example from lecture

import java.util.ArrayList;

/**
 * INVARIANT: healthyEntry list and goal list are the same size
 * each healthyEntry has a goal associated, and vice versa, at matching indices
 */


public class User {
    private String name;
    private double weight;
    private double height;
    private ArrayList<String> entries;
    private ArrayList<String> goals;  //how to make Goal some kind of enumeration //change goal to a class later


    //constructor
    public User() {
        this.entries = new ArrayList<>();
        this.goals = new ArrayList<>();

    }

    //getters


    //EFFECTS: returns the user's name
    public String getUserName() {
        return this.name;
    }

    //EFFECTS: returns the user's weight
    public double getWeight() {
        return this.weight;
    }

    //EFFECTS: returns the user's height
    public double getHeight() {
        return this.height;
    }

    //REQUIRES: course has to be in the list
    //EFFECTS: calculate and return the user's BMI
    public double calculateBMI() {
        double height = this.height;
        double weight = this.weight;
        double bmi = (weight / (height * height));
        return bmi;
    }


    //TODO: ask TA about the calendar
    // This method should return day, goal, and entry in some consistent String format
    //requires some form of recursion
    //REQUIRES: day has to be in the list
    //EFFECTS: return day, goal name and entry in day:goal:entry
    public String getGoalAndEntry(String goal) {
        return null; //stub
    }

    //EFFECT: return the entry of a given index in the entries list
    // how can i implement this?
    public String getEntryIndex(int index) {
        return "null";  //stub
    }

    //EFFECT: return the index of a given goal in the goals list
    public int getGoalIndex(String goal) {
        return 1; //stub
    }
    //* // Function to find the index of an element in a primitive array in Java
    //public static int find(int[] a, int target)
    //{
    //	for (int i = 0; i < a.length; i++)
    //		if (a[i] == target)
    //			return i;
    //
    //	return -1;
    //}


    //setters

    //MODIfIES: this
    //EFFECTS: set name
    public void setName(String name) {
        this.name = name;
    }

    //MODIfIES: this
    //EFFECTS: set height
    public void setHeight(double height) {
        this.height = height;
    }

    //MODIfIES: this
    //EFFECTS: set weight
    public void setWeight(double weight) {
        this.weight = weight;
    }


    //MODIFIES: this
    //EFFECTS: add entry to entries list
    public void addEntry(String entry) {
        this.entries.add(entry);
    }

    //REQUIRES: the goal should be one of : Exercise, Drink Water, Eat Healthy
    //EFFECTS: add goal to list of HealthyGoal
    public void addGoal(String goal) {
        this.goals.add(goal);
    }



    // EFFECTS: Returns the number of entries in the list
    public int entrySize() {
        return entries.size();
    }

    // EFFECTS: Returns the number of goals in the list
    public int goalSize() {
        return goals.size();
    }

    //EFFECTS: check if entry list and goal list have the same size
    public boolean checkIndex() {
        int goalSize = goalSize();
        int entrySize = entrySize();
        if (goalSize == entrySize) {
            return true;
        } else {
            return false;
        }
    }
}







    







