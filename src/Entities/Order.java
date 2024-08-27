package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class Order {
    private int Id;
    private LocalDate date;
    private boolean paid;
    private Client client;
    private Doctor doctor;
    private Bill bill;
    public List<Analysis> listAnalysis = new ArrayList();
    public Order(){
        
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Order(int Id) {
        this.Id = Id;
    }
    
    
    public Order(int Id, LocalDate date){
        this.Id = Id;
        this.date = date;
    }

    public Order(int Id, LocalDate date, boolean paid, Client client, Doctor doctor, List<Analysis> list) {
        this.Id = Id;
        this.date = date;
        this.paid = paid;
        this.client = client;
        this.doctor = doctor;
        this.listAnalysis = list;
    }
    public Order(Order o) {
        this.Id = o.Id;
        this.date = o.date;
        this.paid = o.paid;
        this.client = o.client;
        this.doctor = o.doctor;
        this.bill = o.bill;
    }

    public List<Analysis> getListOrders() {
        return listAnalysis;
    }

    public void setListOrders(List<Analysis> listOrders) {
        this.listAnalysis = listOrders;
    }
    
    

    public int getId() {
        return Id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
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
        final Order other = (Order) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
    public String toString() {
        return Id + " " + date;
    }
//    public Analysis getAnalysisById(int Id){
//        //todo
//        return null;
//    }
//    
//    public List getAllAnalysis(){
//        //todo
//        return null;
//    }
}
