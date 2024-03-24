package UlsterCS250;

import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.rest.EmployeeResource;

public class ApplicationConfig {
    private static EmployeeRepository employeeRepository;
    private static EmployeeResource employeeResource;

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