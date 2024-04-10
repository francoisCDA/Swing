package org.example.service;

import org.example.dao.DepartementDAO;
import org.example.model.Departement;


public class DepartementService {

    private final DepartementDAO departementDAO = new DepartementDAO();

    public void save(Departement departement) {

        departementDAO.addDept(departement);

    }
}
