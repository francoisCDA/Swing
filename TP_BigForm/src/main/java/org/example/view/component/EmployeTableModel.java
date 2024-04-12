package org.example.view.component;

import javax.swing.table.DefaultTableModel;

public class EmployeTableModel extends DefaultTableModel {

    private String[] header = {"EmployeeID","Name","Gender","Age","BloodGroup","ContactNo","Qualification","DOJ","Address","photoPath"};


    public EmployeTableModel(String[][] employeeList) {
        setColumnIdentifiers(header);
        updListEmployee(employeeList);

    }

    public void updListEmployee(String[][] employeeList ) {

        setRowCount(0);
        for (int i = 0; i < employeeList.length; i++) {
            addRow(employeeList[i]);
        }

    }

}
