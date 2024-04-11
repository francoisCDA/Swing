package org.example.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee  {

    private int id;

    private String name;

    private int age;

    private boolean isFemale;

    private String address;

    private Qualification qualification;

    private String bloodtype;

    private String phoneNumber;

    private LocalDate startDate;

    private String photoPath;

    public String[] getRow() {
        return new String[]{Integer.toString(id),name,isFemale ? "Female" : "Male",Integer.toString(age),bloodtype,phoneNumber, qualification.name(),address,startDate.toString(),photoPath};
    }


}
