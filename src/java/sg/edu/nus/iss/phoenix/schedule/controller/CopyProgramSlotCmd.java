package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Added by Xuemin on 15/09/20.
 */
@Action("copyps")
public class CopyProgramSlotCmd implements Perform {
    @Override
    public String perform(String s, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("ps_dateOfProgram", req.getParameter("dateOfProgram"));
        req.setAttribute("ps_startTime",req.getParameter("startTime"));
        req.setAttribute("ps_duration", req.getParameter("duration"));
        req.setAttribute("ps_radioProgramName", req.getParameter("radioProgramName"));
        req.setAttribute("ps_presenterId", req.getParameter("presenterId"));
        req.setAttribute("ps_presenterName", req.getParameter("presenterName"));
        req.setAttribute("ps_producerId", req.getParameter("producerId"));
        req.setAttribute("ps_producerName", req.getParameter("producerName"));
        req.setAttribute("insps", req.getParameter("insertps"));
        return "/pages/setupschedule.jsp";
    }
}
