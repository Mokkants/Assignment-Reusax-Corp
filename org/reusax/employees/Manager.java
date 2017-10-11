package org.reusax.employees;

import org.reusax.Degree;

public class Manager extends Employee {

    private Degree degree;

    public Manager(String ID, String name,double grossSalary, Degree degree) {
        super( ID, name, grossSalary);
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + getName() + '\'' +
                ", ID='" + getID() + '\'' +
                ", grossSalary=" + grossSalary +
                ", netSalary = " + getNetSalary() +
                "degree = "+degree+
                '}';
    }

    @Override
    public double getNetSalary() {

        double salary = calcGrossSalaryWithBonus();

        return salary - salary * getUniversalTaxRate();
    }

    @Override
    public double getGrossSalary() {
        return calcGrossSalaryWithBonus();
    }

    private double calcGrossSalaryWithBonus() {
        double salary = grossSalary;

        float bonus=0;

        switch (degree){
            case BSc:
                bonus = .1f;
                break;
            case MSc:
                bonus = .2f;
                break;
            case PhD:
                bonus = .35f;
                break;
        }

        salary *= (1+bonus);
        return salary;
    }



    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree){
        this.degree = degree;
    }

    public void setDegree(String degree) {

        switch (degree.toLowerCase()){
            case "bsc":
                this.degree = Degree.BSc;
                break;
            case "msc":
                this.degree = Degree.MSc;
                break;
            case "phd":
                this.degree = Degree.PhD;
                break;
        }
    }
}