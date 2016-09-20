package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

/**
 * Created by yao on 15/09/16.
 */
public class ProgramSlot implements Cloneable, Serializable {

    private Time duration;
    private Date dateOfProgram;
    private Date startTime;
    private String programName;

    public ProgramSlot () {

    }

    public ProgramSlot (Time duration, Date dateOfProgram) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getDateOfProgram() {
        return dateOfProgram;
    }

    public void setDateOfProgram(Date dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setAll (Time duration, Date dateOfProgram, Date startTime, String programName) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.programName = programName;
    }

}
