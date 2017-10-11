package org.reusax.employees;

public class Intern extends Employee {

    private int GPA;


    public Intern(String ID, String name, double grossSalary, int GPA) {
        super(ID, name, grossSalary);
        this.GPA = GPA;
    }


    @Override
    public String toString() {
        return "Intern{" +
                "name='" + getName() + '\'' +
                ", ID='" + getID() + '\'' +
                ", grossSalary=" + grossSalary +
                ", netSalary = " + getNetSalary() +
                "GPA=" + GPA +
                '}';
    }

    @Override
    public double getNetSalary(){

        double NetSalary;
        if (GPA <= 5){
            NetSalary = 0;
        }else if (GPA > 8){
            NetSalary = super.getGrossSalary()+1000;
        }else {
            NetSalary = super.getGrossSalary();
        }
        return NetSalary;
    }

    public int getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
    }
}