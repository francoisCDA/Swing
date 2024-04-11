package org.example.view.component;

import javax.swing.table.DefaultTableModel;

public class EmployeTableModel extends DefaultTableModel {

    String[] header = {"EmployeeID","Name","Gender","Age","BloodGroup","ContactNo","Qualification","DOJ","Address","photoPath"};

    public EmployeTableModel() {
        setColumnIdentifiers(header);
        setRowCount(0);



    }

}
