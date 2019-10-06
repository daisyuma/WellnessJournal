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
    //EFFECT: if eligible, grow by GROWTH_REWARD and return true, if not return false;
    public boolean grow(int points) {
        if (points >= POINTS_FOR_GROWTH) {
            int ratio = points / POINTS_FOR_GROWTH;
            int heightIncrease = ratio * GROWTH_REWARD;
            height = height + heightIncrease;
            return true;
        } else {
            return false;
        }
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

    //MODIFIES: this
    //EFFECTS: set the stage(only used for testing
    public void setStage(String stage) {
        this.stage = stage;
    }


    public abstract void changeStage();
}
