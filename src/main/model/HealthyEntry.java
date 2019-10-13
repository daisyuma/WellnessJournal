package model;

import exceptions.InvalidGoalException;

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
    public void setGoal(String goal) throws InvalidGoalException {
        if (goal.equals("exercise")) {
            this.goal = goal;
        } else if (goal.equals("drink_water")) {
            this.goal = goal;
        } else if (goal.equals("eat_healthy")) {
            this.goal = goal;
        } else {
            throw new InvalidGoalException();
        }
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











