package org.example.controller;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;

import java.util.List;

public class EmployeeController {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();


    public String[][] getEmployeeRows() {
        List<Employee> employees = employeeDAO.getAllEmployees();

        String[][] employeeRows = new String[employees.size()][10];

        for (int i = 0; i < employees.size(); i++) {
            employeeRows[i] = employees.get(i).getRow();
        }

        return employeeRows;

    }
}
