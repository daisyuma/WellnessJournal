package ui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String goal;
    private String journal;

    public FormEvent(Object source) {  //source of the event. in our case will be the button in our form panel
        super(source);
    }

    public FormEvent(Object source, String goal, String journal) {
        super(source);
        this.goal = goal;
        this.journal = journal;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
