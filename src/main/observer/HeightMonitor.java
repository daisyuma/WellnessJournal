package observer;

public class HeightMonitor implements Observer {
    public HeightMonitor() {
        super();
    }

    public void update(int before, int after) {
        System.out.println("congrat! Your plant is growing!");
        System.out.println("your plant was " + before + " cm");
        System.out.println("now your plant is " + after + " cm!");
    }
}
