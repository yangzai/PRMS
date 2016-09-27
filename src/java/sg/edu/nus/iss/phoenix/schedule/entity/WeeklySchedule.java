package sg.edu.nus.iss.phoenix.schedule.entity;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;

import java.util.Date;

/**
 * Created by yao on 15/09/16.
 */
public class WeeklySchedule {
    private Date startDate;
    private User assignedBy;

    public WeeklySchedule(Date startDate, User assignedBy) {
        this.startDate = startDate;
        this.assignedBy = assignedBy;
    }

    public Date getStartDate() { return startDate; }

    public User getAssignedBy() {return assignedBy; }
}
