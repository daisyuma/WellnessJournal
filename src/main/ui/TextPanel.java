package ui;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends JPanel {
    private JTextArea entries = new JTextArea();
    private JTextArea plantStat = new JTextArea();
    private JTextArea pointStat = new JTextArea();

    TextPanel() {
        setLayout(new BorderLayout());
        createBorder(this, "Wellness Journal",5);
        setUpEntriesPanel();
        setUpPlantStatPanel();
        setUpPointStatPanel();
    }


    //MODIFIES: this
    //EFFECTS: set up size, border, and title of the entries Panel
    private void setUpEntriesPanel() {
        JPanel entriesPanel = new JPanel();
        setUpSize(400, 700, entries);
        entriesPanel.add(entries);
        createBorder(entriesPanel,"Healthy Entry",0);
        add(entriesPanel, BorderLayout.WEST);
    }

    //MODIFIES: this
    //EFFECTS: set up size, border, and title of the plant stat Panel
    private void setUpPlantStatPanel() {
        JPanel plantStatPanel = new JPanel();
        setUpSize(400, 700, plantStat);
        plantStatPanel.add(plantStat);
        createBorder(plantStatPanel, "Plant Status",0);
        add(plantStatPanel, BorderLayout.CENTER);

    }

    //MODIFIES: this
    //EFFECTS: set up size, border, and title of the points stat Panel
    private void setUpPointStatPanel() {
        JPanel pointStatPanel = new JPanel();
        setUpSize(400, 700, pointStat);
        pointStatPanel.add(pointStat);
        createBorder(pointStatPanel, "Points",0);
        add(pointStatPanel, BorderLayout.EAST);
    }

    //TOOLS:

    //EFFECTS: create a border for the give JPanel by the given title and border weight
    private void createBorder(JPanel panel, String title, int borderWeight) {
        Border innerBorder = BorderFactory.createTitledBorder(title);
        Border outerBorder = BorderFactory.createEmptyBorder(borderWeight, borderWeight, borderWeight, borderWeight);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }


    //EFFECTS: set up size for the given JComponent
    private void setUpSize(int width, int height, JComponent component) {
        Dimension dim = component.getPreferredSize();
        dim.setSize(width, height);
        component.setPreferredSize(dim);
    }


    //EFFECTS: append and display the given text
    void appendTextInEntriesField(String text) {
        this.entries.append(text + "\n");
    }

    void displayPlantStat(String text) {
        this.plantStat.append(text + "\n");
    }

    void displayPointStat(String text) {
        this.pointStat.append(text + "\n");
    }
}
