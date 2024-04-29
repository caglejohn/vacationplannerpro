package UlsterCS250.planner;
import UlsterCS250.entities.JHalfDay;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

public class HalfDay {
    public static final String[] weekDays = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public static final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    private int id;
    private int index;
    private String name;
    private Date startDate;
    private Date endDate;
    private String startString;
    private String endString;
    private boolean workDay;

    public HalfDay(GregorianCalendar c, int index){
        id=c.get(Calendar.DAY_OF_WEEK);
        name=getDayString(id);
        this.index=index;
        workDay=true;
        startDate=null;
        endDate=null;
    }

    public JHalfDay convert() {
        return new JHalfDay(index+1, index%2==0, id, startDate, endDate, workDay);
    }

    public static String getDayString(int id){
        return weekDays[id-1];
    }
    public void saveDate(GregorianCalendar c, boolean isEnd){
        Date date = new java.sql.Date(c.getTime().getTime());
        if (isEnd) endDate = date;
        else startDate = date;
        String string=date.toString();
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
    public Date getStart() {
        return startDate;
    }
    public Date getEnd() {return endDate;}
}
