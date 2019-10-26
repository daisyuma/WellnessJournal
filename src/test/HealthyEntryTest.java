import exceptions.EmptyInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import model.HealthyEntry;

public class HealthyEntryTest {

    private HealthyEntry myEntry;

    @BeforeEach
    public void runBefore() {
        myEntry = new HealthyEntry();

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
            System.out.println("exception expected here");
        }
    }

    @Test
    public void testSetGoalNoExceptionExpected() {
        try {
            myEntry.setGoal("exercise");
        } catch (EmptyInputException e) {
            fail("No exception should be thrown in this case");
        }
    }

    @Test
    public void testSetGoalExceptionExpected() {
        try {
            myEntry.setGoal("");
            fail("an exception should have been thrown");
        } catch (EmptyInputException e) {
        }
    }
}
