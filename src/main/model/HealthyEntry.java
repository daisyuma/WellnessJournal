package model;

import java.time.LocalDate;


public class HealthyEntry {

    protected LocalDate date;
    protected String goal;
    protected String journal;

    //constructor
    public HealthyEntry() {
    }


    //REQUIRES: goal is one of: "exercise", "drink water", or "eat healthy"
    //EFFECTS: set the goal
    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getGoal() {
        return this.goal;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getJournal() {
        return this.journal;
    }


}











