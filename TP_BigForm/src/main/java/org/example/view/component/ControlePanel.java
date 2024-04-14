package org.example.view.component;

import org.example.controller.EmployeeController;
import org.example.model.Employee;
import org.example.util.HTMLUtil;
import org.example.util.PhotoUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ControlePanel extends JPanel {

    private Employee activeEmployee;
    private JTable employeeTable;

    private final EmployeeController employeeController;

    private JDialog modal;

    public ControlePanel(EmployeeController emplCntrl, Employee employee, JTable emplTble, Border border) {
        this.employeeController = emplCntrl;
        setLayout(new FlowLayout());

        Border mainPadding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border mainBorder = BorderFactory.createCompoundBorder(mainPadding, border);

        setBorder(mainBorder);

        activeEmployee = employee;
        this.employeeTable = emplTble;


        JLabel searchLabel = new JLabel("Search");
        JTextField searchField = new JTextField(10);
        JButton newButton = new JButton("New",PhotoUtil.getIcone("src/main/resources/icone/plus.png",22));
        JButton saveButton = new JButton("Save",PhotoUtil.getIcone("src/main/resources/icone/disquette.png",22));
        saveButton.setEnabled(false);
        JButton updateButton = new JButton("Update",PhotoUtil.getIcone("src/main/resources/icone/mise-a-jour.png",22));
        JButton deleteButton = new JButton("Delete",PhotoUtil.getIcone("src/main/resources/icone/supprimer.png",22));
        JButton clearButton = new JButton("Clear",PhotoUtil.getIcone("src/main/resources/icone/effacer.png",22));
        JButton printButton = new JButton("Print",PhotoUtil.getIcone("src/main/resources/icone/imprimante.png",22));


        printButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println(activeEmployee.toString());
                //JOptionPane.showMessageDialog(ControlePanel.this, "Check your console");
                if (activeEmployee.getId() != null) {
                    try {
                        File employeeHtml = HTMLUtil.mkEmployeeHtmlFile(employee.mkClone());
                        System.out.println("Employee html file successfully create at : " + employeeHtml.getAbsolutePath());

                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            Desktop.getDesktop().browse(employeeHtml.toURI());
                        }

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(modal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ControlePanel.this,"You must create or view an employee before you can print.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (newButton.getText().equals("New")) {
                    saveButton.setEnabled(true);
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    searchField.setEnabled(false);
                    newButton.setText("Cancel");
                    newButton.setIcon(PhotoUtil.getIcone("src/main/resources/icone/bouton-supprimer.png",22));
                    activeEmployee.setId(null);
                } else {
                    saveButton.setEnabled(false);
                    updateButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    searchField.setEnabled(true);
                    newButton.setText("New");
                    newButton.setIcon(PhotoUtil.getIcone("src/main/resources/icone/plus.png",22));
                    activeEmployee.reset();
                }

            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modal = new JDialog();

                try {

                    int employedID = employeeController.save(activeEmployee.mkClone());

                    if ( employedID != 0 ) {

                        String newPath = PhotoUtil.path(activeEmployee.getPhotoPath(), employedID);
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
                searchField.setEnabled(true);
                newButton.setText("New");
                newButton.setIcon(PhotoUtil.getIcone("src/main/resources/icone/plus.png",22));

                activeEmployee.reset();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String currentDirectory = System.getProperty("user.dir");

                    if (!activeEmployee.getPhotoPath().startsWith(currentDirectory)) {
                        String newPath = PhotoUtil.path(activeEmployee.getPhotoPath(), activeEmployee.getId());
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

                                PhotoUtil.rmEmployeePicture(activeEmployee.getPhotoPath());
                                HTMLUtil.rmHtmlEmployeeFile(activeEmployee.getId());

                                JOptionPane.showMessageDialog(ControlePanel.this, "Employee deleted!");

                            } else {
                                JOptionPane.showMessageDialog(ControlePanel.this, "An error occured! Check data");
                            }

                            EmployeTableModel tableModel = (EmployeTableModel) employeeTable.getModel();
                            tableModel.updListEmployee(employeeController.getEmployeeRows());

                        } catch (SQLException | RuntimeException ex) {
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
