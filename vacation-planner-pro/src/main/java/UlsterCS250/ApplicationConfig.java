package UlsterCS250;

import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.repository.HalfDayRepository;
import UlsterCS250.rest.EmployeeResource;
import UlsterCS250.rest.HalfDayResource;

public class ApplicationConfig {
    private static EmployeeRepository employeeRepository;
    private static EmployeeResource employeeResource;
    private static HalfDayRepository halfDayRepository;
    private static HalfDayResource halfDayResource;

    public static HalfDayRepository getHalfDayRepository() {
        if (halfDayRepository == null) {
            halfDayRepository = new HalfDayRepository();
        }
        return halfDayRepository;
    }
    public static HalfDayResource getHalfDayResource(){
        if(halfDayResource==null){
            halfDayResource = new HalfDayResource(getHalfDayRepository());
        }
        return halfDayResource;
    }
    public static EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            employeeRepository = new EmployeeRepository();
        }
        return employeeRepository;
    }

    public static EmployeeResource getEmployeeResource() {
        if (employeeResource == null) {
            employeeResource = new EmployeeResource(getEmployeeRepository());
        }
        return employeeResource;
    }
}