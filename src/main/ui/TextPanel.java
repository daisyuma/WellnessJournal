package ui;

import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class TextPanel extends JPanel {
    private TextArea text = new TextArea();

    TextPanel() throws IOException, ParseException {
        setLayout(new BorderLayout());
        Border innerBorder = BorderFactory.createTitledBorder("Entry of the Day");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        add(text, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
    }

    void displayText(String text) {
        this.text.setText(text);
    }

    void appendText(String text) {
        this.text.append(text + "\n");
    }
}
