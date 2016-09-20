package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yao on 15/09/16.
 */
@Action("copyps")
public class CopyProgramSlotCmd implements Perform {
    @Override
    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return "/pages/setupschedule.jsp";
    }
}
