package org.example.view.component;

import javax.swing.*;
import java.awt.*;

public class ControlePanel extends JPanel {

    public ControlePanel() {
        setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Search");
        JTextField searchField = new JTextField(10);
        JButton newButton = new JButton("Search");
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");
        JButton printButton = new JButton("Print");

        add(searchLabel);
        add(searchField);
        add(newButton);
        add(saveButton);
        add(updateButton);
        add(deleteButton);
        add(clearButton);
        add(printButton);

        setVisible(true);
    }
}
