package model;

import exceptions.EmptyInputException;

import java.time.LocalDate;


public class HealthyEntry {
    private String goal;
    private String journal;

    //constructor
    public HealthyEntry() {
    }


    //REQUIRES: goal is one of: "exercise", "drink water", or "eat healthy"
    //EFFECTS: set the goal
    public void setGoal(String goal) throws EmptyInputException {
        if (goal.isEmpty()) {
            throw new EmptyInputException();
        }
        this.goal = goal;
    }


    public void setJournal(String journal) throws EmptyInputException {
        if (journal.isEmpty()) {
            throw new EmptyInputException();
        }
        this.journal = journal;
    }

    public String getGoal() {
        return this.goal;
    }

    public String getJournal() {
        return this.journal;
    }


}











