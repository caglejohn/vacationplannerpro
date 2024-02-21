package UlsterCS250.planner;

import java.util.ArrayList;

public class Employee {
    public static final String[] names = new String[]{"CathrineStafford"};
    public static final String[] domains = new String[]{"@gmail.com"};
    private ArrayList<VacationInterval> timeOff;

    private String name;
    private String email;
    private int companyID;
    public Employee(String name, String email, int companyID) {
        this.name=name;
        this.email=email;
        this.companyID=companyID;
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
    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
