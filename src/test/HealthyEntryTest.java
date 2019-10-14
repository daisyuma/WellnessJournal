import exceptions.EmptyInputException;
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

    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.now(), myEntry.getDate());
    }

    @Test
    public void testSetEntryNoExceptionExpected() {
        try {
            myEntry.setJournal("I went to the gym today, feeling good!");
        } catch (EmptyInputException e) {
            fail("No Exception Expected here");
        }
    }

    @Test
    public void testSetEntryExceptionExpected() {
        try {
            myEntry.setJournal("");
            fail("Expected an exception here");
        } catch (EmptyInputException e) {
        }
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
