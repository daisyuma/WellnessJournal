package ui;

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

    public MainFrame() throws IOException, ParseException {
        super("WellnessJournal");  //setting the title of the JFrame to name of app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit by pressing close button
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BorderLayout()); //a method in JFrame that sets the Layout, pass in Layout type
        b1.addActionListener(this);
        add(textPanel, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
        add(toolBar, BorderLayout.EAST);
        add(b1, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textPanel.askForName();
    }
}
