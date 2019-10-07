package ui;


import model.Flower;
import model.Plant;
import model.Tomato;
import model.User;

import java.io.IOException;
import java.util.Scanner;


public class WellnessJournal {
    private static Scanner scanner = new Scanner(System.in);

    public void start() throws IOException {
        welcome();
    }

    public void welcome() {
        String appName = "Bloom";
        System.out.println("Welcome to " + appName);
    }

    public Plant askPlant() {
        System.out.println("What kind of plant would you like to grow? Your default is a flower"
                + " if you want a tomato instead type 2");
        int plantAnswer = scanner.nextInt();
        Plant myPlant = new Flower();
        if (plantAnswer == 2) {
            myPlant = new Tomato();
        }
        return myPlant;
    }

    public boolean askComplete() {
        System.out.println("Did you complete your goal for today?"
                + " if yes, please answer yes, if not, please answer no ");
        String answer = scanner.nextLine();
        if (answer == "yes") {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        WellnessJournal myJournal = new WellnessJournal();
        myJournal.start();
        User myUser = new User();
        Plant myPlant = myJournal.askPlant();
        myUser.run();
        boolean complete = myJournal.askComplete();
        myUser.addPoint(complete);
        myPlant.grow(myUser.getPoints());
        myPlant.changeStage();
    }
}




