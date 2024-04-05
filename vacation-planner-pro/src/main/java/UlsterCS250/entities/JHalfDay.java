package UlsterCS250.entities;

import java.util.ArrayList;

public class JHalfDay {
    public int index;
    public String startHour;
    public String endHour;
    public String startWeekDay;
    public String endWeekDay;
    public String startMonth;
    public String endMonth;
    public String startYear;
    public String endYear;
    public int[] start; // MM/DD/YYYY/HH
    public int[] end;   // MM/DD/YYYY/HH
    public boolean isWorkDay;
    public ArrayList<JEmployee> takenOff;

    public String getStartWeekDay() {
        return startWeekDay;
    }

    public void setStartWeekDay(String startWeekDay) {
        this.startWeekDay = startWeekDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public int[] getStart() {
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

    public boolean isWorkDay() {
        return isWorkDay;
    }

    public void setWorkDay(boolean workDay) {
        isWorkDay = workDay;
    }

    public ArrayList<JEmployee> getTakenOff() {
        return takenOff;
    }

    public void setTakenOff(ArrayList<JEmployee> takenOff) {
        this.takenOff = takenOff;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }
    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndWeekDay() {
        return endWeekDay;
    }

    public void setEndWeekDay(String endWeekDay) {
        this.endWeekDay = endWeekDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
}
