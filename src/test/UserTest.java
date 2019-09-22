
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    public User myUser;

    @BeforeEach
    public void runBefore(){
        myUser =  new User("Daisy", 55, 1.55);
        myUser.addEntry("I did 10 push-ups");
        myUser.addGoal("Exercise");
        myUser.addEntry("I drank 7 cups of water, feeling hydrated!");
        myUser.addGoal("Drink Water");
    }


    @Test
    public void testGetUserName(){
        assertEquals("Daisy", myUser.getUserName());
    }

    @Test
    public void testGetWeight(){
        assertEquals(55, myUser.getWeight());
    }

    @Test
    public void testCalculateBMI(){
        assertEquals((55/(1.55*1.55)), myUser.calculateBMI());
    }

    //@Test
    //public void testGoalAndEntry() {
    // assertEquals("Exercise: I did 10 push-ups, Drink Water:I drank 7 cups of water, feeling hydrated!");
    //}

    //@Test
    //public void testGetEntryIndex() {
        //assertEquals("I drank 7 cups of water, feeling hydrated!", myuser.getEntryIndex(1));
    //}

    @Test
    public void testCheckIndexWhenEqualSize(){
        assertTrue(myUser.checkIndex());
    }

    @Test
    public void testCheckIndexWhenUnequalSize(){
        myUser.addGoal("Healthy Food");
        assertFalse(myUser.checkIndex());
    }






}
