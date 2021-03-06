
import exceptions.EmptyInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;
import model.Plant;
import model.Flower;

import static org.junit.jupiter.api.Assertions.*;

import model.HealthyEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserTest {

    private User myUser;
    private HealthyEntry myEntry = new HealthyEntry();

    @BeforeEach
    public void runBefore() throws EmptyInputException {
        myUser = new User();
        myUser.setName("Daisy");
        myEntry.setGoal("exercise");
        myEntry.setJournal("i feel good");
        myUser.addEntry(myEntry);

    }

    @Test   //test that User has a plant and plant has that user
    public void testSetPlant() {
        Plant myPlant = new Flower();
        myUser.setPlant(myPlant);
        assertEquals(myPlant, myUser.getPlant());
        assertEquals(myUser, myPlant.getUser());
    }


    @Test
    public void testGetUserName() {
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
    public void testSaveEntry() throws IOException, EmptyInputException {
        HealthyEntry otherEntry = new HealthyEntry();
        otherEntry.setGoal("drink_water");
        otherEntry.setJournal("today is rainy");
        myUser.addEntry(otherEntry);
        myUser.saveEntry();
    }

    @Test
    void testLoadEntry() throws IOException {
        List<HealthyEntry> entry = myUser.getEntries();
        entry.clear();
        User user = myUser.loadEntry();
        List<HealthyEntry> dummyEntries = user.getEntries();
        HealthyEntry dummyEntry = dummyEntries.get(0);
        assertEquals("drink_water", dummyEntry.getGoal());
    }

    @Test
    public void testAddPointComplete() {
        myUser.addPoint(true);
        myUser.addPoint(true);
        assertEquals(4, myUser.getPoints());
    }

    @Test
    public void testAddPointIncomplete() {
        myUser.addPoint(false);
        assertEquals(0, myUser.getPoints());
    }

    @Test
    public void testSplitOnFirstSpace() {
        ArrayList<String> testStringList = myUser.splitOnFirstSpace("today is a nice day");
        ArrayList<String> dummyStringList = new ArrayList<>();
        dummyStringList.add("today");
        dummyStringList.add(" is a nice day");
        assertEquals(dummyStringList, testStringList);
    }

    @Test
    public void testSavePointAndLoadPoint() throws IOException {
        myUser.addPoint(true);
        myUser.addPoint(true); //user now has 4 points
        myUser.savePoint();
        myUser.setPoint(0);  //set point back to zero and see if load point will successfully load 4 points back
        myUser.loadPoint();
        assertEquals(4, myUser.getPoints());
    }

    @Test
    public void testSetEntriesMapWithNewGoal() throws EmptyInputException {
        HealthyEntry dummyEntry = new HealthyEntry();
        dummyEntry.setGoal("socialize");
        dummyEntry.setJournal("I made a new friend");
        myUser.addEntry(dummyEntry);
        myUser.setEntriesMap();
        HashMap<String, ArrayList<HealthyEntry>> map = myUser.getEntriesMap();
        assertEquals(dummyEntry,map.get("socialize").get(0));
    }

    @Test
    public void testSetEntriesMapWithAlreadyExistingGoal() throws EmptyInputException {
        HealthyEntry dummyEntry = new HealthyEntry();
        dummyEntry.setGoal("exercise");
        dummyEntry.setJournal("i exercised for 30min today");
        myUser.addEntry(dummyEntry);
        myUser.setEntriesMap();
        HashMap<String, ArrayList<HealthyEntry>> map = myUser.getEntriesMap();
        assertEquals(dummyEntry,map.get("exercise").get(1));
    }
}
