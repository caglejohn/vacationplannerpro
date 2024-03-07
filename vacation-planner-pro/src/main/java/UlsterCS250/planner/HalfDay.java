package UlsterCS250.planner;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HalfDay {
    public static final String[] weekDays = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private final int id;
    private final int index;
    private final String name;
    private final int[] start = new int[4];
    private final int[] end = new int[4];
    private String startString;
    private String endString;
    private boolean workDay;

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
    public int getId(){
        return id;
    }
    public int[] getStart(){
        return start;
    }
    public boolean isWorkDay() {
        return workDay;
    }
    public void setWorkDay(boolean workDay) {
        this.workDay = workDay;
    }
    @Override
    public String toString() {
        return startString + " to " + endString+" "+(workDay ? "WORK":"OFF");
    }
}
