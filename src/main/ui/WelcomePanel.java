package ui;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
    private TextArea text = new TextArea();
    private JButton startButton = new JButton("Get Started!");
    private StartListener startListener;

    public WelcomePanel() {
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
        setUpSize(300,200, startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startListener != null) {
                    startListener.start();
                }
            }
        });
    }

    private void setUpSize(int width, int height, JComponent component) {
        Dimension dim = component.getPreferredSize();
        dim.setSize(width, height);
        component.setPreferredSize(dim);
    }

    void setStartListener(StartListener startListener) {
        this.startListener = startListener;
    }


    void appendText(String text) {
        this.text.append(text + "\n");
    }
}
