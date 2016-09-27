package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzai on 10/9/16.
 */
public class AnnualScheduleDAOImpl implements AnnualScheduleDAO {
    @Override
    public AnnualSchedule createValueObject() {
        return new AnnualSchedule();
    }

    @Override
    public List<AnnualSchedule> retrieveAllAnnualSchedules() throws SQLException {
        List<AnnualSchedule> annualScheduleList = new ArrayList<>();
        String sql = "SELECT * FROM `annual-schedule` LEFT JOIN user ON assingedBy = user.id";

        try (Connection connection = DBConstants.newConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                AnnualSchedule annualSchedule = createValueObject();
                annualSchedule.setYear(resultSet.getInt("year"));
                User assignedBy = new User();
                assignedBy.setAll(resultSet.getString("id"), resultSet.getString("password"),
                        resultSet.getString("name"), resultSet.getString("role"));
                annualSchedule.setAssignedBy(assignedBy);
                annualScheduleList.add(annualSchedule);
            }
        }

        return annualScheduleList;
    }

    @Override
    public boolean checkAnnualScheduleExists(AnnualSchedule annualSchedule) throws SQLException {

        String sql = "SELECT assingedBy FROM `annual-schedule` WHERE `year` = ?";
        try (Connection connection = DBConstants.newConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, annualSchedule.getYear());
            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.first();
            }
        }
    }

    @Override
    public void createAnnualSchedule(AnnualSchedule annualSchedule) throws SQLException {
        String sql = "INSERT INTO `annual-schedule` VALUES (?, ?)";
        try (Connection connection = DBConstants.newConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, annualSchedule.getYear());
            stmt.setString(2, annualSchedule.getAssignedBy().getId());
            if (stmt.executeUpdate() != 1)
                throw new SQLException("Insertion failed.");
        }
    }
}
