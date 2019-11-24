package observer;

public class HeightMonitor implements Observer {
    public HeightMonitor() {
        super();
    }


    //EFFECTS: prints to console the height change of plant
    public void update(int before, int after) {
        System.out.println("congrats! Your plant is growing!");
        System.out.println("your plant was " + before + " cm");
        System.out.println("now your plant is " + after + " cm!");
    }
}
