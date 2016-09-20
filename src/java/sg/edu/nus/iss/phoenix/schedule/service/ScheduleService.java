package sg.edu.nus.iss.phoenix.schedule.service;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by yangzai on 10/9/16.
 */
public class ScheduleService {

    private Calendar cal;
    private AnnualScheduleDAO annualScheduleDAO;
    private WeeklyScheduleDAO weeklyScheduleDAO;
    private ScheduleDAO scheduleDAO;

    public ScheduleService() {
        //All weeks starts on Monday
        //1st week of the year starts from the 1st Monday.
        //Days that are before the 1st Monday
        //belong to the preceding annual schedule.
        cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(7);

        DAOFactory daoFactory = new DAOFactoryImpl();
        annualScheduleDAO = daoFactory.getAnnualScheduleDAO();
        weeklyScheduleDAO = daoFactory.getWeeklyScheduleDAO();
        scheduleDAO = daoFactory.getScheduleDAO();
    }

    public List<AnnualSchedule> processRetrieveAllAnnualSchedule() {
        try {
            return annualScheduleDAO.retrieveAllAnnualSchedules();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void processCreateAnnualWeeklySchedule(int year, User user) {
        if (year < 0)
            throw new IllegalArgumentException("Year cannot be negative.");

        try {
            //should have been designed to call from create
            if (annualScheduleDAO.checkAnnualScheduleExists(year))
                throw new IllegalArgumentException("Schedule already exist.");

            //create annual
            annualScheduleDAO.createAnnualSchedule(year, user);

            //create weekly
            cal.set(year, 1, 1);
            int noOfWeeks = cal.getWeeksInWeekYear();

            for (int i = 1; i <= noOfWeeks; i++) {
                cal.setWeekDate(year, i, Calendar.MONDAY);
                weeklyScheduleDAO.createWeeklySchedule(cal.getTime(), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void processCreate(ProgramSlot ps) {
        try {
            scheduleDAO.create(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processModify(ProgramSlot ps) {
        try {
            scheduleDAO.save(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void processDelete(Time duration, Date dateOfProgram) {
        try {
            ProgramSlot ps = new ProgramSlot(duration, dateOfProgram);
            scheduleDAO.delete(ps);
        }catch (NotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
