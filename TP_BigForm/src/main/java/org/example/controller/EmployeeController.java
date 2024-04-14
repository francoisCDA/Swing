package org.example.controller;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;
import org.example.util.PhotoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public int save(Employee employee) throws SQLException {

        if (!(employee.getId() == null
            && employee.getName() != null
            && employee.getAge() != null
            && employee.getAddress() != null
            && employee.getQualification() != null
            && employee.getBloodtype() != null
            && employee.getPhoneNumber() != null
            && employee.getStartDate() != null)
        ) {
            return 0;
        }

        return employeeDAO.addEmployee(employee);
    }

    public boolean update(Employee employee) throws SQLException {

        if (!(employee.getId() != null
                && employee.getName() != null
                && employee.getAge() != null
                && employee.getAddress() != null
                && employee.getQualification() != null
                && employee.getBloodtype() != null
                && employee.getPhoneNumber() != null
                && employee.getStartDate() != null)
        ) {
            return false;
        }

        String actualPath = employeeDAO.getPathById(employee.getId());

        if (actualPath != null ) {
            if (!actualPath.equals(employee.getPhotoPath())) {
                PhotoUtil.rmEmployeePicture(actualPath);
            }
        }

        if ( employeeDAO.updateEmployee(employee) == 1) {
            return true;
        } else {
            throw new SQLException("Error updating employee");
        }
   }


    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public boolean removeEmployeeById(Integer id) throws SQLException {
        if (employeeDAO.deleteEmployee(id) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePath(int employeeId, String path) throws SQLException {

        if(employeeDAO.updatePathEmployee(employeeId,path) == 1) {
            return true;
        } else {
            return false;
        }
    }


    public String[][] searchEmployees(String searchData) throws SQLException {

        List<Employee> employees = new ArrayList<Employee>();

        try {
            int employeeId = Integer.parseInt(searchData);
            employees = employeeDAO.getEmployeesIdLike(employeeId);
        } catch (NumberFormatException e) {
            employees = employeeDAO.getEmployeesNameLike(searchData);
        }

        String[][] employeeRows = new String[employees.size()][10];

        for (int i = 0; i < employees.size(); i++) {
            employeeRows[i] = employees.get(i).getRow();
        }
        return employeeRows;
    }
}
