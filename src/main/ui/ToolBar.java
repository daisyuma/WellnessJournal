package ui;

import model.Flower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel implements ActionListener {
    private JButton b1 = new JButton("Flower");
    private JButton b2 = new JButton("Tomato");

    public ToolBar() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(b1);
        add(b2);
        b1.addActionListener(MainFrame);
        b2.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbutton = (JButton) e.getSource();
        if (jbutton == b1) {
            Flower
        }
    }
}
