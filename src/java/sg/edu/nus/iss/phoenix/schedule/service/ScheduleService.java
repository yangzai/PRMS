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
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public void processCreateAnnualWeeklySchedule(AnnualSchedule annualSchedule) {
        int year = annualSchedule.getYear();
        User user = annualSchedule.getAssignedBy();

        if (year < 0)
            throw new IllegalArgumentException("Year cannot be negative.");

        try {
            if (annualScheduleDAO.checkAnnualScheduleExists(annualSchedule))
                throw new IllegalArgumentException("Schedule already exist.");

            //create annual
            annualScheduleDAO.createAnnualSchedule(annualSchedule);

            //create weekly
            cal.set(year, 1, 1);
            int noOfWeeks = cal.getWeeksInWeekYear();

            for (int i = 1; i <= noOfWeeks; i++) {
                cal.setWeekDate(year, i, Calendar.MONDAY);
                WeeklySchedule weeklySchedule = new WeeklySchedule(cal.getTime(), user);
                weeklyScheduleDAO.createWeeklySchedule(weeklySchedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will create a ProgramSlot in the database and catch any exception
     * @param ps the program slot to be created.
     * @exception IllegalArgumentException If there is any error when creating programSlot
     * @exception SQLException Error in SQL insert query
     */
    public boolean processCreate(ProgramSlot ps) throws IllegalArgumentException, SQLException, NotFoundException{
        if (!scheduleDAO.checkProgramSlotExists(ps)){
            scheduleDAO.create(ps);
            return true;
        }else {
            return false;
        }
    }

    public void processModify(ProgramSlot ps) {
        try {
            scheduleDAO.save(ps);
        } catch (SQLException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void processDelete(Time startTime, Date dateOfProgram) {
        try {
            ProgramSlot ps = new ProgramSlot(startTime, dateOfProgram);
            scheduleDAO.delete(ps);
        }catch (NotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
