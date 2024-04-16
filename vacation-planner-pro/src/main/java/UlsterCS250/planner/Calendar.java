package UlsterCS250.planner;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;
public class Calendar {
    private ArrayList<HalfDay> calendar;
    private int bufferUnits;
    private final TimeZone timeZone;
    private int year;
    private int currentIndex;
    private final GregorianCalendar gregorianCalendar;
    public Calendar(TimeZone timeZone){
        this.timeZone=timeZone;
        gregorianCalendar=new GregorianCalendar(timeZone);
        bufferUnits=0;
        calendar=new ArrayList<>();
        generateCalendar();
        update();
    }
    public boolean canTakeOff(int index){
        update();
        return index>=0&&index<calendar.size()&&index>currentIndex+bufferUnits&&calendar.get(index).isWorkDay();
    }
    public void setWorkDay(int ind, boolean isWorkDay){
        try {
            calendar.get(ind).setWorkDay(isWorkDay);
        }catch (IndexOutOfBoundsException e){
            System.err.println("day at index: "+ind+" is out of range in this calender of size: "+calendar.size());
            e.printStackTrace();
        }
    }
    public void setWeeklyWorkPattern(int startInd, int endInd, boolean[] pattern){
        if(pattern.length%14!=0) throw new RuntimeException("pattern length error");
        if(calendar.get(startInd).getStart()[3]!=0) startInd--;
        System.out.println("starting at: "+calendar.get(startInd));
        int ind=2*(calendar.get(startInd).getId()-1); //find index of where we are in pattern (encase year does not start on a Monday)
        while(startInd<=endInd&&startInd<calendar.size()){
            setWorkDay(startInd,pattern[ind%pattern.length]);
            ind++;
            startInd++;
        }
    }
    private void generateCalendar(){
        GregorianCalendar c = (GregorianCalendar) gregorianCalendar.clone();
        c.set(c.get(java.util.Calendar.YEAR), java.util.Calendar.JANUARY,1,0,0,0);
        year=c.get(java.util.Calendar.YEAR);
        int ind=0;
        while(c.get(java.util.Calendar.YEAR)==year){
            HalfDay day = new HalfDay(c,ind++);
            day.saveDate(c,false);
            c.add(java.util.Calendar.HOUR_OF_DAY,12);
            day.saveDate(c,true);
            calendar.add(day);
        }
    }
    private void update(){
        currentIndex=(gregorianCalendar.get(java.util.Calendar.DAY_OF_YEAR)-1)*2;
        if(gregorianCalendar.get(java.util.Calendar.AM_PM) == java.util.Calendar.PM) currentIndex++;
    }
    public String toString(){
        return "index: "+ currentIndex +" year: "+year+" current HalfDay: "+calendar.get(currentIndex)+" "+
                calendar.stream().map(HalfDay::toString).reduce("",(String s, String x)->s+'\n'+x);
    }
    public ArrayList<HalfDay> getCalendar() {
        return calendar;
    }
    public void setCalendar(ArrayList<HalfDay> calendar) {
        this.calendar = calendar;
    }
    public int getBufferUnits() {
        return bufferUnits;
    }
    public void setBufferUnits(int bufferUnits) {
        this.bufferUnits = bufferUnits;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
