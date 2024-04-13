package org.example.dao;

import org.example.model.Employee;
import org.example.model.Qualification;
import org.example.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection con;

    private PreparedStatement ps;

    private ResultSet rs;

    public int addEmployee(Employee emp) throws SQLException {
        con = ConnectDB.getConnection();
        int ret = 0;

        try {
            ps = con.prepareStatement("INSERT INTO employee ( name , age, is_female, address, qualification, bloodtype, phone, start_date, photoPath) VALUES (?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            prepareEmployee(emp);

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return ret;
    }


    public List<Employee> getAllEmployees() {
        con = ConnectDB.getConnection();
        List<Employee> employees = new ArrayList<Employee>();

        try {
            rs = con.createStatement().executeQuery("SELECT id, name , age, is_female, address, qualification, bloodtype, phone, start_date, photoPath FROM employee ");

            while (rs.next()) {
                Employee emp = new Employee();
                createEmploye(emp);

                employees.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        con = ConnectDB.getConnection();
        Employee emp = null;

        try {
            ps = con.prepareStatement("SELECT id, name , age, is_female, address, qualification, bloodtype, phone, start_date, photoPath FROM employee WHERE id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee();
                createEmploye(emp);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emp;
    }

    public int updateEmployee(Employee emp) throws SQLException {
        con = ConnectDB.getConnection();

            ps = con.prepareStatement("UPDATE employee SET name = ? , age = ? , is_female = ? , address = ? , qualification = ? , bloodtype = ?, phone = ?, start_date = ?, photoPath = ?  WHERE id = ? ");
            prepareEmployee(emp);
            ps.setInt(10, emp.getId());

            return ps.executeUpdate();
    }

    public int deleteEmployee(int id) throws SQLException {
        con = ConnectDB.getConnection();

        try {
            ps = con.prepareStatement("DELETE FROM employee WHERE id = ?");
            ps.setInt(1, id);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void prepareEmployee(Employee emp) throws SQLException {

        ps.setString(1, emp.getName());
        ps.setInt(2, emp.getAge());
        ps.setBoolean(3, emp.isFemale());
        ps.setString(4, emp.getAddress());
        ps.setString(5, emp.getQualification().toString());
        ps.setString(6, emp.getBloodtype());
        ps.setString(7, emp.getPhoneNumber());
        ps.setString(8, emp.getStartDate().toString());
        ps.setString(9, emp.getPhotoPath());
    }


    private void createEmploye(Employee emp) throws SQLException {
        emp.setId(rs.getInt("id"));
        emp.setName(rs.getString("name"));
        emp.setAge(rs.getInt("age"));
        emp.setFemale(rs.getBoolean("is_female"));
        emp.setAddress(rs.getString("address"));
        emp.setQualification(Qualification.valueOf(rs.getString("qualification")));
        emp.setBloodtype(rs.getString("bloodtype"));
        emp.setPhoneNumber(rs.getString("phone"));
        emp.setStartDate(LocalDate.parse(rs.getString("start_date")));
        emp.setPhotoPath(rs.getString("photoPath"));
    }

    public int updatePathEmployee(int employeeId, String path) throws SQLException {

        con = ConnectDB.getConnection();

        ps = con.prepareStatement("UPDATE employee SET photoPath = ?  WHERE id = ? ");
        ps.setString(1,path);
        ps.setInt(2, employeeId);

        return ps.executeUpdate();
    }

    public List<Employee> getEmployeesIdLike(int employeeId) throws SQLException {
        con = ConnectDB.getConnection();
        List<Employee> ret = new ArrayList<>();

        ps = con.prepareStatement("SELECT id, name , age, is_female, address, qualification, bloodtype, phone, start_date, photoPath FROM employee WHERE id LIKE ? ");
        ps.setInt(1, employeeId);

        rs = ps.executeQuery();

        while (rs.next()) {
            Employee emp = new Employee();
            createEmploye(emp);
            ret.add(emp);
        }
        return ret;
    }

    public List<Employee> getEmployeesNameLike(String searchData) throws SQLException {
        con = ConnectDB.getConnection();
        List<Employee> ret = new ArrayList<>();

        ps = con.prepareStatement("SELECT id, name , age, is_female, address, qualification, bloodtype, phone, start_date, photoPath FROM employee WHERE name LIKE ? ");
        ps.setString(1, "%"+searchData+"%");

        rs = ps.executeQuery();

        while (rs.next()) {
            Employee emp = new Employee();
            createEmploye(emp);
            ret.add(emp);
        }
        return ret;
    }
}
