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
import java.io.IOException;
import java.util.ArrayList;

public class CardLayout extends Component {
    JPanel cards; //a panel that uses CardLayout
    static final String TEXTPANEL = "TextPanel";
    static final String TOOLPANEL = "ToolPanel";
    static final String ENTRYPANEL = "EntryPanel";
    static final String WELCOMEPANEL = "WelcomePanel";
    private TextPanel textPanel = new TextPanel();
    private WelcomePanel welcomePanel = new WelcomePanel();
    private ToolBar toolPanel = new ToolBar();
    private EntryPanel entryPanel = new EntryPanel();
    private User myUser = new User();
    private Plant myPlant;
    private WebReader webReader = new WebReader();

    public CardLayout() throws IOException, ParseException {
    }

    public void addComponentToPane(Container pane) throws IOException, ParseException {
        //Create the panel that contains the "cards".
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(welcomePanel, WELCOMEPANEL);
        cards.add(textPanel, TEXTPANEL);
        cards.add(toolPanel, TOOLPANEL);
        cards.add(entryPanel, ENTRYPANEL);
        pane.add(cards, BorderLayout.CENTER);
    }

    //EFFECTS: set myPlant to the plant Button chosen by User
    public void setPlant() {
        switchCard(TOOLPANEL);
        toolPanel.setPlantListener(new PlantListener() {
            @Override
            public void setPlant(Plant plant, String string) {
                myPlant = plant;
                try {
                    myPlant.loadHeight();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "your plant has been set to " + string);
                setEntryOfTheDay();
            }
        });
    }

    public void switchCard(String panel) {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, panel);
    }

    public HealthyEntry setEntryOfTheDay() {
        switchCard(ENTRYPANEL);
        HealthyEntry myEntry = new HealthyEntry();
        entryPanel.setEntryListener(new EntryListener() {
            public void formSubmitted(EntryEvent e) {
                String goal = e.getGoal();
                String journal = e.getJournal();
                try {
                    myEntry.setGoal(goal);
                    myEntry.setJournal(journal);
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
        myUser.loadPoint();
    }


    //EFFECTS: ask the User if want to load all the entries
    // - if User input YES, load all entries
    // - if NO, prompt user to input goal to load from
    public void askLoadAll() {
        switchCard(TEXTPANEL);
        textPanel.displayText(null); // just to get rid of weather
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

    private void plantGrowAndChangeStage() {
        myPlant.grow();
        myPlant.changeStage();
        try {
            myPlant.saveHeight();  //TODO: bring all the souts to the UI
        } catch (IOException e) {
            e.printStackTrace();
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


    public void displayWeather() throws IOException, ParseException {
        welcomePanel.appendText("Welcome to Wellness Journal");
        welcomePanel.appendText(webReader.weatherForecast());
        welcomePanel.setStartListener(new StartListener() {
            @Override
            public void start() {
                setPlant();
            }
        });
    }


    public void askComplete() throws IOException {
        myUser.setPlant(myPlant);
        int answer = JOptionPane.showConfirmDialog(this,
                "Did you complete your goal?",
                "Complete?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            myUser.addPoint(true);
        } else {
            myUser.addPoint(false);
        }
        plantGrowAndChangeStage();
        myUser.savePoint();
        askLoadAll();
    }

    private void displayEntry(String goal, String journal) {
        switchCard(TEXTPANEL);
        textPanel.appendText("Goal: " + goal + " | " + "Journal:" + journal);
    }
}
