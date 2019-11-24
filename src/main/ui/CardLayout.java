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

    public CardLayout() {
    }


    //MODIFIES: this
    //EFFECTS: creates a new JPanel with CardLayout that contains three cards, and add them to pane
    void addComponentToPane(Container pane) {
        //Create the panel that contains the "cards".
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(welcomePanel, WELCOMEPANEL);
        cards.add(textPanel, TEXTPANEL);
        cards.add(toolPanel, TOOLPANEL);
        cards.add(entryPanel, ENTRYPANEL);
        pane.add(cards, BorderLayout.CENTER);
    }


    //EFFECTS: display Welcome message and weather to user, move to setPlant if startButton is clicked
    void displayWeather() throws IOException, ParseException {
        welcomePanel.appendText("Welcome to Wellness Journal");
        welcomePanel.appendText(webReader.weatherForecast());
        welcomePanel.setStartListener(new StartListener() {
            @Override
            public void start() {
                setPlant();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: set myPlant to the plant Button chosen by User, move on to setEntry step after chooses plant
    //         - load current height back to plant from a file
    private void setPlant() {
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
                setEntry();
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: set Entry to user inputs and add that entry to the User,
    // - if submit button is clicked, ask if User has completed their goal
    private void setEntry() {
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
    }


    //EFFECTS: asks if user has completed goal
    //  - if YES, adds points to User
    //  - allow Plant to grow
    //  - save points to a file
    //  - move on to askLoadAll()
    private void askComplete() throws IOException {
        int before = myUser.getPoints();
        myUser.setPlant(myPlant);
        int answer = JOptionPane.showConfirmDialog(this,
                "Did you complete your goal?",
                "Complete?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            myUser.addPoint(true);
            int after = myUser.getPoints();
            updatePoint(before, after);
        } else {
            myUser.addPoint(false);
        }
        growPlant();
        myUser.savePoint();
        askLoadAll();
    }


    //EFFECTS: asks how User wants to load all their entries
    //    - if YES, load all entries
    //    - if NO, load by goal
    private void askLoadAll() {
        switchCard(TEXTPANEL);
        int loadByGoalInput = JOptionPane.showConfirmDialog(this,
                "Do you want to load all the goals?",
                "Load Format?", JOptionPane.YES_NO_OPTION);
        if (loadByGoalInput == JOptionPane.YES_OPTION) {
            loadAllEntries();
            //TODO: implement else load by goal, maybe change JOptionPane type
        } else if (loadByGoalInput == JOptionPane.NO_OPTION) {
            String goalToLoadFrom = JOptionPane.showInputDialog("what goal do you want to load from?");
            try {
                loadSpecificGoal(goalToLoadFrom);
            } catch (InvalidInputException e) {
                textPanel.displayEntries("You have no entries for this goal");
            }
        }
        displayStat();
    }


    //EFFECTS: display plant stat and point stat on TextPanel
    private void displayStat() {
        displayPlantStat();
        displayPointStat();
    }

    private void displayPlantStat() {
        int height = myPlant.getHeight();
        String heightString = Integer.toString(height);
        String stage = myPlant.getStage();
        textPanel.displayPlantStat("Plant Height: " + heightString);
        textPanel.displayPlantStat("Plant Stage: " + stage);
    }

    private void displayPointStat() {
        int point = myUser.getPoints();
        String pointString = Integer.toString(point);
        textPanel.displayPointStat("You have " + pointString + " points now!");
    }

    //TOOLS:


    //MODIFIES: this
    //EFFECTS: - add entry to a User
    //         - save and load entries from a file
    //         - load points back to user
    private void setUpUser(HealthyEntry myEntry) throws IOException {
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        this.myUser = myUser.loadEntry();
        myUser.loadPoint();
    }


    //EFFECTS: update point changes to user with a pop-up message box
    private void updatePoint(int before, int after) {
        JOptionPane.showMessageDialog(null,
                "you have " + before + " points before \n"
                        + "now you have " + after + " points!");
    }


    //EFFECTS: switch the card currently being displayed to the ID passed in as parameter
    public void switchCard(String panel) {
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, panel);
    }


    //EFFECTS: load all entries in User
    private void loadAllEntries() {
        for (HealthyEntry entry : myUser.getEntries()) {
            displayEntry(entry.getGoal(), entry.getJournal());
        }
    }


    //EFFECTS: allows plant to grow and change stage
    //         - save height to a file
    private void growPlant() {
        myPlant.grow();
        myPlant.changeStage();
        try {
            myPlant.saveHeight();  //TODO: bring all the souts to the UI
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //EFFECTS: only display entries of a specific goal chosen by the user
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

    //EFFECTS: display entries on textPanel by a specific format
    private void displayEntry(String goal, String journal) {
        switchCard(TEXTPANEL);
        textPanel.displayEntries("Goal: " + goal + " | " + "Journal:" + journal);
    }
}
