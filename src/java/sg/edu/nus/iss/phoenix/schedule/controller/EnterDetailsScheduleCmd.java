package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduledProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Added by Xuemin on 15/09/20.
 */
@Action("enterps")
public class EnterDetailsScheduleCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        ProgramSlot ps = new ProgramSlot();


        String dateOfP=req.getParameter("dateOfProgram");
        Date dateOfProgram=Date.valueOf(dateOfP);
        ps.setDateOfProgram(dateOfProgram);

        String dur=req.getParameter("duration");
        Time duration=Time.valueOf(dur);
        ps.setDuration(duration);

        String programName=req.getParameter("radioProgram");
        RadioProgram rp=new RadioProgram(programName);
        ps.setRadioProgram(rp);

        String presenterId=req.getParameter("presenterId");
        Presenter presenter= new Presenter(presenterId);
        ps.setPresenter(presenter);

        String producerId=req.getParameter("producerId");
        Producer producer=new Producer(producerId);
        ps.setProducer(producer);

        String ins = (String) req.getParameter("ins");
        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Insert Flag: " + ins);
        //ins==true means create or copy
        if (ins.equalsIgnoreCase("true")) {
            del.processCreate(ps);
        } else {
            del.processModify(ps);
        }

        ReviewSelectScheduledProgramDelegate rsdel = new ReviewSelectScheduledProgramDelegate();
        List<ProgramSlot> data = rsdel.reviewSelectScheduledProgram();
        //parse programslots to scheduleProgramList page
        req.setAttribute("psl", data);
        return "/pages/scheduleProgramList.jsp";
    }
}
