package org.example.view;

import org.example.model.Employee;
import org.example.view.component.ControlePanel;
import org.example.view.component.EmployeTableModel;
import org.example.view.component.FormulairePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeFrame extends JFrame {

    private Employee activEmployee;

    private DefaultTableModel tableModel;
    private JTable employeeTable;

    public EmployeeFrame() {
        setTitle("Employee Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        tableModel = new EmployeTableModel();
        employeeTable = new JTable(this.tableModel);

        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new FormulairePanel(), BorderLayout.NORTH);
        mainPanel.add(new ControlePanel(), BorderLayout.CENTER);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        add(mainPanel);


    }

    private void updEmployeeList() {
        
        String[][] employees


    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeFrame().setVisible(true);;
            }
        });
    }

}
