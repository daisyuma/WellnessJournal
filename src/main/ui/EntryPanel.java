package ui;

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
    private EntryListener entryListener;
    private GridBagConstraints gc = new GridBagConstraints();

    EntryPanel() {
        goalLabel = new JLabel("Goal: ");
        journalLabel = new JLabel("Journal: ");
        goalField = new JTextField(10);
        journalField = new JTextField(10);
        submitButton = new JButton("ok!");
        setUpSize();
        setUpLayout();
        setUpBorder();
        setSubmitButton();
    }

    //MODIFIES: this
    //EFFECTS: set up size of this Panel
    private void setUpSize() {
        Dimension dim = getPreferredSize();
        dim.setSize(400, 600);
        setPreferredSize(dim);
    }


    //MODIFIES: this
    //EFFECTS: Set up Borders of this Panel
    private void setUpBorder() {
        Border innerBorder = BorderFactory.createTitledBorder("Entry of the Day");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    //MODIFIES: this
    //EFFECTS: set up the position of components in this Panel using GridBagConstraints Layout Manager
    private void setUpLayout() {
        setLayout(new GridBagLayout());

        /////FIRST ROW//////
        gc.weightx = 20;
        gc.anchor = GridBagConstraints.LINE_END;
        setComponentPosition(goalLabel,0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        setComponentPosition(goalField,1,0,0);

        //SECOND ROW
        gc.anchor = GridBagConstraints.LINE_END;
        setComponentPosition(journalLabel,0,1,5);
        gc.anchor = GridBagConstraints.LINE_START;
        setComponentPosition(journalField, 1,1,0);

        ///THIRD ROW////
        setComponentPosition(submitButton,1,2,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weighty = 1;
    }

    //EFFECTS: sets up first row in the Panel in this format
    //        - goal: textfield
    private void setComponentPosition(JComponent component, int x, int y, int insetRight) {
        gc.insets = new Insets(0, 0, 0, insetRight); //some space on the right
        gc.gridx = x;
        gc.gridy = y;
        add(component, gc);
    }


    //MODIFIES: this
    //EFFECTS: adds an ActionListener to submitButton and fires an entryEvent to entryListener
    // entry event carries data inputted by the User in the TextFields
    //        - entryListener then deals with the entryEvent as an anonymous class in the main Frame
    private void setSubmitButton() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goal = goalField.getText();
                String journal = journalField.getText();  //pass this info to main Frame
                EntryEvent entryEvent = new EntryEvent(this, goal, journal);
                if (entryListener != null) {
                    entryListener.formSubmitted(entryEvent);
                }
            }
        });
    }

    void setEntryListener(EntryListener entryListener) {
        this.entryListener = entryListener;
    }
}
