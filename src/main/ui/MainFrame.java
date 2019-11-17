package ui;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit by pressing close button
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BorderLayout()); //a method in JFrame that sets the Layout, pass in Layout type
        b1.addActionListener(this);
        add(textPanel, BorderLayout.WEST);  // add(Component, LayoutManager.STATICPOSITION)
        add(toolBar, BorderLayout.EAST);
        add(entryPanel, BorderLayout.CENTER);
        add(b1, BorderLayout.SOUTH);
        toolBar.setPlantListener(new PlantListener() {
            @Override
            public void setPlant(Plant plant, String string) {
                myPlant = plant;
                textPanel.displayText("your plant has been set to " + string);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textPanel.askForName();
    }

    public static void main(String[] args) throws IOException, ParseException {
        new MainFrame();
    }
}
