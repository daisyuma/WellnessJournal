package ui;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WelcomePanel extends JPanel {
    private TextArea text = new TextArea();
    private JButton startButton = new JButton("Get Started!");
    private StartListener startListener;

    public WelcomePanel() throws IOException, ParseException {
        setUp();
    }

    private void setUp() {
        setLayout(new BorderLayout());
        Border innerBorder = BorderFactory.createTitledBorder("Welcome to WellnessJournal");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        add(text, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
        add(startButton, BorderLayout.SOUTH);
        setStartButton();
    }

    private void setStartButton() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startListener != null) {
                    startListener.start();
                }
            }
        });
    }

    void setStartListener(StartListener startListener) {
        this.startListener = startListener;
    }

    void displayText(String text) {
        this.text.setText(text);
    }

    void appendText(String text) {
        this.text.append(text + "\n");
    }
}
