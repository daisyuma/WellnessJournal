package ui;

import java.util.EventObject;

public class EntryEvent extends EventObject {
    private String goal;
    private String journal;

    public EntryEvent(Object source) {  //source of the event. in our case will be the button in our form panel
        super(source);
    }

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
