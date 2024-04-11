package org.example.service;

import org.example.dao.DepartementDAO;
import org.example.model.Departement;

import java.util.List;


public class DepartementService {

    private final DepartementDAO departementDAO = new DepartementDAO();

    public void save(Departement departement) {

        departementDAO.addDept(departement);

    }

    public List<Departement> getAllDept() {
        return departementDAO.getAllDepartement();
    }

    public String[] getDeptName() {

        List<Departement> deptList = departementDAO.getAllDepartement();
        String[] deptName = new String[deptList.size()];
        for (int i = 0; i < deptList.size(); i++) {
            deptName[i] = deptList.get(i).getName();
        }

        return deptName;
    }

    public Departement getDeptByName(String deptName) {

        List<Departement> deptList = getAllDept();
        for (int i = 0; i < deptList.size(); i++) {
            if (deptList.get(i).getName().equals(deptName)) {
                return deptList.get(i);
            }
        }
        return null;
    }

    public void remove(Departement dept) {

        departementDAO.rmDepartementById(dept.getId());

    }

    public void updDepartementName(Departement dept) {
        departementDAO.updateDepartement(dept);
    }
}
