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
public class Analysis {
    private int Id;
    private String name;
    private String unit;
    private String value;
    private float defaultPrice;
    
    public Analysis(String name){
        this.name = name;
    }
    private float result;

    public Analysis(int Id, String name, String unit, String value, float defaultPrice) {
        this.Id = Id;
        this.name = name;
        this.unit = unit;
        this.value = value;
        this.defaultPrice = defaultPrice;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    

    
    
    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
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
        final Analysis other = (Analysis) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return  name;
    }
    
}
