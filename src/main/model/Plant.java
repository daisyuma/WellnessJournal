package model;

import observer.HeightMonitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Plant extends Subject {
    protected int height;
    protected String stage;
    protected User user = null;
    protected static int GROWTH_REWARD = 2; //centimeter
    protected static int POINTS_FOR_GROWTH = 20;
    protected static int HEIGHT_TO_CHANGE_STAGE_1 = 40;
    protected static int HEIGhT_TO_CHANGE_STAGE_2 = 60;
    protected static int HEIGHT_TO_CHANGE_STAGE_FINAL = 100;

    public Plant() {
        height = 0;
        stage = "seed";
        addObserver(new HeightMonitor());
    }

    //MODIFIES: this
    //EFFECT: if eligible, grow by GROWTH_REWARD and subtract point and allow growth
    //              and set user's points as leftover points,
    //              if not user will have the same points as before, no growth
    public void grow() {
        int leftOverPoint = user.getPoints();
        if (leftOverPoint >= POINTS_FOR_GROWTH) {
            int ratio = leftOverPoint / POINTS_FOR_GROWTH;
            int heightIncrease = ratio * GROWTH_REWARD;
            int before = this.height;
            height = height + heightIncrease;
            int after = this.height;
            notify(before, after);
            leftOverPoint = leftOverPoint - (ratio * POINTS_FOR_GROWTH);
        }
        user.setPoint(leftOverPoint);
    }


    //getters
    //EFFECTS: get the stage of the plant
    public String getStage() {
        return stage;
    }

    public int getHeight() {
        return height;
    }

    public User getUser() {
        return user;
    }

    //setters
    //MODIFIES: this
    //EFFECTS: set the height(only used for testing
    public void setHeight(int height) {
        this.height = height;
    }

    public void setUser(User user) {
        if (this.user == null) {
            this.user = user;
            user.setPlant(this);
        }
    }

    public abstract void changeStage();

    //EFFECTS: stores the current height in a file
    public void saveHeight() throws IOException {
        List<String> heights = Files.readAllLines(Paths.get("./data/height.txt")); //there's only one line in this file
        Integer updatedHeight = this.height;
        String heightString = Integer.toString(updatedHeight);
        heights.clear();
        heights.add(heightString);
        PrintWriter writer = new PrintWriter("./data/height.txt", "UTF-8");
        writer.println(heightString);
        writer.close();
    }

    //MODIFIES:this
    //EFFECTS: load height to this
    public void loadHeight() throws IOException {
        List<String> heights = Files.readAllLines(Paths.get("./data/height.txt"));//there's only one line in this file
        String heightSoFarString = heights.get(0);
        int height = Integer.valueOf(heightSoFarString);
        System.out.println("Your plant is " + heightSoFarString + " cm so far");
        this.height = height;
    }
}
