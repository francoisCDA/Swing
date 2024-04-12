package org.example.view.component;

import org.example.model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlePanel extends JPanel {

    private Employee activeEmployee;
    private JTable employeeTable;



    public ControlePanel(Employee employee, JTable employeeTable, Border border) {
        setLayout(new FlowLayout());
        setBorder(border);

        activeEmployee = employee;
        this.employeeTable = employeeTable;

        JLabel searchLabel = new JLabel("Search");
        JTextField searchField = new JTextField(10);
        JButton newButton = new JButton("New");
        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        JButton printButton = new JButton("Print/Test");
        printButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeEmployee.setId(100);
                activeEmployee.setName("Test Name");
            }

        });


        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(true);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                clearButton.setEnabled(false);
                searchField.setEnabled(false);
                newButton.setEnabled(false);
                activeEmployee.reset();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(activeEmployee.toString());

                saveButton.setEnabled(false);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                clearButton.setEnabled(true);
                searchField.setEnabled(true);
                newButton.setEnabled(true);
                activeEmployee.reset();


            }
        });






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
