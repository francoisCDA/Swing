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
}
