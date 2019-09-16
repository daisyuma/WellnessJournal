package ui;

import model.User;

import model.HealthyGoal;

public class Main {
    static void greeting(String name) {
        System.out.println("Hello " + name + "!");
    }

    static void welcome() {
        String appName = "WellnessJournal";
        System.out.println("Welcome to " + appName);
    }

    static void askToChoose() {
        System.out.println("Please choose a healthy goal for today. you can choose either"
                + " 1) Fitness, or 2) Healthy Food");
    }

    public static void main(String[] args) {
        greeting("Daisy");
        welcome();
        askToChoose();
        int x = 1;
        User.chooseGoal(x);

    }
}



