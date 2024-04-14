package org.example.model;


import org.example.util.PhotoUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.HashMap;


public class Employee  {


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

        setId(null);
        setName(null);
        setAge(null);
        setFemale(false);
        setAddress(null);
        setQualification(Qualification.BEPC);
        setPhoneNumber(null);
        setPhotoPath(null);
        setStartDate(null);
        setPhotoPath(null);
        setBloodtype(null);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        listeners.put(propertyName, listener);
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        if (listeners.containsKey("id")) {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "id", oldId, id);
            listeners.get("id").propertyChange(event);
        }
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;

        if(listeners.containsKey("name")) {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "name", oldName, name);
            listeners.get("name").propertyChange(event);
        }

    }

    public void setAge(Integer age) {
        Integer oldAge = this.age;
        this.age = age;
        if ( listeners.containsKey("age")){
            PropertyChangeEvent event = new PropertyChangeEvent(this, "age", oldAge, age);
            listeners.get("age").propertyChange(event);
        }
    }

    public void setFemale(boolean female) {
        boolean oldFemale = this.isFemale;
        this.isFemale = female;
        if (listeners.containsKey("isFemale")){
            PropertyChangeEvent event = new PropertyChangeEvent(this, "isFemale", oldFemale, female);
            listeners.get("isFemale").propertyChange(event);
        }
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        if (listeners.containsKey("address")){
            PropertyChangeEvent event = new PropertyChangeEvent(this, "address", oldAddress, address);
            listeners.get("address").propertyChange(event);
        }

    }

    public void setQualification(Qualification qualification) {
         Qualification oldQualification = this.qualification;
         this.qualification = qualification;
         if (listeners.containsKey("qualification")){
             PropertyChangeEvent event = new PropertyChangeEvent(this, "qualification", oldQualification, qualification);
             listeners.get("qualification").propertyChange(event);
         }

    }

    public void setBloodtype(String bloodtype) {
         String oldBloodtype = this.bloodtype;
         this.bloodtype = bloodtype;
         if (listeners.containsKey("bloodType")){
             PropertyChangeEvent event = new PropertyChangeEvent(this, "bloodType", oldBloodtype, bloodtype);
             listeners.get("bloodType").propertyChange(event);
         }
    }

    public void setPhoneNumber(String phoneNumber) {
        String oldPhoneNumber = this.phoneNumber;
        this.phoneNumber = phoneNumber;
        if (listeners.containsKey("phoneNumber")){
            PropertyChangeEvent event = new PropertyChangeEvent(this, "phoneNumber", oldPhoneNumber, phoneNumber);
            listeners.get("phoneNumber").propertyChange(event);
        }
    }

    public void setStartDate(LocalDate startDate) {
         LocalDate oldStartDate = this.startDate;
         this.startDate = startDate;
         if (listeners.containsKey("startDate")){
             PropertyChangeEvent event = new PropertyChangeEvent(this, "startDate", oldStartDate, startDate);
             listeners.get("startDate").propertyChange(event);
         }
    }

    public void setPhotoPath(String photoPath) {

         String oldPhotoPath = this.photoPath;
         this.photoPath = photoPath;

         if (listeners.containsKey("photoPath")){
             PropertyChangeEvent event = new PropertyChangeEvent(this, "photoPath", oldPhotoPath, photoPath);
             listeners.get("photoPath").propertyChange(event);
         }

    }

    @Override
    public String toString() {
        return "\nEmployee's informations " +
                "\n\t id : " + id +
                ",\n\t name : " + name +
                ",\n\t age : " + age +
                ",\n\t gender : " + (isFemale ? "Female" : "Male") +
                ",\n\t address : \n" + address +
                ",\n\t qualification : " + qualification +
                ",\n\t blood type : " + bloodtype +
                ",\n\t phone number : " + phoneNumber +
                ",\n\t start date : " + startDate +
                ",\n\t profil photo path : " + photoPath +
                ".\n";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public String getAddress() {
        return address;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    // NO EVENT SETTERS

    public void setIdNoEvent(Integer id) {
        this.id = id;
    }

    public void setNameNoEvent(String name) {
        this.name = name;
    }

    public void setAgeNoEvent(String age) {
        try {
            int newAge = Integer.parseInt(age);
            this.age = newAge;
        } catch (NumberFormatException e) {
        }
    }

    public void setIsFemaleNoEvent(Boolean isFemale) {
        this.isFemale = isFemale;
    }

    public void setAddressNoEvent(String address) {
        this.address = address;
    }

    public void setQualificationNoEvent(Qualification qualification) {
        setQualification(qualification);
    }

    public void setPhoneNoEvent(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBloodtypeNoEvent(String bloodType) {
        this.bloodtype = bloodType;
    }

    public void setStartDateNoEvent(LocalDate localDate) {
        this.startDate = localDate;
    }

    private void setPhotoPathNoEvent(String photoPath) {

        this.photoPath = photoPath;
    }


    public Employee mkClone() {
        Employee clone = new Employee();

        clone.setIdNoEvent(id);
        clone.setNameNoEvent(name);
        clone.setAgeNoEvent(age.toString());
        clone.setIsFemaleNoEvent(isFemale);
        clone.setAddressNoEvent(address);
        clone.setQualificationNoEvent(qualification);
        clone.setBloodtypeNoEvent(bloodtype);
        clone.setPhoneNoEvent(phoneNumber);
        clone.setStartDateNoEvent(startDate);
        clone.setPhotoPathNoEvent(photoPath);

        return clone;
    }

    public void setEmployee(Employee employeeSelected) {

        setId(employeeSelected.getId());
        setName(employeeSelected.getName());
        setAge(employeeSelected.getAge());
        setFemale(employeeSelected.isFemale());
        setAddress(employeeSelected.getAddress());
        setQualification(employeeSelected.getQualification());
        setPhoneNumber(employeeSelected.getPhoneNumber());
        setPhotoPath(employeeSelected.getPhotoPath());
        setStartDate(employeeSelected.getStartDate());
        setPhotoPath(employeeSelected.getPhotoPath());
        setBloodtype(employeeSelected.getBloodtype());

    }

}
