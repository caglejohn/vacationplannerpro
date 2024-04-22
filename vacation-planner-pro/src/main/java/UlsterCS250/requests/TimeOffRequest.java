package UlsterCS250.requests;

import java.sql.Date;

public class TimeOffRequest {
    private Date day;
    private String time; // "AM" or "PM"
    private String reason;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
