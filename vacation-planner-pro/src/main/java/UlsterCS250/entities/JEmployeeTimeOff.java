package UlsterCS250.entities;


public class JEmployeeTimeOff {
    private int id;
    private int employeeId;
    private int halfDayId;
    private String reason;

    public JEmployeeTimeOff(int id, int employeeId, int halfDayId, String reason) {
        this.id = id;
        this.employeeId = employeeId;
        this.halfDayId = halfDayId;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getHalfDayId() {
        return halfDayId;
    }

    public void setHalfDayId(int halfDayId) {
        this.halfDayId = halfDayId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
