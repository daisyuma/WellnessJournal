import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import model.HealthyEntry;
public class HealthyEntryTest {

    private HealthyEntry myEntry;

    @BeforeEach
    public void runBefore() {
        myEntry = new HealthyEntry();
        myEntry.setDate();
        myEntry.setGoal("exercise");
        myEntry.setJournal("I went to the gym today, feeling good!");
    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.now(), myEntry.getDate());
    }

    @Test
    public void testGetEntry() {
        assertEquals("I went to the gym today, feeling good!", myEntry.getJournal());
    }



//    @Test
//    public void testGetGoal() {
//        assertEquals(Goal.EXERCISE, myEntry.getGoal());
//    }   !!! how do I declare an enum at top level

}
