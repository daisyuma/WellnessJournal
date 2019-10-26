import model.Flower;
import model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class FlowerTest {
    private Plant myFlower;
    private User myUser;

    @BeforeEach
    public void runBefore() {
        myFlower = new Flower();
        myUser = new User();
        myFlower.setUser(myUser);
    }

    @Test
    public void testGrow() {
        myUser.setPoint(42);
        myFlower.grow();
        assertEquals(4, myFlower.getHeight());
        assertEquals(2, myUser.getPoints());
    }

    @Test
    public void testNoGrow() {
        myUser.setPoint(12);
        myFlower.grow();
        assertEquals(0, myFlower.getHeight());
        assertEquals(12, myUser.getPoints());

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

    @Test
    public void testSaveHeightAndLoadHeight() throws IOException {
        myFlower.setHeight(40);
        myFlower.saveHeight();
        myFlower.setHeight(0);  //set point back to zero and see if load point will successfully load 4 points back
        myFlower.loadHeight();
        assertEquals(40, myFlower.getHeight());
    }
}
