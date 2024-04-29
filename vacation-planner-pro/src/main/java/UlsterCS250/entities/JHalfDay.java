package UlsterCS250.entities;

import java.util.ArrayList;
import java.sql.Date;

public class JHalfDay {
    private int id;
    private boolean isAm;
    private int dayOfWeekId;
    private Date startDate;
    private Date endDate;
    private boolean isWorkDay;
    private ArrayList<JEmployeeTimeOff> employeeTimeOffs;

    public JHalfDay(int id, boolean isAm, int dayOfWeekId, Date startDate, Date endDate, boolean isWorkDay) {
        this.id = id;
        this.isAm = isAm;
        this.dayOfWeekId = dayOfWeekId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isWorkDay = isWorkDay;
        this.employeeTimeOffs = new ArrayList<>();
    }

    public JHalfDay(int id, boolean isAm, int dayOfWeekId, Date startDate, Date endDate, boolean isWorkDay, ArrayList<JEmployeeTimeOff> employeeTimeOffs) {
        this.id = id;
        this.isAm = isAm;
        this.dayOfWeekId = dayOfWeekId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isWorkDay = isWorkDay;
        this.employeeTimeOffs = new ArrayList<>(employeeTimeOffs);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAm() {
        return isAm;
    }

    public void setAm(boolean am) {
        isAm = am;
    }

    public int getDayOfWeekId() {
        return dayOfWeekId;
    }

    public void setDayOfWeekId(int dayOfWeekId) {
        this.dayOfWeekId = dayOfWeekId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isWorkDay() {
        return isWorkDay;
    }

    public void setWorkDay(boolean workDay) {
        isWorkDay = workDay;
    }

    public ArrayList<JEmployeeTimeOff> getEmployeeTimeOffs() {
        return employeeTimeOffs;
    }

    private void setEmployeeTimeOffs(ArrayList<JEmployeeTimeOff> timeOff) {
        this.employeeTimeOffs = new ArrayList<>(timeOff);
    }
}
