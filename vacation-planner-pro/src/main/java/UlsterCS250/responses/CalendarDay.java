package UlsterCS250.responses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import UlsterCS250.entities.JHalfDay;

public class CalendarDay {
    private int id;
    private final List<JHalfDay> halfDays;
    private Date day;

    public CalendarDay() {
        this.halfDays = new ArrayList<>();
    }

    public CalendarDay(int id, Date day, List<JHalfDay> halfDays) {
        this.id = id;
        this.day = day;
        this.halfDays = new ArrayList<>(halfDays);
    }

    public int getId() {
        return this.id;
    }

    public Date getDay() {
        return this.day;
    }

    public List<JHalfDay> getHalfDays() {
        return Collections.unmodifiableList(halfDays);
    }

    public void addHalfDay(JHalfDay halfDay) {
        this.halfDays.add(halfDay);
    }
}