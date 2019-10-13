package model;

public abstract class Plant {
    protected int height;
    protected String stage;
    protected static int GROWTH_REWARD = 2; //centimeter
    protected static int POINTS_FOR_GROWTH = 20;
    protected static int HEIGHT_TO_CHANGE_STATE_1 = 40;
    protected static int HEIGhT_TO_CHANGE_STATE_2 = 60;
    protected static int HEIGHT_TO_CHANGE_STATE_FINAL = 100;

    public Plant() {
        height = 0;
        stage = "seed";
    }

    //MODIFIES: this
    //EFFECT: - !!!if eligible, grow by GROWTH_REWARD and subtract point and allow growth and return leftover points,
    //        -if not return original points;
    public int grow(int points) {
        int leftOverPoint = points;
        if (points >= POINTS_FOR_GROWTH) {
            int ratio = points / POINTS_FOR_GROWTH;
            int heightIncrease = ratio * GROWTH_REWARD;
            height = height + heightIncrease;
            leftOverPoint = points - (ratio * POINTS_FOR_GROWTH);
        }
        return leftOverPoint;
    }

    //getters
    //EFFECTS: get the stage of the plant
    public String getStage() {
        return stage;
    }

    public int getHeight() {
        return height;
    }

    //setters
    //MODIFIES: this
    //EFFECTS: set the height(only used for testing
    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void changeStage();
}
