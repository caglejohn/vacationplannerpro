package UlsterCS250.entities;

import java.util.Date;

public class VacationRequest {
    
    private Long requestId;
    private Long employeeId;
    private Long approverId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private String type; 

    VacationRequest(){

    }

    VacationRequest(Long requestId, Long employeeId, Long approverId, Date startDate,
        Date endDate, String reason, String status, String type){
            this.requestId = requestId;
            this.employeeId = employeeId;
            this.approverId = approverId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.reason = reason;
            this.status = status;
            this.type = type;
       }

       public Long getRequestId(){
        return requestId;
       }

       public void setRequestId(Long requestId){
        this.requestId = requestId;
       }

       public Long getEmployeeId(){
        return employeeId;
       }

       public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
       }

       public Long getApproverId(){
        return approverId;
       }

       public void setApproverId(Long approverId){
        this.approverId = approverId;
       }

       public Date getStartDate(){
        return startDate;
       }

       public void setStartDate(Date startDate){
        this.startDate = startDate;
       }

       public Date getEndDate(){
        return endDate;
       }

       public void setEndDate(Date endDate){
        this.endDate = endDate;
       }

       public String getReason(){
            return reason;
       }

       public void setReason(String reason){
        this.reason = reason;
       }

       public String getStatus(){
        return status;
       }

       public void setStatus(String status){
        this.status = status;
       }

       public String getType(){
        return type;
       }

       public void setType(String type){
        this.type = type;
       }
}
