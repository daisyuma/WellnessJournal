package model;


import java.util.ArrayList;

public class User {
    private String name;
    private double weight;
    private double height;
    private ArrayList<HealthyGoal> healthyGoals;

    //Constructor
    public User(String name, double weight, double height) {
        this.name = name;
        this.weight = weight;
        this.height = height;

    }

    //getters


    //setters


    //interactive methods

   //EFFECTS: prints the healthy goal the user chooses and 1)fitness or 2)food, if the goal is not one of the two choices
   //asks user to choose again and return 3

    public static int chooseGoal(int choice) {
        if (choice == 1) {
            System.out.println("You have selected a fitness goal for today");
            return 1;
        } else if (choice == 2) {
            System.out.println("You have selected to eat healthy food for today");
            return 2;
        } else {
            System.out.println("Please select one of the choices listed above");
            return 3;
        }
    }








}
