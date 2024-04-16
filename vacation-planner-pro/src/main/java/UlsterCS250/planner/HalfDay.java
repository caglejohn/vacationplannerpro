package UlsterCS250.planner;
import UlsterCS250.entities.JEmployee;
import UlsterCS250.entities.JHalfDay;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HalfDay {
    public static final String[] weekDays = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public static final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    private int id;
    private int index;
    private String name;
    private int[] start = new int[4];
    private int[] end = new int[4];
    private String startString;
    private String endString;
    private boolean workDay;

    public HalfDay(GregorianCalendar c, int index){
        id=c.get(Calendar.DAY_OF_WEEK);
        name=getDayString(id);
        this.index=index;
        workDay=true;
    }

    /*public static JHalfDay convert(int index, int dayOfWeek, Date startDate, Date endDate, boolean workDay, boolean isAm, ArrayList<JEmployee> takenOff) {
        JHalfDay halfDay = new JHalfDay();
        halfDay.setIndex(index);
        halfDay.setWorkDay(workDay);
        String[] start = startDate.toString().split("-");
        String[] end = endDate.toString().split("-");
        halfDay.setStart(new int[] {Integer.parseInt(start[1]),Integer.parseInt(start[2]),Integer.parseInt(start[0]), isAm ? 0 : 12});
        halfDay.setEnd(new int[] {Integer.parseInt(end[1]),Integer.parseInt(end[2]),Integer.parseInt(end[0]), isAm ? 12 : 24});
        halfDay.setStartHour(isAm ? "12:00 AM" : "12:00 PM");
        halfDay.setEndHour(isAm ? "12:00 PM" : "12:00 AM");
        halfDay.setStartWeekDay(weekDays[dayOfWeek]);
        halfDay.setEndWeekDay(isAm ? weekDays[dayOfWeek] : weekDays[(dayOfWeek+1)%7]);
        halfDay.setStartMonth(months[halfDay.getStart()[0]-1]);
        halfDay.setEndMonth(months[halfDay.getEnd()[0]-1]);
        halfDay.setStartYear(start[0]);
        halfDay.setEndYear(end[0]);
        halfDay.setTakenOff(takenOff);
        return halfDay;
    }*/

    public static String getDayString(int id){
        return weekDays[id-1];
    }
    public void saveDate(GregorianCalendar c, boolean isEnd){
        int[] date = isEnd ? end : start;
        date[0]=c.get(Calendar.MONTH)+1;
        date[1]=c.get(Calendar.DAY_OF_MONTH);
        date[2]=c.get(Calendar.YEAR);
        date[3]=c.get(Calendar.HOUR_OF_DAY);
        String string=date[0]+"/"+date[1]+"/"+date[2]+" "+getDayString(c.get(Calendar.DAY_OF_WEEK)) + " 12:00 " + (c.get(Calendar.AM_PM)==Calendar.AM ? "AM" : "PM");
        if (isEnd) endString=string;
        else startString=string;
    }
    @Override
    public String toString() {
        return startString + " to " + endString+" "+(workDay ? "WORK":"OFF");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isWorkDay() {
        return workDay;
    }
    public void setWorkDay(boolean workDay) {
        this.workDay = workDay;
    }
    public int getIndex(){
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getId(){
        return id;
    }
    public int[] getStart() {
        return start;
    }
}
