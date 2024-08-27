package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
public class Doctor {
    private String Id;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
    private String title;
    
    public Doctor(){
        
    }
    public Doctor(String Id){
        this.Id = Id;
    }
    public Doctor(String Id, String firstName, String lastName, String phone, String location, String title) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
        this.title = title;
    }
    public Doctor( String firstName, String lastName,String phone, String location, String title) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
        this.title = title;
    }
    public Doctor(Doctor d){
        this.Id = d.Id;
        this.firstName = d.firstName;
        this.lastName = d.lastName;
        this.phone = d.phone;
        this.location = d.location;
        this.title = d.title;
    }

    public String getId() {
        return Id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doctor other = (Doctor) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
    public String toString() {
        return  title + " " + firstName + " " + lastName + "/" + phone;
    }
  
}
