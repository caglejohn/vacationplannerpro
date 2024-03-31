package UlsterCS250.entities;

public class VacationProfile {
    private Long id;
    private Long personalChoiceDays;
    private Long vacationDaysTaken;
    private Long vacationDaysRemaining;
    private Long personalChoiceTaken;
    private Long personalChoiceDaysRemaining;

    public VacationProfile(){

    }

    public VacationProfile(Long id, Long personalChoiceDays, 
            Long vacationDaysTaken, Long vacationDaysRemaining, Long personalChoiceTaken,
            Long personalChoiceDaysRemaining){
                this.id = id;
                this.personalChoiceDays = personalChoiceDays;
                this.vacationDaysTaken = vacationDaysTaken;
                this.vacationDaysRemaining = vacationDaysRemaining;
                this.personalChoiceTaken = personalChoiceTaken;
                this.personalChoiceDaysRemaining = personalChoiceDaysRemaining;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public Long getPersonalChoiceDays(){
        return personalChoiceDays;
    }

    public void setPersonalChoiceDays(Long personalChoiceDays){
        this.personalChoiceDays = personalChoiceDays;
    }

    public Long getVacationDaysTaken(){
        return vacationDaysTaken;
    }

    public void setVacationDaysTaken(Long vacationDaysTaken){
        this.vacationDaysTaken = vacationDaysTaken;
    }

    public Long getVacationDaysRemaining(){
        return vacationDaysRemaining;
    }

    public void setVacationDaysRemaining(Long vacationDaysRemaining){
        this.vacationDaysRemaining = vacationDaysRemaining;
    }

    public Long getPersonalChoiceTaken(){
        return personalChoiceTaken;
    }

    public void setPersonalChoiceTaken(Long personalChoiceTaken){
        this.personalChoiceTaken = personalChoiceTaken;
    } 

    public Long getPersonalChoiceDaysRemaining(){
        return personalChoiceDaysRemaining;
    }

    public void setPersonalChoiceDaysRemaining(Long personalChoiceDaysRemaining){
        this.personalChoiceDaysRemaining = personalChoiceDaysRemaining;
    }

}

