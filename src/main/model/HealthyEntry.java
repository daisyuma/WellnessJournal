package model;

import java.time.LocalDate;



public class HealthyEntry {
//    enum Goal {
//        EXERCISE, DRINK_WATER, EAT_HEALTHY }

    private LocalDate date;
    private String goal;
    private String journal;

    //constructor
    public HealthyEntry() {
    }

    //setters

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

    //getters

    public String getGoal() {
        return this.goal;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getJournal() {
        return this.journal;
    }

//    public void checkGoal() {
//        switch (this.goal) {
//            case EXERCISE:
//                System.out.println("My goal is exercise");
//                break;
//            case DRINK_WATER:
//                System.out.println("My goal is drink water");
//                break;
//            case EAT_HEALTHY:
//                System.out.println("My goal is eat healthy");
//                break;
//            default:
//        }
//    }


}











