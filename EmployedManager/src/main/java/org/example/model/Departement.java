package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Departement {

    private int id;
    private String name;
    private List<Salarie> salaries;

    @Override
    public String toString() {
        return "Departement info : " +
                "id = " + id +
                ", name = " + name +
                ", nombre de salariés =" + salaries.size() ;
    }
}

