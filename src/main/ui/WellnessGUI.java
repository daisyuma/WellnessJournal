package ui;

import com.sun.xml.internal.bind.v2.WellKnownNamespace;
import model.Plant;
import model.User;
import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WellnessGUI extends JFrame {
    private User myUser;
    private Plant myPlant;
    private WebReader webReader = new WebReader();

    public WellnessGUI() throws IOException, ParseException {
        MainFrame myFrame = new MainFrame();
    }

    public void initializeFields() {
    }

    public static void main(String[] args) throws IOException, ParseException {
        //new WellnessGUI();
        MainFrame myFrame = new MainFrame();

//        //Creating the Frame
//        JFrame frame = new JFrame("WellnessJournal");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit by pressing close button
//        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
//        JMenuBar mb = new JMenuBar();
//        JMenu m1 = new JMenu("FILE");
//        JMenu m2 = new JMenu("Help");
//        mb.add(m1);
//        mb.add(m2);
//        JMenuItem m11 = new JMenuItem("Open");
//        JMenuItem m22 = new JMenuItem("Save as");
//        m1.add(m11);
//        m1.add(m22);
//
//        //Creating the panel at bottom and adding components
//        JPanel panel = new JPanel(); // the panel is not visible in output
//        JLabel label = new JLabel("Enter Text");
//        JTextField tf = new JTextField(10); // accepts upto 10 characters
//        JButton send = new JButton("Send");
//        JButton reset = new JButton("Reset");
//        panel.add(label); // Components Added using Flow Layout
//        panel.add(label); // Components Added using Flow Layout
//        panel.add(tf);
//        panel.add(send);
//        panel.add(reset);
//
//        // Text Area at the Center
//        JTextArea ta = new JTextArea();
//
//        //Adding Components to the frame.
//        frame.getContentPane().add(BorderLayout.SOUTH, panel);
//        frame.getContentPane().add(BorderLayout.NORTH, mb);
//        frame.getContentPane().add(BorderLayout.CENTER, ta);
//        frame.setVisible(true);
    }


}
