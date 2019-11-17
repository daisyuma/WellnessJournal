package ui;

import java.util.EventListener;

public interface EntryListener extends EventListener {
    void formSubmitted(EntryEvent e);
}
