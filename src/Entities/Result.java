/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Alaa Shafshak
 */
public class Result {
    double results;
    double particip;
    Analysis a;
    Order o;

    public Result(double results, float particip, Analysis a, Order o) {
        this.results = results;
        this.particip = particip;
        this.a = a;
        this.o = o;
    }

    public Result(Analysis a) {
        this.a = a;
    }

    public Result(double particip) {
        this.particip = particip;
    }

    public double getResults() {
        return results;
    }

    public double getParticip() {
        return particip;
    }

    public Analysis getA() {
        return a;
    }

    public Order getO() {
        return o;
    }

    public void setParticip(double particip) {
        this.particip = particip;
    }

    public void setA(Analysis a) {
        this.a = a;
    }

    public void setO(Order o) {
        this.o = o;
    }
    
    
}
