package sg.edu.nus.iss.phoenix.schedule.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.controller.ReturnCode;
import sg.edu.nus.iss.phoenix.user.service.UserService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Haijun on 2016/9/23.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReviewSelectScheduledProgramTest {
    @Mock
    private ScheduleDAO scheduleDAO;
    @InjectMocks
    private ReviewSelectScheduledProgramService reviewSelectScheduledProgramService;

    ArrayList<ProgramSlot> programSlotList;

    @Before
    public void setUp() throws Exception {
        programSlotList = new ArrayList<ProgramSlot>();
        Calendar now = Calendar.getInstance();
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),now.get(Calendar.SECOND));
        Date date = new Date(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        ProgramSlot ps = new ProgramSlot(time,date);
        programSlotList.add(ps);
    }

    @Test
    public void reviewSelectScheduledProgramTest() throws Exception {
        when(scheduleDAO.loadAll()).thenReturn(programSlotList);
        assertThat(true,
                is(programSlotList.equals(reviewSelectScheduledProgramService.reviewSelectScheduledProgram())));
        verify(scheduleDAO).loadAll();

        when(scheduleDAO.loadAll()).thenReturn(null);
        assertThat(null,
                is(reviewSelectScheduledProgramService.reviewSelectScheduledProgram()));
        verify(scheduleDAO, times(2)).loadAll();
        verifyNoMoreInteractions(scheduleDAO);
    }
}
