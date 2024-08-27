package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author User
 */
public class Client {
    
    private int Id;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
  //  private List<Order> listOrders = new ArrayList();
    
    public Client(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Client(int Id, String firstName, String lastName, String phone, String location) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
    }
    public Client( String firstName, String lastName, String phone, String location) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
    }
    public Client(){
        
    }
    public Client(int Id){
        this.Id=Id;
    }
    public Client(Client c){
        this.Id = c.Id;
        this.firstName = c.firstName;
        this.lastName = c.lastName;
        this.phone = c.phone;
        this.location = c.location;
    }

    public int getId() {
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
        final Client other = (Client) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
    public String toString() {
        return Id + " " +firstName + " " + lastName +" " + phone+" " +location;
    }
     
  
    
    
//    public Order getOrderById(int Id){
//        //todo
//        return null;
//    }
//    
//    public List getAllOrders(){
//        //todo
//        return null;
//    }
//    
    
    
}
