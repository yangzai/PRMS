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
 * Created by Tran Ngoc Hieu 20 Sep 2016
 * The ManageSchedule Command is triggered when user select Manage Schedule. It
 * will get the list of program slot from Use Case ReviewSelectScheduledProgram
 * and display it in scheduleProgramList page.
 */
@Action("manageschedule")
public class ManageScheduleCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ReviewSelectScheduledProgramDelegate del = new ReviewSelectScheduledProgramDelegate();
        List<ProgramSlot> data = del.reviewSelectScheduledProgram();
        //System.out.print(data.get(0).getDuration());
        System.out.print("hello world");
        //List<ProgramSlot> data = new ArrayList<ProgramSlot>();
        req.setAttribute("psl", data);
        return "/pages/scheduleProgramList.jsp";
    }
}
