package model;

public class Tomato extends Plant {

    public Tomato() {
        super();
    }

    //MODIFIES :this
    //EFFECTS: if height is enough, change stage to sprout -> fruit -> ripen
    public void changeStage() {
        if (height < HEIGHT_TO_CHANGE_STATE_1) {
            System.out.println("Tomato: You need to work harder!");
        }
        if (height >= HEIGHT_TO_CHANGE_STATE_1 && height < HEIGhT_TO_CHANGE_STATE_2) {
            stage = "sprout";
            System.out.println("Tomato: fruit is starting to grow!");
        } else if (height >= HEIGhT_TO_CHANGE_STATE_2 && height < HEIGHT_TO_CHANGE_STATE_FINAL) {
            stage = "fruit";
            System.out.println("Tomato: The fruit is growing!");
        } else if (height >= HEIGHT_TO_CHANGE_STATE_FINAL) {
            stage = "ripe";
            System.out.println("Tomato: I'm ripening!");
        }
    }
}


