package org.reusax;

/**
 * Created by mokkants on 2017-10-04.
 */
public class Main {

    public static void main(String[] args){


        ReusaxCorp reusaxCorp = new ReusaxCorp();

        try {
            reusaxCorp.registerEmployee("1", "d", 11);
            reusaxCorp.registerEmployee("2", "e", 12);
            reusaxCorp.registerDirector("6", "c", 15, Degree.BSc, Department.BUSINESS);
            reusaxCorp.registerIntern("4", "a", 12,7);
            reusaxCorp.registerManager("5", "b", 12, Degree.BSc);

            reusaxCorp.printEmployees();

            System.out.println("...");
            reusaxCorp.promoteToIntern("6", 7);
            reusaxCorp.promoteToManager("2", Degree.BSc);
            reusaxCorp.promoteToDirector("5", Degree.BSc , Department.TECHNICAL);
            reusaxCorp.promoteToEmployee("4");

            reusaxCorp.printEmployees();

            System.out.println("...");
            System.out.println(reusaxCorp.numberOfEmployees());


            System.out.println("...");

            reusaxCorp.updateEmployee("1", EmployeeProperty.DEPARTMENT, "technical");
            reusaxCorp.printEmployees();
        } catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

}
