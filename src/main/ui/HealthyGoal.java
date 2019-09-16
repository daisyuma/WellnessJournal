package ui;

import java.util.ArrayList;

public class HealthyGoal {
    private ArrayList<String> fitness;
    private ArrayList<String> healthyfood;

    public HealthyGoal() {
        fitness = new ArrayList<>();
        healthyfood = new ArrayList<>();
    }

    //setters

    //MODIFIES: this
    //EFFECT: add the user's input of food into the list healthyfood
    public void addFood(String food) {
        healthyfood.add(food);
    }

    public void addFitness(String exercise) {
        fitness.add(exercise);
    }
}










