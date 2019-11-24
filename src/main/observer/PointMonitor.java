package observer;


public class PointMonitor implements Observer {

    @Override
    //EFFECTS: prints to console the points change
    public void update(int before, int after) {
        System.out.println("you have " + before + " points before");
        System.out.println("now you have " + after + " points!");
    }
}
