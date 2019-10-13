import model.Flower;
import model.Plant;
import model.Tomato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTomato {
    private Plant myTomato;

    @BeforeEach
    public void runBefore() {
        myTomato = new Tomato();
    }

    @Test
    public void testGrow() {
        int leftOverPoint = myTomato.grow(84);
        assertEquals(8, myTomato.getHeight());
        assertEquals(4, leftOverPoint);
    }

    @Test
    public void testGrowOnBOundary() {
        int leftOverPoint = myTomato.grow(20);
        assertEquals(2, myTomato.getHeight());
        assertEquals(0, leftOverPoint);
    }

    @Test
    public void testNoGrow() {
        int leftOverPoint = myTomato.grow(19);
        assertEquals(0, myTomato.getHeight());
        assertEquals(19, leftOverPoint);
    }
    @Test
    public void testNoChangeState() {
        myTomato.setHeight(5);
        myTomato.changeStage();
        assertEquals("seed", myTomato.getStage());
    }

    @Test
    public void testChangeStateFirst() {
        myTomato.setHeight(40);
        myTomato.changeStage();
        assertEquals("sprout", myTomato.getStage());
    }

    @Test
    public void testChangeStateSecond() {
        myTomato.setHeight(99);
        myTomato.changeStage();
        assertEquals("fruit", myTomato.getStage());
    }

    @Test
    public void testChangeStateFinal() {
        myTomato.setHeight(100);
        myTomato.changeStage();
        assertEquals("ripe", myTomato.getStage());
    }
}
