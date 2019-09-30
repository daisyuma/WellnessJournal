
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import static org.junit.jupiter.api.Assertions.*;
import model.HealthyEntry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public void testGetEntries() {
        assertEquals(myEntry, myUser.getEntry(0));
    }

    @Test
    public void testRun() {

    }

   @Test
    public void testSave() throws IOException {
       String fileName = "outputfile.txt";
       Path path = Paths.get(fileName);
       List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
       List<String> expected = new ArrayList<String>();
       expected.add("exercise i feel good");
       assertEquals(expected, allLines);
   }

   @Test
    public void testLoad() {

   }





}
