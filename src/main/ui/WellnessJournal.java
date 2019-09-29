package ui;

import model.Loadable;
import model.Saveable;
import model.User;
import model.HealthyEntry;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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




    public static void main(String[] args) throws IOException {
        WellnessJournal myJournal = new WellnessJournal();
        myJournal.start();
        User myUser = new User();
        myUser.run();

    }
}




