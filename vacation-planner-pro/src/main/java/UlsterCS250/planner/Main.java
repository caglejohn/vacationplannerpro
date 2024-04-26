package UlsterCS250.planner;

import UlsterCS250.entities.JEmployee;
import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.repository.EmployeeTimeOffRepository;
import UlsterCS250.repository.HalfDayRepository;

public class Main {
    public static void main(String[] args){
        try {
            HalfDayRepository h = new HalfDayRepository();
            h.fillCalendar();
            EmployeeRepository e = new EmployeeRepository();
            e.addEmployee(new JEmployee(1,"johndoe", "hashed_password1", "johndoe@example.com", "John", "Doe", false, true, null, null, 5));
            e.addEmployee(new JEmployee(2,"janedoe", "hashed_password2", "janedoe@example.com", "Jane", "Doe", true, true, null, null, 8));
            EmployeeTimeOffRepository o = new EmployeeTimeOffRepository();
            o.addDayOff(new JEmployeeTimeOff(1,1,1,"sick"));
            o.addDayOff(new JEmployeeTimeOff(2,2,3,"dentist"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
