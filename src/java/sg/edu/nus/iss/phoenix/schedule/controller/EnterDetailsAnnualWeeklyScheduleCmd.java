package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yangzai on 11/9/16.
 */
@Action("enter-details-annual-schedule")
public class EnterDetailsAnnualWeeklyScheduleCmd implements Perform {

    @Override
    public String perform(String s, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        AuthenticateDelegate authenticateDelegate = new AuthenticateDelegate();
        if (user == null || !user.getRoles().stream().map(Role::getRole).anyMatch("manager"::equals))
            return "/pages/login.jsp";

        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();

        try {
            AnnualSchedule annualSchedule = new AnnualSchedule(
                    Integer.parseInt(req.getParameter("year")), user
            );
            scheduleDelegate.processCreateAnnualWeeklySchedule(annualSchedule);
        } catch (IllegalArgumentException e) {
            return "/pages/error.jsp";
        }

        List<AnnualSchedule> data = scheduleDelegate.processRetrieveAllAnnualSchedule();
        req.setAttribute("annualScheduleList", data);
        return "/pages/annualScheduleListPage.jsp";
    }
}
