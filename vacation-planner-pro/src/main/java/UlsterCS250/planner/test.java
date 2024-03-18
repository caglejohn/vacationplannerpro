package UlsterCS250.planner;
import java.util.TimeZone;

public class test {
    public static void main(String[] args){
        Calendar p = new Calendar(TimeZone.getTimeZone("EST"));
        boolean[] pattern=new boolean[]{false,false,true,true,true,true,true,true,true,true,true,true,false,false};
        p.setWeeklyWorkPattern(8,732,pattern);
        p.setWorkDay(133, false);
        System.out.println(p);
    }
}
