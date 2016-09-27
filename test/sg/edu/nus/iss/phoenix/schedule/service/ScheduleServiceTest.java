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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @InjectMocks
    private ScheduleService scheduleService;

    private List<AnnualSchedule> annualScheduleList;
//    private List<WeeklySchedule> weeklyScheduleList;

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
}