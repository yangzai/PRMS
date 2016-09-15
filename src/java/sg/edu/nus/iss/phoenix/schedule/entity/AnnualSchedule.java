package sg.edu.nus.iss.phoenix.schedule.entity;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;

import java.io.Serializable;

/**
 * Created by yangzai on 9/9/16.
 */
public class AnnualSchedule implements Cloneable, Serializable {
    private int year;
    private User assignedBy;

    public AnnualSchedule() {}

    public AnnualSchedule(int year, User assignedBy) {
        setYear(year);
        this.assignedBy = assignedBy;
    }

    public int getYear() { return year; }

    public User getAssignedBy() { return assignedBy; }

    public void setYear(int year) {
        if (year < 0)
            throw new IllegalArgumentException("Year cannot be negative.");

        this.year = year;
    }

    public void setAssignedBy(User assignedBy) {
        this.assignedBy = assignedBy;
    }
}
