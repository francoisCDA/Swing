package org.example.view;

import org.example.controller.EmployeeController;
import org.example.model.Employee;
import org.example.view.component.ControlePanel;
import org.example.view.component.EmployeTableModel;
import org.example.view.component.FormulairePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeFrame extends JFrame {

    private final EmployeeController employeeController = new EmployeeController();

    private final Employee activEmployee;

    private DefaultTableModel tableModel;
    private JTable employeeTable;

    public EmployeeFrame() {
        setTitle("Employee Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 800);
        setLocationRelativeTo(null);

        Border border = BorderFactory.createLineBorder(Color.black,2);

        activEmployee = new Employee();

        tableModel = new EmployeTableModel(employeeController.getEmployeeRows());


        employeeTable = new JTable(this.tableModel);

        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1) {

                    int rowidx  = employeeTable.convertRowIndexToModel(employeeTable.getSelectedRow());

                    String idStr = (String) employeeTable.getModel().getValueAt(rowidx,0);

                    Employee employeeSelected = employeeController.getEmployeeById(Integer.parseInt(idStr));

                    activEmployee.setEmployee(employeeSelected);
                }
            }
        });


        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new FormulairePanel(activEmployee,border), BorderLayout.NORTH);
        mainPanel.add(new ControlePanel(employeeController,activEmployee,employeeTable,border), BorderLayout.CENTER);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new EmployeeFrame().setVisible(true);;
//            }
//        });
//    }

}
