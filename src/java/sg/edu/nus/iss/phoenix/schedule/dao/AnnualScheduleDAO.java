package sg.edu.nus.iss.phoenix.schedule.dao;

import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangzai on 10/9/16.
 */
public interface AnnualScheduleDAO {
    AnnualSchedule createValueObject();
    List<AnnualSchedule> retrieveAllAnnualSchedules() throws SQLException;

    boolean checkAnnualScheduleExists(AnnualSchedule annualSchedule) throws SQLException;

    void createAnnualSchedule(AnnualSchedule annualSchedule) throws SQLException;
}
