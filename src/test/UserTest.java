
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import static org.junit.jupiter.api.Assertions.*;
import model.HealthyEntry;

public class UserTest {

    private User myUser;
    private HealthyEntry myEntry = new HealthyEntry();

    @BeforeEach
    public void runBefore(){
        myUser =  new User();
        myUser.setName("Daisy");
        myEntry.setGoal("exercise");
        myUser.addEntry(myEntry);

    }


    @Test
    public void testGetUserName(){
        assertEquals("Daisy", myUser.getUserName());
    }



    @Test
    public void testAddGoal() {

        assertEquals(myEntry, myUser.getEntry(0));
    }

    @Test
    public void testEntrySize() {
        myUser.addEntry(myEntry);
        assertEquals(2, myUser.entrySize());
    }

    @Test
    public void testAddPointComplete() {
        myUser.addPoint(true);
        assertEquals(2, myUser.getPoint());
    }

    @Test
    public void testAddPointIncomplete(){
        myUser.addPoint(false);
        assertEquals(0, myUser.getPoint());
    }

   @Test
    public void testSave() {

   }

   @Test
    public void testLoad() {

   }





}
