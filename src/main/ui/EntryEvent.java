package ui;

import java.util.EventObject;

public class EntryEvent extends EventObject {
    private String goal;
    private String journal;
    private String complete;

    EntryEvent(Object source, String goal, String journal) {
        super(source);
        this.goal = goal;
        this.journal = journal;
    }

    String getGoal() {
        return goal;
    }

    String getJournal() {
        return journal;
    }

}
