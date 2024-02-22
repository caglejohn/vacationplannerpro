package UlsterCS250.planner;
import java.util.TimeZone;

public class test {
    public static void main(String[] args){
        Planner p = new Planner(TimeZone.getTimeZone("EST"));
        boolean[] pattern=new boolean[]{false,false,true,true,true,true,true,true,true,true,true,true,false,false};
        p.setWeeklyWorkPattern(0,732,pattern);
        System.out.println(p);
    }
}
