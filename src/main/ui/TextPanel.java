package ui;

import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TextPanel extends JPanel {
    private TextArea text = new TextArea();
    private JButton startButton = new JButton("Get Started!");

    TextPanel() throws IOException, ParseException {
        setLayout(new BorderLayout());
        Border innerBorder = BorderFactory.createTitledBorder("Entry of the Day");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        add(text, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
        add(startButton, BorderLayout.SOUTH);
    }


    //EFFECTS: asks for name
    void askForPlant() {
        text.setText("Welcome Back! "
                + "What kind of plant would you like to grow today? "
                + "Your default is a flower. Please select by clicking the buttons! ");
    }

    void displayText(String text) {
        this.text.setText(text);
    }

    void appendText(String text) {
        this.text.append(text + "\n");
    }
}
