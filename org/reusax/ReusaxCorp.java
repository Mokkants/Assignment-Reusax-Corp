package org.reusax;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.reusax.employees.*;

import java.io.InvalidObjectException;
import java.security.InvalidKeyException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mokkants on 2017-10-04.
 */
public class ReusaxCorp {

    public static double bonus = 10000;

    private Map<String, Employee> employees = new LinkedHashMap<>();

    public void registerEmployee(String ID, String name, double grossSalary) throws InvalidKeyException{

        if(employees.get(ID) != null){
            throw new InvalidKeyException("ID already registered");
        }

        employees.put(ID, new Employee(ID,name,grossSalary));

    }

    public void registerIntern(String ID, String name, double grossSalary, int GPA) throws InvalidKeyException {

        if(retrieveEmployee(ID) != null){
            throw new InvalidKeyException("ID already registered");
        }

        employees.put(ID, new Intern(ID, name, grossSalary,GPA));

    }

    public void registerManager(String ID, String name, double grossSalary,@NotNull Degree degree) throws InvalidKeyException {
        //TODO

        if(retrieveEmployee(ID) != null){
            throw new InvalidKeyException("ID already registered");
        }

        employees.put(ID, new Manager(ID, name ,grossSalary,degree));

    }


    public void registerDirector(String ID, String name, double grossSalary, @NotNull Degree degree,@NotNull Department department) throws InvalidKeyException {
        //TODO

        if(retrieveEmployee(ID) != null){
            throw new InvalidKeyException("ID already registered");
        }

        employees.put(ID, new Director(ID,name,grossSalary,degree,department));

    }

    public void removeEmployee(String ID){
        //TODO
        if (retrieveEmployee(ID) != null){
            employees.remove(ID);
        }
    }


    @Nullable
    public Employee retrieveEmployee(String ID){

        return  employees.get(ID);

    }

    public void updateEmployee(String ID,@NotNull EmployeeProperty field,@NotNull String newValue) throws Exception{

        Employee target = retrieveEmployee(ID);

        if(field == null || newValue == null) throw new InvalidArgumentException(new String[]{"Method arguments cannot be null!"});

        if(target != null){

            switch (field){

                case ID:
                    throw new IllegalArgumentException("Employee's ID is immutable");
                case NAME:
                    target.setName(newValue);
                    break;
                case GROSS_SALARY:
                    target.setGrossSalary(Double.parseDouble(newValue));
                    break;
                case DEGREE:
                    if(target instanceof Manager){
                    ((Manager) target).setDegree(newValue);
                    }
                    else throw new InvalidObjectException("Employee's degree is not being tracked at this rank.");

                    break;
                case DEPARTMENT:
                    if(target instanceof Director){
                    ((Director) target).setDepartment(newValue);
                    }
                    else throw new InvalidObjectException("Employee is not a director of a department.");

                    break;
                case GPA:
                    if(target instanceof Intern){
                    ((Intern) target).setGPA(Integer.parseInt(newValue));
                    }
                    else throw new InvalidObjectException("Employee is not an intern. GPA is not being tracked");


                    break;

            }

        }
        else throw new NullPointerException();
    }


    public int numberOfEmployees(){

        return employees.size();

    }

    public void promoteToIntern(String ID, int GPA){


        Employee target = retrieveEmployee(ID);
        removeEmployee(ID);
        if(target != null){

            employees.put(ID, new Intern(ID, target.getName(), target.getGrossSalary(), GPA));

        }

    }

    public void promoteToEmployee(String ID){

        Employee target = retrieveEmployee(ID);
        removeEmployee(ID);
        if(target != null){

            employees.put(ID, new Employee(ID, target.getName(), target.getGrossSalary()));

        }

    }

    public void promoteToManager(String ID, @NotNull Degree degree){

        Employee target = retrieveEmployee(ID);
        removeEmployee(ID);

        if(target != null){

                employees.put(ID, new Manager(ID, target.getName(), target.getGrossSalary(), degree));

        }
    }

    public void promoteToDirector(String ID,@NotNull Degree degree,@NotNull Department department){

        Employee target = retrieveEmployee(ID);
        removeEmployee(ID);
        if(target != null){

            employees.put(ID, new Director(ID,target.getName(),  target.getGrossSalary(), degree, department));

        }

    }

    public void sortEmployees(){
        sortBy("Name", "Ascending");
    }

    public void sortBy(String attribute){
        sortBy(attribute, "Ascending");
    }

    public void sortBy(String attribute, String order){
        //TODO

        boolean isDescending = order.toLowerCase().equals("descending");
        boolean isAscending = order.toLowerCase().equals("ascending") || !isDescending; //default to ascending in case of typo

        //turn map into array
        Employee[] employeeList = employees.values().toArray(new Employee[employees.size()]);


        //sort the array
        switch(attribute.toLowerCase()){
            case "name":
                employeeList=sortByName(employeeList);
                break;
            case "netsalary":
            case "net salary":
                employeeList = sortByNetSalary(employeeList);
                break;
        }


        //turn it back into a map

        if(isAscending){
            for (int j = 0; j < employeeList.length; j++) {

                employees.remove(employeeList[j].getID());
                employees.put(employeeList[j].getID(), employeeList[j]);

            }
        }
        else if(isDescending){
            for (int j = employeeList.length-1; j >= 0 ; j--) {

                employees.remove(employeeList[j].getID());
                employees.put(employeeList[j].getID(), employeeList[j]);

            }
        }


    }

    public void printEmployees(){

        Iterator it = employees.entrySet().iterator();




        //turn our map into an array
        while(it.hasNext()){

            Map.Entry par = (Map.Entry) it.next();
            System.out.println(employees.get(par.getKey()).toString());

        }

    }

    private Employee[] sortByName(Employee[] employeeList){

        int wrongPlace = 0;

        for (int i = 0; i < employeeList.length-1; i++) {

            int comparisonResult = employeeList[i].compareTo(employeeList[i+1]);
            if( comparisonResult == 1 || (comparisonResult == 0 && employeeList[i+1].getDefaultGrossSalary() < employeeList[i].getDefaultGrossSalary())){

                //the old switch-er-oo
                Employee temp = employeeList[i];
                employeeList[i] = employeeList[i+1];
                employeeList[i+1] = temp;

                wrongPlace++;

            }

        }

        if(wrongPlace>0){
            employeeList = sortByName(employeeList);
        }

        return employeeList;

    }

    private Employee[] sortByNetSalary(Employee[] employeeList){

        int wrongPlace = 0;

        for (int i = 0; i < employeeList.length-1; i++) {

            if( employeeList[i].getNetSalary() > employeeList[i+1].getNetSalary() ){

                //the old switch-er-oo
                Employee temp = employeeList[i];
                employeeList[i] = employeeList[i+1];
                employeeList[i+1] = temp;

                wrongPlace++;

            }

        }

        if(wrongPlace>0){
            employeeList = sortByNetSalary(employeeList);
        }

        return employeeList;

    }




    public static double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }



}
