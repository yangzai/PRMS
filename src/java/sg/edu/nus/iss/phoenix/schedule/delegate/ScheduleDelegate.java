package sg.edu.nus.iss.phoenix.schedule.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Created by yangzai on 10/9/16.
 */
public class ScheduleDelegate {
    private ScheduleService scheduleService;

    public ScheduleDelegate() {
        scheduleService = new ScheduleService();
    }

    public List<AnnualSchedule> processRetrieveAllAnnualSchedule() {
        return scheduleService.processRetrieveAllAnnualSchedule();
    }

    public void processCreateAnnualWeeklySchedule(AnnualSchedule annualSchedule) {
        scheduleService.processCreateAnnualWeeklySchedule(annualSchedule);
    }

    /**
     * This method will create a ProgramSlot in the database catch any exception
     * @param ps the program slot to be created.
     * @exception IllegalArgumentException If there is any error when creating programSlot
     */
    public void processCreate(ProgramSlot ps) throws IllegalArgumentException {
        scheduleService.processCreate(ps);
    }

    public void processModify(ProgramSlot ps) throws IllegalArgumentException {
        scheduleService.processModify(ps);
    }

    public void processDelete(Time startTime, Date dateOfProgram) throws IllegalArgumentException{
        scheduleService.processDelete(startTime, dateOfProgram);
    }
}
