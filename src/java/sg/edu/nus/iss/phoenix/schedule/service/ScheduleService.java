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
 * <p> <strong>ScheduleService</strong> will process all business logic related to create/copy/modify/delete Program
 * slot. </p>
 *
 * @author Ang Hao Yang, Chen Yao
 * @version 1.0 20 Sep 2016
 */
public class ScheduleService {

    private Calendar cal;
    private AnnualScheduleDAO annualScheduleDAO;
    private WeeklyScheduleDAO weeklyScheduleDAO;
    private ScheduleDAO scheduleDAO;

    /**
     * The constructor of Schedule Service.
     * Get all the relevant DAO and setting.
     */
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

    /**
     * This method will return list of all Annual Schedule object.
     * @return  List of all annual schedule in the system.
     */
    public List<AnnualSchedule> processRetrieveAllAnnualSchedule() {
        try {
            return annualScheduleDAO.retrieveAllAnnualSchedules();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * This method will create new Annual Schedule and 52 Weekly Schedule object for that year.
     * @param annualSchedule Annual Schedule object to be created in the system
     */
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
     */
    public void processCreate(ProgramSlot ps) throws IllegalArgumentException {
        boolean isProgramSlotExist;
        try {
            isProgramSlotExist = scheduleDAO.checkProgramSlotExists(ps);
        } catch (SQLException | NotFoundException e) {
            throw new IllegalArgumentException("Internal Server error");
        }
        if (isProgramSlotExist){
            throw new IllegalArgumentException("Error overlapping with existing program slot in the system");
        } else try {
            scheduleDAO.create(ps);
        }catch (SQLException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Error in database, pls check with administrator");
        }
    }

    /**
     * This method will modify an existing program slot
     * @param ps the program slot to be modified.
     * @exception IllegalArgumentException If there is any error when modifying programSlot
     */
    public void processModify(ProgramSlot ps) throws IllegalArgumentException {
        try {
            scheduleDAO.save(ps);
        } catch (SQLException | NotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error in modifying program slot");
        }
    }

    /**
     * This method will delete an existing program slot based on input startTime and dateOfProgram
     * @param startTime the start time of the deleted program slot
     * @param dateOfProgram the date of the program slot
     * @exception IllegalArgumentException If there is any error when modifying programSlot
     */
    public void processDelete(Time startTime, Date dateOfProgram) {
        try {
            ProgramSlot ps = new ProgramSlot(startTime, dateOfProgram);
            scheduleDAO.delete(ps);
        }catch (NotFoundException | SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error in deleting program slot");
        }
    }
}
