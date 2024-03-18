package UlsterCS250.planner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HalfDay {
    public static final String[] weekDays = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private int id;
    private int index;
    private String name;
    private int[] start = new int[4];
    private int[] end = new int[4];
    private String startString;
    private String endString;
    private boolean workDay;
    private ArrayList<Employee> takenOff;

    public HalfDay(GregorianCalendar c, int index){
        id=c.get(Calendar.DAY_OF_WEEK);
        name=getDayString(id);
        this.index=index;
        workDay=true;
    }
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
    public void setId(int id) {
        this.id = id;
    }
    public int[] getStart(){
        return start;
    }
    public void setStart(int[] start) {
        this.start = start;
    }
    public int[] getEnd() {
        return end;
    }
    public void setEnd(int[] end) {
        this.end = end;
    }
}
