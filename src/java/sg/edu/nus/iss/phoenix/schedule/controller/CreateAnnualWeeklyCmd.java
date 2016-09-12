package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yangzai on 9/9/16.
 */
@Action("create-annual-schedule")
public class CreateAnnualWeeklyCmd implements Perform {
    @Override
    public String perform(String s, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();
        List<AnnualSchedule> data = scheduleDelegate.processRetrieveAllAnnualSchedule();
        req.setAttribute("annualScheduleList", data);
        return "/pages/annualScheduleListPage.jsp";
    }
}
