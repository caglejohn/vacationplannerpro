package UlsterCS250.requests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeOffRequest {
    private String day;
    private String time; // "AM" or "PM"
    private String reason;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalDate getFormattedDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(day, formatter);
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getIsAm() {
        return time.equals("AM") ? true : false;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
