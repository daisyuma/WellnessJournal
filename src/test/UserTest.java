
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import static org.junit.jupiter.api.Assertions.*;
import model.HealthyEntry;

import java.io.IOException;
import java.util.ArrayList;

public class UserTest {

    private User myUser;
    private HealthyEntry myEntry = new HealthyEntry();

    @BeforeEach
    public void runBefore(){
        myUser =  new User();
        myUser.setName("Daisy");
        myEntry.setGoal("exercise");
        myEntry.setJournal("i feel good");
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
    public void testGetEntries() {
        ArrayList<HealthyEntry> dummyEntries = new ArrayList<>();
        dummyEntries.add(myEntry);
        assertEquals(dummyEntries, myUser.getEntries());
    }


   @Test
    public void testLoad() throws IOException {
   }





}
