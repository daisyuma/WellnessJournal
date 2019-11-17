package ui;

import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TextPanel extends JPanel {
    private TextArea text = new TextArea();
    private WebReader webReader = new WebReader();

    TextPanel() throws IOException, ParseException {
        text.setText(webReader.weatherForecast());
        setLayout(new BorderLayout());
        add(text, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
    }


    //EFFECTS: asks for name
    void askForPlant() {
        text.setText("Welcome Back! "
                + "What kind of plant would you like to grow today? "
                + "Please select by clicking the buttons! ");
    }

    void displayText(String text) {
        this.text.setText(text);
    }
}
