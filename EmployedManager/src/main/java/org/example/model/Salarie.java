package org.example.model;

import lombok.Data;

@Data
public class Salarie {

    private int id;

    private String firstname;

    private String lastname;

    private Role role;

    private Departement departement;


    public int getDeparementId() {
        return departement.getId();
    }

    public String[] getRow() {
        return new String[]{Integer.toString(id),lastname,firstname,role.toString(),departement.getName()};
    }
}
