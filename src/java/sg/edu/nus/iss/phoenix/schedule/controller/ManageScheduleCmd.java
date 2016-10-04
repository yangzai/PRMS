package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduledProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> <strong>ManageScheduleCmd</strong> is triggered when user select Manage Schedule. It
 * will get the list of program slot from Use Case ReviewSelectScheduledProgram
 * and display it in scheduleProgramList page.</p>
 *
 * @author Tran Ngoc Hieu
 * @version 1.0 20 Sep 2016
 */
@Action("manageschedule")
public class ManageScheduleCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ReviewSelectScheduledProgramDelegate del = new ReviewSelectScheduledProgramDelegate();
        List<ProgramSlot> data = del.reviewSelectScheduledProgram();
        req.setAttribute("psl", data);
        return "/pages/scheduleProgramList.jsp";
    }
}
