package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yangzai on 12/9/16.
 */
public class WeeklyScheduleDAOImpl implements WeeklyScheduleDAO {
    @Override
    public void createWeeklySchedule(WeeklySchedule weeklySchedule) throws SQLException {
        Date startDate = weeklySchedule.getStartDate();
        User user = weeklySchedule.getAssignedBy();

        String sql = "INSERT INTO `weekly-schedule` VALUES (?, ?)";
        try (Connection connection = DBConstants.newConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setString(2, user.getId());
            if (stmt.executeUpdate() != 1)
                throw new SQLException("Insertion failed.");
        }
    }
}
