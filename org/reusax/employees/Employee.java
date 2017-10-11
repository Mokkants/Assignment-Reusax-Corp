package org.reusax.employees;

public class Employee implements Comparable<Employee>{

    private final String ID;

    private String name;
    protected double grossSalary;
    private double universalTaxRate = .1;

    public Employee(String ID, String name, double grossSalary) {
        this.ID = ID;
        this.name = name;
        this.grossSalary = grossSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", grossSalary=" + grossSalary +
                ", netSalary = " + getNetSalary() +
                '}';
    }

    public double getNetSalary(){
        return grossSalary - grossSalary*universalTaxRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    //grossSalary without any bonus shenanigans
    public final double getDefaultGrossSalary() {return grossSalary;}

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getID() {
        return ID;
    }

    public double getUniversalTaxRate() {
        return universalTaxRate;
    }

    public void setUniversalTaxRate(double universalTaxRate) {
        this.universalTaxRate = universalTaxRate;
    }

    @Override
    public int compareTo(Employee e) {

        return getName().compareTo(e.getName()) > 0 ? 1 : getName().compareTo(e.getName()) < 0 ? -1  : 0;

    }

}
