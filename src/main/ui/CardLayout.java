package ui;

import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import model.HealthyEntry;
import model.Plant;
import model.User;
import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class CardLayout extends Component implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    static final String TEXTPANEL = "TextPanel";
    static final String TOOLPANEL = "ToolPanel";
    static final String ENTRYPANEL = "EntryPanel";
    private TextPanel textPanel = new TextPanel();
    private ToolBar toolPanel = new ToolBar();
    private EntryPanel entryPanel = new EntryPanel();
    private User myUser;
    private Plant myPlant;
    private WebReader webReader = new WebReader();

    public CardLayout() throws IOException, ParseException {
    }

    public void addComponentToPane(Container pane) throws IOException, ParseException {
        //Put the JComboBox in a JPanel to get a nicer look.

        //Create the panel that contains the "cards".
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(textPanel, TEXTPANEL);
        cards.add(toolPanel, TOOLPANEL);
        cards.add(entryPanel, ENTRYPANEL);
        pane.add(cards, BorderLayout.CENTER);
    }

    //EFFECTS: set myPlant to the plant Button chosen by User
    public void setPlant() {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, TOOLPANEL); //TODO: refactor this out
        toolPanel.setPlantListener(new PlantListener() {
            @Override
            public void setPlant(Plant plant, String string) {
                myPlant = plant;
                JOptionPane.showMessageDialog(null, "your plant has been set to " + string);
                setEntryOfTheDay();
            }
        });
    }

    public HealthyEntry setEntryOfTheDay() {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, ENTRYPANEL);
        HealthyEntry myEntry = new HealthyEntry();
        entryPanel.setEntryListener(new EntryListener() {
            public void formSubmitted(EntryEvent e) {
                String goal = e.getGoal();
                String journal = e.getJournal();
                try {
                    myEntry.setGoal(goal);
                    myEntry.setJournal(journal);
                    myUser = new User();
                    setUpUser(myEntry);
                    askComplete();
                } catch (EmptyInputException | IOException ei) {
                    JOptionPane.showMessageDialog(null, "You cannot add an empty entry");
                }
            }
        });
        return myEntry;
    }

    private void setUpUser(HealthyEntry myEntry) throws IOException {
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        this.myUser = myUser.loadEntry();
    }



    //EFFECTS: ask the User if want to load all the entries
    // - if User input YES, load all entries
    // - if NO, prompt user to input goal to load from
    public void askLoadAll() throws IOException {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        myUser.addPoint(false);
        myUser.savePoint();
        //TODO: refactor this out into helper methods
        myPlant.changeStage();
        myPlant.saveHeight();  ///bring all the souts to the UI
        cl.show(cards, TEXTPANEL);
        textPanel.displayText(null);
        int loadByGoalInput = JOptionPane.showConfirmDialog(this, "Do you want to load all the entries?",
                "Load Format?", JOptionPane.YES_NO_OPTION);
        if (loadByGoalInput == JOptionPane.YES_OPTION) {
            for (HealthyEntry entry : myUser.getEntries()) {
                displayEntry(entry.getGoal(), entry.getJournal());
            }
            //TODO: implement else load by goal, maybe change JOptionPane type
        } else if (loadByGoalInput == JOptionPane.NO_OPTION) {
            String goalToLoadFrom = JOptionPane.showInputDialog("what goal do you want to load from?");
            try {
                loadSpecificGoal(goalToLoadFrom);
            } catch (InvalidInputException e) {
                textPanel.displayText("You have no entries for this goal");
            }
        }
    }

    private void loadSpecificGoal(String goal) throws InvalidInputException {
        myUser.setEntriesMap();
        if (!myUser.getEntriesMap().containsKey(goal)) {
            throw new InvalidInputException();
        } else {
            ArrayList<HealthyEntry> entries = myUser.getEntriesMap().get(goal);
            for (HealthyEntry entry : entries) {
                displayEntry(goal, entry.getJournal());
            }
        }
    }

    public void itemStateChanged(ItemEvent evt) {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    public void displayWeather() throws IOException, ParseException {
        textPanel.displayText(webReader.weatherForecast());
        setPlant();
    }


    public void askComplete() throws IOException {
        int answer = JOptionPane.showConfirmDialog(this,
                "Did you complete your goal?",
                "Complete?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            myUser.addPoint(true);
            myUser.setPlant(myPlant);
            myPlant.grow();
        }
        askLoadAll();

    }

    private void displayEntry(String goal, String journal) {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, TEXTPANEL);
        textPanel.appendText("Goal: " + goal + " | " + "Journal:" + journal);
    }
}
