package org.example.view.component;

import org.example.controller.EmployeeController;
import org.example.model.Employee;
import org.example.model.Qualification;
import org.example.util.SavePhoto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControlePanel extends JPanel {

    private Employee activeEmployee;
    private JTable employeeTable;

    private final EmployeeController employeeController;

    private JDialog modal;

    public ControlePanel(EmployeeController employeeController, Employee employee, JTable employeeTable, Border border) {
        this.employeeController = employeeController;
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
                System.out.println(activeEmployee.toString());
            }

        });


        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(true);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                //clearButton.setEnabled(false);
                searchField.setEnabled(false);
                newButton.setEnabled(false);
                activeEmployee.reset();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modal = new JDialog();

                try {

                    int employedID = employeeController.save(activeEmployee.mkClone());

                    if ( employedID != 0 ) {

                        String newPath = SavePhoto.path(activeEmployee.getPhotoPath(), employedID);
                        activeEmployee.setPhotoPath(newPath);

                        if (employeeController.updatePath(employedID, newPath)) {

                            EmployeTableModel tableModel = (EmployeTableModel) employeeTable.getModel();
                            tableModel.updListEmployee(employeeController.getEmployeeRows());

                            JOptionPane.showMessageDialog(ControlePanel.this, "Employee created successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        };

                    } else {
                        JOptionPane.showMessageDialog(ControlePanel.this, "Invalide data");
                    }
                } catch (SQLException | RuntimeException | IOException ex) {
                    JOptionPane.showMessageDialog(modal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                saveButton.setEnabled(false);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                //clearButton.setEnabled(true);
                searchField.setEnabled(true);
                newButton.setEnabled(true);
                activeEmployee.reset();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String currentDirectory = System.getProperty("user.dir");

                    if (!activeEmployee.getPhotoPath().startsWith(currentDirectory)) {
                        String newPath = SavePhoto.path(activeEmployee.getPhotoPath(), activeEmployee.getId());
                        activeEmployee.setPhotoPath(newPath);
                    }


                    if (employeeController.update(activeEmployee.mkClone())) {

                        EmployeTableModel tableModel = (EmployeTableModel) employeeTable.getModel();
                        tableModel.updListEmployee(employeeController.getEmployeeRows());

                        JOptionPane.showMessageDialog(ControlePanel.this, "Employee updated!");
                    } else {
                        JOptionPane.showMessageDialog(ControlePanel.this, "Invalid data");
                    }

                } catch (SQLException |IOException ex) {
                    JOptionPane.showMessageDialog(modal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (activeEmployee.getId() != null) {
                    int confirm = JOptionPane.showConfirmDialog(modal, "Are you sure you want to delete" + employee.getName() + " ?");

                    if (confirm == JOptionPane.YES_OPTION) {
                       try {
                            if ( employeeController.removeEmployeeById(activeEmployee.getId()) ) {
                                JOptionPane.showMessageDialog(ControlePanel.this, "Employee deleted!");
                            } else {
                                JOptionPane.showMessageDialog(ControlePanel.this, "An error occured! Check data");
                            }

                            EmployeTableModel tableModel = (EmployeTableModel) employeeTable.getModel();
                            tableModel.updListEmployee(employeeController.getEmployeeRows());

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(modal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } finally {
                           activeEmployee.reset();
                       }

                    }
                } else {
                    JOptionPane.showMessageDialog(ControlePanel.this, "Choose an employee to delete");
                }



            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                employee.reset();
                searchField.setText("");
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchEmployee();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchEmployee();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchEmployee();
            }
            private void searchEmployee() {
                EmployeTableModel tableModel = (EmployeTableModel) employeeTable.getModel();

                if (searchField.getText().isEmpty()) {
                    tableModel.updListEmployee(employeeController.getEmployeeRows());

                } else {
                    try {
                        tableModel.updListEmployee(employeeController.searchEmployees(searchField.getText()));
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(modal, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

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
