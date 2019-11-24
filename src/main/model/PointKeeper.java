package model;

import observer.PointMonitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PointKeeper extends Subject {
    private int points;
    public static int POINTS_FOR_GOAL = 2;

    public PointKeeper() {
        points = 0;
        addObserver(new PointMonitor());
        ;
    }

    //EFFECTS: return the number of points user have
    public int getPoints() {
        return this.points;
    }

    //MODIFIES: this
    //EFFECTS: set point
    public void setPoint(int point) {
        this.points = point;
    }

    //MODIFIES: this
    //EFFECTS: if user completed their goal of the day
    //      - add POINTS_FOR_GOAL
    //else don't add point
    public void addPoint(boolean complete) {
        if (complete) {
            int before = this.points;
            this.points = points + POINTS_FOR_GOAL;
            int after = this.points;
            notify(before, after);
        }
    }

    //EFFECTS: save current point to a file
    public void savePoint() throws IOException {
        List<String> points = Files.readAllLines(Paths.get("./data/points.txt")); //there's only one line in this file
        Integer point = this.points;
        String pointString = Integer.toString(point);
        points.clear();
        points.add(pointString);
        PrintWriter writer = new PrintWriter("./data/points.txt", "UTF-8");
        writer.println(pointString);
        writer.close();
    }


    //MODIFIES:this
    //EFFECTS: load point from a file to this
    public void loadPoint() throws IOException {
        List<String> points = Files.readAllLines(Paths.get("./data/points.txt"));//there's only one line in this file
        String pointSoFar = points.get(0);
        int point = Integer.valueOf(pointSoFar);
        this.points = point;
    }
}
