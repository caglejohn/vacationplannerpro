package UlsterCS250.planner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HalfDay {
    public enum WeekDay{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
        String getName(){
            switch (this){
                case SUNDAY -> {return "Sunday";}
                case MONDAY -> {return "Monday";}
                case TUESDAY -> {return "Tuesday";}
                case WEDNESDAY -> {return "Wednesday";}
                case THURSDAY -> {return "Thursday";}
                case FRIDAY -> {return "Friday";}
                default -> {return "Saturday";}
            }
        }
        int getCalId(){
            switch (this){
                case SUNDAY -> {return 1;}
                case MONDAY -> {return 2;}
                case TUESDAY -> {return 3;}
                case WEDNESDAY -> {return 4;}
                case THURSDAY -> {return 5;}
                case FRIDAY -> {return 6;}
                default -> {return 7;}
            }
        }
    };
    private final WeekDay id;
    private final int index;
    private final String name;
    private final int[] start = new int[4];
    private final int[] end = new int[4];
    private String startString;
    private String endString;
    private boolean workDay;

    public HalfDay(GregorianCalendar c, int index){
        id=getDay(c.get(Calendar.DAY_OF_WEEK));
        name=id.getName();
        this.index=index;
        workDay=true;
    }
    public WeekDay getDay(int calId){
        switch (calId){
            case 1 -> {return WeekDay.SUNDAY;}
            case 2 -> {return WeekDay.MONDAY;}
            case 3 -> {return WeekDay.TUESDAY;}
            case 4 -> {return WeekDay.WEDNESDAY;}
            case 5 -> {return WeekDay.THURSDAY;}
            case 6 -> {return WeekDay.FRIDAY;}
            default -> {return WeekDay.SATURDAY;}
        }
    }
    public void saveDate(GregorianCalendar c, boolean isEnd){
        int[] date = isEnd ? end : start;
        date[0]=c.get(Calendar.MONTH)+1;
        date[1]=c.get(Calendar.DAY_OF_MONTH);
        date[2]=c.get(Calendar.YEAR);
        date[3]=c.get(Calendar.HOUR_OF_DAY);
        String string=date[0]+"/"+date[1]+"/"+date[2]+" "+getDay(c.get(Calendar.DAY_OF_WEEK)).getName() + " 12:00 " + (c.get(Calendar.AM_PM)==Calendar.AM ? "AM" : "PM");
        if (isEnd) endString=string;
        else startString=string;
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
    public WeekDay getId() {
        return id;
    }
    @Override
    public String toString() {
        return startString + " to " + endString+" "+(workDay ? "WORK":"OFF");
    }
}
