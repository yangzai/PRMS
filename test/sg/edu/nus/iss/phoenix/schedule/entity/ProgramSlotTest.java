package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 * Created by Administrator on 2016/9/27.
 */

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Created by Haijun on 2016/9/27.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramSlotTest {
    private Date date;
    private Time time;
    private Time Duration;
    private RadioProgram rp;
    private Producer prod;
    private Presenter pres;

    @Before
    public void setUp() throws Exception {
        Calendar now = Calendar.getInstance();
        Time time =new Time(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),now.get(Calendar.SECOND));
        Date date = new Date(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        rp = new RadioProgram("test");
        prod = new Producer();
        pres = new Presenter();
    }

    @Test
    public void ProgramSlotEntityTest( ) {
        ProgramSlot ps = new ProgramSlot();
        ps.setAll(date,new Time(3600), time, rp, pres, prod);

        ProgramSlot ps2 = new ProgramSlot();
        ps2.setAll(date,new Time(3600), time, rp, pres, prod);

        assertThat(true, is(ps.hasEqualMapping(ps2)));

        ProgramSlot ps3 = new ProgramSlot(time, date);
        assertThat(false, is(ps.hasEqualMapping(ps3)));
    }

}
