package UlsterCS250.entities;

import java.util.Date;

public class EmployeeTimeOff {
    private Long id;
    private Long employeeId;
    private Date date;
    private boolean isAm;
    private boolean isPm;
    private boolean isPersonal;

    public EmployeeTimeOff(){

    }

    public EmployeeTimeOff(Long id, long employeeId, Date date, boolean isAm, boolean isPm,
        boolean isPersonal){
            this.id = id;
            this.employeeId = employeeId;
            this.date = date;
            this.isAm = isAm;
            this.isPm = isPm;
            this.isPersonal = isPersonal;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getEmployeeId(){
        return employeeId;
    }

    public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public boolean getIsAm(){
        return isAm;
    }

    public void setISAm(boolean isAm){
        this.isAm = isAm;
    }

    public boolean getIsPm(){
        return isPm;
    }

    public void setIsPm(boolean isPm){
        this.isPm = isPm;
    }

    public boolean getIsPersonal(){
        return isPersonal;
    }

    public void setIsPersonal(boolean isPersonal){
        this.isPersonal = isPersonal;
    }

    
}
