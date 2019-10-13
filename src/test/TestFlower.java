import model.Flower;
import model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFlower {
    private Plant myFlower;

    @BeforeEach
    public void runBefore() {
        myFlower = new Flower();
    }

    @Test
    public void testGrow() {
        int leftOverPoint = myFlower.grow(42);
        assertEquals(4, myFlower.getHeight());
        assertEquals(2, leftOverPoint);
    }

    @Test
    public void testNoGrow() {
        int leftOverPoint = myFlower.grow(12);
        assertEquals(0, myFlower.getHeight());
        assertEquals(12, leftOverPoint);

    }

    @Test
    public void testChangeStateNoChange() {
        myFlower.setHeight(5);
        myFlower.changeStage();
        assertEquals("seed", myFlower.getStage());
    }
    @Test
    public void testChangeStateFirst() {
        myFlower.setHeight(41);
        myFlower.changeStage();
        assertEquals("sprout", myFlower.getStage());
    }

    @Test
    public void testChangeStateSecond() {
        myFlower.setHeight(60);
        myFlower.changeStage();
        assertEquals("bud", myFlower.getStage());
    }

    @Test
    public void testChangeStateFinal() {
        myFlower.setHeight(111);
        myFlower.changeStage();
        assertEquals("flower", myFlower.getStage());
    }
}
