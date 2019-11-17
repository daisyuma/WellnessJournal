package ui;

import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import model.HealthyEntry;
import model.Plant;
import model.User;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {   //controller
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private TextPanel textPanel = new TextPanel();
    private ToolBar toolBar = new ToolBar();
    private JButton b1 = new JButton("Get Started!");  //pass in names for the Button
    private EntryPanel entryPanel = new EntryPanel();
    private User myUser;
    private Plant myPlant;

    public MainFrame() throws IOException, ParseException {
        super("WellnessJournal");  //setting the title of the JFrame to name of app
        setup();
        getStarted();
        setPlant();
        myPlant.loadHeight();
        myUser = new User();
        setUpUser(myUser);
        myUser.setPlant(myPlant);
    }

    //EFFECTS: setup the Frame of the GUI
    public void setup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit by pressing close button
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BorderLayout()); //a method in JFrame that sets the Layout
        add(textPanel, BorderLayout.WEST);  // add(Component, LayoutManager.STATICPOSITION)
        add(toolBar, BorderLayout.EAST);
        add(entryPanel, BorderLayout.CENTER);
        add(b1, BorderLayout.SOUTH);
    }


    //EFFECTS: prompts the textPanel to ask User to choose Plant
    // add an ActionListener to b1
    public void getStarted() {
        b1.addActionListener(this);
    }

    //EFFECTS: set myPlant to the plant Button chosen by User
    public void setPlant() {
        toolBar.setPlantListener(new PlantListener() {
            @Override
            public void setPlant(Plant plant, String string) {
                myPlant = plant;
                textPanel.displayText("your plant has been set to " + string);
            }
        });
    }

    public void run() {
        boolean complete = false;
        try {
            complete = askComplete();
        } catch (InvalidInputException e) {
            System.out.println("Your answer should be one of y or n");
        }
        myUser.setPlant(myPlant);
        myUser.loadPoint();  //load into TextArea as well
        myUser.addPoint(complete);
        myPlant.grow();
        myUser.savePoint();
        myPlant.changeStage();
        myPlant.saveHeight();  ///bring all the souts to the UI
    }


    public HealthyEntry setEntryOfTheDay() {
        HealthyEntry myEntry = new HealthyEntry();
        entryPanel.setFormListener(new FormListener() {
            public void formSubmitted(FormEvent e) {
                String goal = e.getGoal();
                String journal = e.getJournal();

                try {
                    myEntry.setGoal(goal);
                    myEntry.setJournal(journal);
                } catch (EmptyInputException ei) {
                    textPanel.displayText("You cannot add an empty entry");
                }
            }
        });
        return myEntry;
    }

    public void setUpUser(User myUser) throws IOException {
        HealthyEntry myEntry = setEntryOfTheDay();
        myUser.addEntry(myEntry);
        myUser.saveEntry();
        this.myUser = myUser.loadEntry();
//        try {
//            askLoadByGoal();
//        } catch (InvalidInputException e) {
//            System.out.println("There are no entries for this goal");
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textPanel.askForPlant();
    }

    public static void main(String[] args) throws IOException, ParseException {
        new MainFrame();
    }
}
