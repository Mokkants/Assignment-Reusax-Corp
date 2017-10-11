package org.reusax.employees;

import org.reusax.Degree;
import org.reusax.Department;
import org.reusax.ReusaxCorp;

import java.io.Serializable;

public class Director extends Manager implements Serializable {

    private Department department;



    public Director( String ID, String name, double grossSalary, Degree degree, Department department) {
        super( ID, name, grossSalary, degree);
        this.department = department;
    }

    @Override
    public double getNetSalary() {

        double BaseSalary = getGrossSalary();
        double NetSalary;
        double SmallTaxes = 0.9;
        double MediumTaxes = 0.8;
        double LargeTaxes = 0.6;
        double FirstTaxLine = 30000;
        double SecondTaxLine = 50000;
        double preRichAmount = SecondTaxLine*MediumTaxes;

        if (BaseSalary < FirstTaxLine){
            NetSalary = BaseSalary*SmallTaxes;
        }else if (BaseSalary < SecondTaxLine){
            NetSalary = BaseSalary*MediumTaxes;
        }else {
            BaseSalary = (BaseSalary-SecondTaxLine)*LargeTaxes;
            NetSalary = BaseSalary+preRichAmount;
        }

        return NetSalary;
    }

    @Override
    public double getGrossSalary() {
        return super.getGrossSalary() + ReusaxCorp.getBonus();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + getName() + '\'' +
                ", ID='" + getID() + '\'' +
                ", grossSalary=" + grossSalary +
                ", netSalary = " + getNetSalary() +
                "degree = "+getDegree()+
                "department =" +department +
                '}';
    }

    public void setDepartment(String department) {

        switch (department.toLowerCase()){
            case "human resources":
            case "humanresources":
                this.department = Department.HUMAN_RESOURCES;
                break;
            case "technical":
                this.department = Department.TECHNICAL;
                break;
            case "business":
                this.department = Department.BUSINESS;
                break;
                default:
                    System.out.println("No such department!");
        }

    }
}