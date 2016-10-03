package sg.edu.nus.iss.phoenix.schedule.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzai on 16/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AnnualScheduleDAO annualScheduleDAO;

    @Mock
    private WeeklyScheduleDAO weeklyScheduleDAO;
    @Mock
    private ScheduleDAO scheduleDAO;
    @InjectMocks
    private ScheduleService scheduleService;

    private List<AnnualSchedule> annualScheduleList;
//    private List<WeeklySchedule> weeklyScheduleList;
    private java.sql.Date date;
    private Time startTime;
    private RadioProgram rp;
    private Producer prod;
    private Presenter pres;
    private ProgramSlot ps;

    @Before
    public void setUp() throws SQLException {
        annualScheduleList = new ArrayList<>();
//        weeklyScheduleList = new ArrayList<>();
        User user = new User();
        user.setAll("aa", "password", "name", "manager");
        annualScheduleList.add(new AnnualSchedule(1999, user));

        //setup mocked DAO
        when(annualScheduleDAO.retrieveAllAnnualSchedules()).thenReturn(annualScheduleList);
        when(annualScheduleDAO.checkAnnualScheduleExists(any(AnnualSchedule.class))).thenAnswer(i -> {
            Object[] args = i.getArguments();
            AnnualSchedule annualSchedule = (AnnualSchedule) args[0];
            int y = annualSchedule.getYear();

            return annualScheduleList.stream().anyMatch(as -> as.getYear() == y);
        });
        doAnswer(i -> {
            Object[] args = i.getArguments();
            AnnualSchedule as = (AnnualSchedule) args[0];
            annualScheduleList.add(as);

            return null;
        }).when(annualScheduleDAO).createAnnualSchedule(any(AnnualSchedule.class));

        //setup programe slot input
        Calendar now = Calendar.getInstance();
        //duration = new Time(3600);
        startTime =new Time(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),now.get(Calendar.SECOND));
        date = new java.sql.Date(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        rp = new RadioProgram("test");
        prod = new Producer();
        pres = new Presenter();
    }

    @Test
    public void processRetrieveAllAnnualSchedule() throws Exception {
        List<AnnualSchedule> retrievedList = scheduleService.processRetrieveAllAnnualSchedule();

        assertThat(retrievedList, equalTo(annualScheduleList));
    }

    @Test
    public void processCreateAnnualWeeklySchedule() throws Exception {
        User user = new User();
        user.setAll("bb", "password2", "name2", "manager");
        int year = 2000;
        scheduleService.processCreateAnnualWeeklySchedule(new AnnualSchedule(year, user));

        AnnualSchedule createdAnnualSchedule = annualScheduleList.get(annualScheduleList.size() - 1);
        assertThat(createdAnnualSchedule.getYear(), equalTo(year));
        assertThat(createdAnnualSchedule.getAssignedBy(), is(user));

        verify(weeklyScheduleDAO, times(52)).createWeeklySchedule(any(WeeklySchedule.class));

        //TODO: add more verification and assertions after weekly schedule DTO is implemented
    }

    @Test
    public void processCreateAnnualWeeklyScheduleDuplicateYear() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        User user = new User();
        user.setAll("cc", "password3", "name3", "manager");
        int year = 1999;
        scheduleService.processCreateAnnualWeeklySchedule(new AnnualSchedule(year, user));
    }

    @Test
    public void processCreateAnnualWeeklyScheduleNegativeYear() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        User user = new User();
        user.setAll("dd", "password4", "name4", "manager");
        int year = -1234;
        scheduleService.processCreateAnnualWeeklySchedule(new AnnualSchedule(year, user));
    }

    @Test
    public void processCreateTest() throws Exception {
        ProgramSlot ps = new ProgramSlot(startTime,date);
        ps.setAll(date,new Time(3600),startTime, rp, pres, prod);
        scheduleService.processCreate(ps);

        verify(scheduleDAO).create(ps);
        verifyNoMoreInteractions(scheduleDAO);

    }

    @Test
    public void processModifyTest() throws Exception {
        ProgramSlot ps = new ProgramSlot(startTime,date);
        ps.setAll(date,new Time(3600),startTime, rp, pres, prod);
        scheduleService.processModify(ps);

        verify(scheduleDAO).save(ps);
        verifyNoMoreInteractions(scheduleDAO);

    }

    @Test
    public void processDeleteTest() throws Exception {
        scheduleService.processDelete(startTime, date);
        ProgramSlot ps = new ProgramSlot(startTime,date);

        ArgumentCaptor<ProgramSlot> psCaptor = ArgumentCaptor.forClass(ProgramSlot.class);
        verify(scheduleDAO).delete(psCaptor.capture());
        ProgramSlot updatedps = psCaptor.getValue();

        verify(scheduleDAO).delete(updatedps);
        assertThat(updatedps.getDateOfProgram(), is(date));
        assertThat(updatedps.getStartTime(), is(startTime));
        assertThat(false, is(ps.equals(updatedps)));

        verifyNoMoreInteractions(scheduleDAO);
    }
}