package ui;

import model.Flower;
import model.Tomato;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel implements ActionListener {
    private JButton b1 = new JButton("Flower");
    private JButton b2 = new JButton("Tomato");
    private PlantListener plantListener;

    public ToolBar() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        Dimension dim = getPreferredSize();
        dim.setSize(400, 600);
        setPreferredSize(dim);
        Border innerBorder = BorderFactory.createTitledBorder("Choose Your Plant");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }

    public void setPlantListener(PlantListener listener) {
        this.plantListener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbutton = (JButton) e.getSource();
        if (jbutton == b1) {
            if (plantListener != null) {
                plantListener.setPlant(new Flower(), "flower");
            }
        } else if (jbutton == b2) {
            if (plantListener != null) {
                plantListener.setPlant(new Tomato(), "tomato");
            }
        }
    }
}
