package sg.edu.nus.iss.phoenix.schedule.entity;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import java.sql.Time;
import java.sql.Date;

/**
 * Created by yao on 15/09/16.
 */

public class ProgramSlot {

    //attribute
    private Date dateOfProgram;
    private Time duration;
    private RadioProgram radioProgram;
    private Presenter presenter;
    private Producer producer;
    private User assignedBy;

    public ProgramSlot () {

    }
    public ProgramSlot(Time duration, Date dateOfProgram){
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
    }

    public Date getDateOfProgram(){
        return this.dateOfProgram;
    }
    public void setDateOfProgram(Date startTime){
        this.dateOfProgram=startTime;
    }

    public Time getDuration(){
        return this.duration;
    }
    public void setDuration(Time duration){
        this.duration=duration;
    }

    public RadioProgram getRadioProgram(){
        return this.radioProgram;
    }
    public void setRadioProgram(RadioProgram radioProgram){
        this.radioProgram=radioProgram;
    }

    public Presenter getPresenter(){
        return this.presenter;
    }
    public void setPresenter(Presenter presenter){
        this.presenter=presenter;
    }

    public Producer getProducer(){
        return this.producer;
    }
    public void setProducer(Producer producer){
        this.producer=producer;
    }

    public User getAssignedBy(){
        return this.assignedBy;
    }
    public void setAssignedBy(User assignedBy){
        this.assignedBy=assignedBy;
    }

    public void setAll(Date startTime,
                       Time duration,RadioProgram radioProgram,Presenter presenter,Producer producer,User assignedBy) {
        this.dateOfProgram = startTime;
        this.duration = duration;
        this.radioProgram = radioProgram;
        this.presenter=presenter;
        this.producer=producer;
        this.assignedBy=assignedBy;
    }

    public boolean hasEqualMapping(ProgramSlot valueObject) {

        if (this.dateOfProgram == null) {
            if (valueObject.getDateOfProgram() != null)
                return(false);
        } else if (!this.dateOfProgram.equals(valueObject.getDateOfProgram())) {
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
        if (this.presenter == null) {
            if (valueObject.getPresenter() != null)
                return(false);
        } else if (!this.getPresenter().equals(valueObject.getPresenter())) {
            return(false);
        }
        if (this.producer == null) {
            if (valueObject.getProducer() != null)
                return(false);
        } else if (!this.getProducer().equals(valueObject.getProducer())) {
            return(false);
        }

        return true;
    }

}
