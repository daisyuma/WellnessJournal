package model;


public class Flower extends Plant {

    public Flower() {
        super();
    }

    //MODIFIES :this
    //EFFECTS: if height is enough, change stage to one of sprout -> bud-> flower
    public void changeStage() {
        if (height < HEIGHT_TO_CHANGE_STAGE_1) {
            System.out.println("Flower: You need to work harder!!");
        }
        if (height >= HEIGHT_TO_CHANGE_STAGE_1 && height < HEIGhT_TO_CHANGE_STAGE_2) {
            this.stage = "sprout";
            System.out.println("Flower: I'm sprouting!");
        } else if (height >= HEIGhT_TO_CHANGE_STAGE_2 && height < HEIGHT_TO_CHANGE_STAGE_FINAL) {
            stage = "bud";
            System.out.println("Flower: I'm budding!");
        } else if (height >= HEIGHT_TO_CHANGE_STAGE_FINAL) {
            stage = "flower";
            System.out.println("Flower: I'm flowering!");
        }
    }
}
