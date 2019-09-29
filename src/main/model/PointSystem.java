package model;

public interface PointSystem {
    //MODIFIES: this
    //EFFECT: add point to the current point
    public void addPoint();

    //EFFECT: get the number of points
    public void getPoint();

    //EFFECT: reward based on the amount of point
    public void reward();


}
