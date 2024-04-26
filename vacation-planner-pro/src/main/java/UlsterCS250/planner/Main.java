package UlsterCS250.planner;
import UlsterCS250.repository.HalfDayRepository;

public class Main {
    public static void main(String[] args){
        /*Calendar p = new Calendar(TimeZone.getTimeZone("EST"));
        boolean[] pattern=new boolean[]{false,false,true,true,true,true,true,true,true,true,true,true,false,false};
        p.setWeeklyWorkPattern(0,732,pattern);
        p.setWorkDay(133, false);
        System.out.println(p);
        ArrayList<JHalfDay> days = new ArrayList<>();
        for(HalfDay h : p.getCalendar()){
            days.add(h.convert());
            System.out.println(days.get(days.size()-1).getStartDate());
        }
        System.out.println(days);
        Date d = new Date(2024);*/
        HalfDayRepository h = new HalfDayRepository();
        h.fillCalendar();
    }
}
