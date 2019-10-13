import exceptions.InvalidGoalException;
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

    @Test
    public void testSetGoalNoExceptionExpected() {
        try {
            myEntry.setGoal("exercise");
        } catch (InvalidGoalException e) {
            fail("No exception should be thrown in this case");
        }
    }

    @Test
    public void testSetGoalExceptionExpected() {
        try {
            myEntry.setGoal("play");
            fail("an exception should have been thrown");
        } catch (InvalidGoalException e) {
        }
    }
}
