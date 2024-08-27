package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author User
 */
public class Bill {
    private int Id;
    private LocalDate date;
    private Order order; 
    
    public Bill(){
        
    }
    
    public Bill(Bill b){
        this.Id = b.Id;
        this.date = b.date;
    }
    public Bill(int Id, LocalDate date, Order order) {
        this.Id = Id;
        this.date = date;
        this.order = order;
    }

    public int getId() {
        return Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Order getOrder() {
        return order;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        final Bill other = (Bill) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
    public String toString() {
        return Id + " " +date;
    }
}
