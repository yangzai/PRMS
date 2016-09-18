package sg.edu.nus.iss.phoenix.schedule.entity;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import java.sql.Time;
import java.util.Date;

/**
 * Created by yao on 15/09/16.
 */

public class ProgramSlot {

    //attribute
    private Date startTime;
    private Time duration;
    private RadioProgram radioProgram;
//    private Presenter presenter;//Is there an entity call this? or two????????????
//    private Producer producer;
    private User preOrPro;
    private User assignedBy;

    public ProgramSlot () {

    }
    public  ProgramSlot(Date startTime){
        this.startTime=startTime;
    }

    public Date getStartTime(){
        return this.startTime;
    }
    public void setStartTime(Date startTime){
        this.startTime=startTime;
    }

    public Time getDuration(){
        return this.duration;
    }
    public void setStarDuration(Time duration){
        this.duration=duration;
    }

    public RadioProgram getRadioProgram(){
        return this.radioProgram;
    }
    public void setRadioProgram(RadioProgram radioProgram){
        this.radioProgram=radioProgram;
    }

    public User getPreOrPro(){
        return this.preOrPro;
    }
    public void setPreOrPro(User preOrPro){
        this.preOrPro=preOrPro;
    }

    public User getAssignedBy(){
        return this.assignedBy;
    }
    public void setAssignedBy(User assignedBy){
        this.assignedBy=assignedBy;
    }

    public void setAll(Date startTime,
                       Time duration,RadioProgram radioProgram,User preOrPro,User assignedBy) {
        this.startTime = startTime;
        this.duration = duration;
        this.radioProgram = radioProgram;
        this.preOrPro=preOrPro;
        this.assignedBy=assignedBy;
    }

    public boolean hasEqualMapping(ProgramSlot valueObject) {

        if (this.startTime == null) {
            if (valueObject.getStartTime() != null)
                return(false);
        } else if (!this.startTime.equals(valueObject.getStartTime())) {
            return(false);
        }
        if (this.duration == null) {
            if (valueObject.getDuration() != null)
                return(false);
        } else if (!this.duration.equals(valueObject.getDuration())) {
            return(false);
        }
        if (this.radioProgram == null) {
            if (valueObject.getRadioProgram() != null)
                return(false);
        } else if (!this.radioProgram.equals(valueObject.getRadioProgram())) {
            return(false);
        }
        if (this.preOrPro == null) {
            if (valueObject.getPreOrPro() != null)
                return(false);
        } else if (!this.getPreOrPro().equals(valueObject.getPreOrPro())) {
            return(false);
        }

        return true;
    }

}
