package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduledProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yao on 15/09/16.
 */
@Action("deleteps")
public class DeleteProgramSlotCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        String dateOfProgram = req.getParameter("dateOfProgram");
        String duration=req.getParameter("duration");
        del.processDelete(duration,dateOfProgram);

        ReviewSelectScheduledProgramDelegate rsDel = new ReviewSelectScheduledProgramDelegate();
        List<ProgramSlot> data = rsDel.reviewSelectScheduledProgram();
        req.setAttribute("rps", data);
        return "/pages/scheduleProgramList.jsp";
        return null;
    }
}
