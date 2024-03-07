package UlsterCS250.planner;
import java.util.TimeZone;

public class test {
    public static void main(String[] args){
        Planner p = new Planner(TimeZone.getTimeZone("EST"));
        boolean[] pattern=new boolean[]{true,true,true,true,true,true,true,false,true,true,false,false,true,true};
        p.setWeeklyWorkPattern(0,732,pattern);
        System.out.println(p);
        System.out.println(Employee.generateEmployees().size());
    }
}
