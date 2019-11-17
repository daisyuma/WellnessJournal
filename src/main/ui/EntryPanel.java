package ui;

import com.oracle.tools.packager.windows.WinServiceBundler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryPanel extends JPanel {
    private JLabel goalLabel;
    private JLabel journalLabel;
    private JTextField goalField;
    private JTextField journalField;
    private JButton submitButton;
    private FormListener formListener;

    public EntryPanel() {
        goalLabel = new JLabel("Goal: ");
        journalLabel = new JLabel("Journal: ");
        goalField = new JTextField(10);
        journalField = new JTextField(10);
        submitButton = new JButton("ok!");
        Dimension dim = getPreferredSize();
        dim.setSize(400, 600);
        setPreferredSize(dim);
        Border innerBorder = BorderFactory.createTitledBorder("Entry of the Day");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;

        /////FIRST ROW//////
        gc.anchor = GridBagConstraints.LINE_END; //right hand side
        gc.insets = new Insets(0, 0, 0, 5); //some space on the right
        add(goalLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 20;
        gc.anchor = GridBagConstraints.LINE_START; //left hand side
        gc.insets = new Insets(0, 0, 0, 0);
        add(goalField, gc);

        ////SECOND ROW/////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(journalLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(journalField, gc);

        ///THIRD ROW////
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START; //top of line
        add(submitButton, gc);

        ////adding a listener to my ok button so when clicked send data in textfield to model

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goal = goalField.getText();
                String journal = journalField.getText();  //pass this info to main Frame
                FormEvent submitEvent = new FormEvent(this, goal, journal);
                if (formListener != null) {
                    formListener.formSubmitted(submitEvent);
                }
            }
        });
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
