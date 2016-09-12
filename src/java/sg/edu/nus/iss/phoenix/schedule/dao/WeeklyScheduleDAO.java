package sg.edu.nus.iss.phoenix.schedule.dao;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yangzai on 12/9/16.
 */
public interface WeeklyScheduleDAO {
    void createWeeklySchedule(Date startDate, User user) throws SQLException;
}
