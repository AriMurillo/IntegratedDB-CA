/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

/**
 *
 * @author Ari
 */
public class Taxes {
    double income;
    double taxesOwed;

    public Taxes(double income, double taxesOwed) {
        this.income = income;
        this.taxesOwed = taxesOwed;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getTaxesOwed() {
        return taxesOwed;
    }

    public void setTaxesOwed(double taxesOwed) {
        this.taxesOwed = taxesOwed;
    }
    
    
}
