package org.example.service;

import org.example.dao.DepartementDAO;
import org.example.dao.SalarieDAO;
import org.example.model.Salarie;

import java.util.List;

public class SalarieService {

    private final SalarieDAO salarieDAO ;

    public SalarieService() {
        this.salarieDAO = new SalarieDAO();
    }


    public List<Salarie> getAll() {
        return salarieDAO.getAllSalaries();
    }

    public String[][] getSalariesRows() {
        List<Salarie> salarieList = getAll();

        String[][] salariesRows = new String[salarieList.size()][5];

        for (int i = 0; i < salarieList.size(); i++) {
            salariesRows[i] = salarieList.get(i).getRow();
        }

        return salariesRows;

    }
}
