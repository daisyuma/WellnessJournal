import model.Flower;
import model.Plant;
import model.Tomato;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TomatoTest {
    private Plant myTomato;
    private User myUser;

    @BeforeEach
    public void runBefore() {
        myTomato = new Tomato();
        myUser = new User();
        myTomato.setUser(myUser);
    }

    @Test
    public void testGrow() {
        myUser.setPoint(84);
        myTomato.grow();
        assertEquals(8, myTomato.getHeight());
        assertEquals(4, myUser.getPoints());
    }

    @Test
    public void testGrowOnBoundary() {
        myUser.setPoint(20);
        myTomato.grow();
        assertEquals(2, myTomato.getHeight());
        assertEquals(0, myUser.getPoints());
    }

    @Test
    public void testNoGrow() {
        myUser.setPoint(19);
        myTomato.grow();
        assertEquals(0, myTomato.getHeight());
        assertEquals(19, myUser.getPoints());
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

    @Test
    public void testSaveHeightAndLoadHeight() throws IOException {
        myTomato.setHeight(20);
        myTomato.saveHeight();
        myTomato.setHeight(0);  //set height back to zero and see if load height will successfully load height back
        myTomato.loadHeight();
        assertEquals(20, myTomato.getHeight());
    }
}
