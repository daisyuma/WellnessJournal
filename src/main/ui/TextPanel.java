package ui;

import network.WebReader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TextPanel extends JPanel {
    private TextArea text = new TextArea();
    private WebReader webReader = new WebReader();

    public TextPanel() throws IOException, ParseException {
        text.setText(webReader.weatherForecast());
        setLayout(new BorderLayout());
        add(text, BorderLayout.CENTER);  // add(Component, LayoutManager.STATICPOSITION)
    }


    //EFFECTS: asks for name
    public void askForName() {
        text.setText("Please enter your name");
    }
}
