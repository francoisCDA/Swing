package org.cda.model;

import lombok.Data;

@Data
public class Contact {

    private int id;
    private String name, number;


    public String[] getRow() {
        return new String[]{Integer.toString(id), name, number};
    }

}
