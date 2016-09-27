package sg.edu.nus.iss.phoenix.schedule.dao;

import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

import java.sql.SQLException;

/**
 * Created by yangzai on 12/9/16.
 */
public interface WeeklyScheduleDAO {
    void createWeeklySchedule(WeeklySchedule weeklySchedule) throws SQLException;
}
