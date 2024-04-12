package org.example.model;

import lombok.Data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Employee  {

  //  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  //  private List<PropertyChangeListener> listeners = new ArrayList<>();

    private HashMap<String,PropertyChangeListener> listeners = new HashMap<>();

    private Integer id;

    private String name;

    private Integer age;

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


    public void reset() {

        id = null;
        name = null;
        age = null;
        isFemale = false;
        address = null;
        qualification = null;
        bloodtype = null;
        phoneNumber = null;
        startDate = null;
        photoPath = null;

    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        listeners.put(propertyName, listener);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNameNoEvent(String name) {
        this.name = name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;

        PropertyChangeEvent event = new PropertyChangeEvent(this, "name", oldName, name);
        listeners.get("name").propertyChange(event);

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isFemale=" + isFemale +
                ", address='" + address + '\'' +
                ", qualification=" + qualification +
                ", bloodtype='" + bloodtype + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startDate=" + startDate +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    //    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
//        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
//    }
}
