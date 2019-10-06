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
    public void testGrowTrue() {
        myTomato.grow(45);
        assertEquals(4, myTomato.getHeight());
        assertTrue(myTomato.grow(45));
    }

    @Test
    public void testGrowFalse() {
        assertFalse(myTomato.grow(19));
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
