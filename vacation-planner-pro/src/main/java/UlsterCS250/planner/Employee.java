package UlsterCS250.planner;

import java.util.ArrayList;

public class Employee {
    public static final String[] names = new String[]{"Cathrine","Oscar","Adam","John","Cheyenne","Kyle","Lou","Stafford","Zandonella","Marsh","Cagle","Demskie","Sullivan","Thomason"};
    public static final String[] emails = new String[]{"@gmail.com","@yahoo.com","@AOL.com"};
    private ArrayList<VacationInterval> timeOff;
    private int vacationTime;
    private String name;
    private String email;
    private final int companyID;
    public Employee(String name, String email, int companyID) {
        this.name=name;
        this.email=email;
        this.companyID=companyID;
        vacationTime=0;
    }
    public static ArrayList<Employee> generateEmployees(){
        ArrayList<Employee> ret = new ArrayList<>();
        generate(ret,"",0,0);
        return ret;
    }
    private static void generate(ArrayList<Employee> employees, String email, int ind, int count){
        if(count >= 2){
            String name= email;
            int companyID = (int) (Math.random()*10000);
            employees.add(new Employee(name,email+emails[(int)(Math.random()*emails.length)],companyID));
            return;
        }
        if(ind>= names.length) return;
        generate(employees,email+names[ind], ind+1, count+1);
        generate(employees,email, ind+1, count);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getVacationTime() {
        return vacationTime;
    }
    public void setVacationTime(int vacationTime) {
        this.vacationTime = vacationTime;
    }
    public String toString(){
        return email;
    }
}
