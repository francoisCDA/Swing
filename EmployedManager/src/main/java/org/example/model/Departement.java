package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Departement {

    private int id;
    private String name;
    private List<Salarie> salaries;
    private int nbSalaries;

    @Override
    public String toString() {

        if (salaries != null) {
            nbSalaries = salaries.size();
        }

        return "Departement info : " +
                "id = " + id +
                ", name = " + name +
                ", nombre de salariés =" + nbSalaries ;
    }
}

