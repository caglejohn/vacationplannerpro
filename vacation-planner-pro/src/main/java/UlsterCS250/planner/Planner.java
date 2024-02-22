package UlsterCS250.planner;
import java.util.*;
public class Planner {
    private final ArrayList<HalfDay> calendar;
    private int bufferUnits;
    private final TimeZone timeZone;
    private int year;
    private int currentIndex;
    private final GregorianCalendar gregorianCalendar;
    public Planner(TimeZone timeZone){
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
        if(calendar.get(startInd).getStart()[3]!=0) throw new RuntimeException("startInd must point to a AM HalfDay");
        System.out.println("starting at: "+calendar.get(startInd));
        int ind=2*(calendar.get(startInd).getId().getCalId()-1);
        startInd-=ind;
        System.out.println("start ind: "+ind);
        while(ind+startInd<=endInd&&ind+startInd<calendar.size()){
            if(ind<0){
                ind++;
                continue;
            }
            setWorkDay(ind+startInd,pattern[ind%pattern.length]);
            ind++;
        }
    }
    private void generateCalendar(){
        GregorianCalendar c = (GregorianCalendar) gregorianCalendar.clone();
        c.set(c.get(Calendar.YEAR),Calendar.JANUARY,1,0,0,0);
        year=c.get(Calendar.YEAR);
        int ind=0;
        while(c.get(Calendar.YEAR)==year){
            HalfDay day = new HalfDay(c,ind++);
            day.saveDate(c,false);
            c.add(Calendar.HOUR_OF_DAY,12);
            day.saveDate(c,true);
            calendar.add(day);
        }
    }
    private void update(){
        currentIndex=(gregorianCalendar.get(Calendar.DAY_OF_YEAR)-1)*2;
        if(gregorianCalendar.get(Calendar.AM_PM)==Calendar.PM) currentIndex++;
    }
    public void setBufferUnits(int bufferUnits) {
        this.bufferUnits = bufferUnits;
    }
    public String toString(){
        return "index: "+ currentIndex +" year: "+year+" current HalfDay: "+calendar.get(currentIndex)+" "+
                calendar.stream().map(HalfDay::toString).reduce("",(String s, String x)->s+'\n'+x);
    }
}
