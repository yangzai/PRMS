package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import jdk.nashorn.internal.ir.IfNode;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduledProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectPresenterProducerDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Added by Xuemin on 15/09/20.
 */
@Action("enterps")
public class EnterDetailsScheduleCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        ProgramSlot ps = new ProgramSlot();

        //for test, a list of user to present presenter and producer
        //ReviewSelectUserDelegate delegate = new ReviewSelectUserDelegate();
        //List<User> userList = delegate.getAllUsers();

        String step = req.getParameter("step");
        req.setAttribute("ps_dateOfProgram", req.getParameter("dateOfProgram"));
        req.setAttribute("ps_startTime",req.getParameter("startTime"));
        req.setAttribute("ps_duration", req.getParameter("duration"));
        req.setAttribute("ps_radioProgramName", req.getParameter("radioProgramName"));
        req.setAttribute("ps_presenterId", req.getParameter("presenterId"));
        req.setAttribute("ps_presenterName", req.getParameter("presenterName"));
        req.setAttribute("ps_producerId", req.getParameter("producerId"));
        req.setAttribute("ps_producerName", req.getParameter("producerName"));
        req.setAttribute("insps",req.getParameter("insertps"));
        if (step.equals("details")){
            step = "selectPresenter";
            req.setAttribute("step", step);
            ReviewSelectProgramDelegate rpdel = new ReviewSelectProgramDelegate();
            List<RadioProgram> data = rpdel.reviewSelectRadioProgram();
            req.setAttribute("rps", data);
            req.setAttribute("reqrp","selectrp");
            return "/pages/crudrp.jsp";
        } else if (step.equals("selectPresenter")){
            step="selectProducer";
            req.setAttribute("step",step);
            ReviewSelectPresenterProducerDelegate ppdel=new ReviewSelectPresenterProducerDelegate();
            List<Presenter> dataOfPre=ppdel.getAllPresenters();
            req.setAttribute("ul",dataOfPre);
        //   req.setAttribute("ul",userList);
            req.setAttribute("reqtype","selectpre");
            return "/pages/userListPage.jsp";
        }else if (step.equals("selectProducer")){
            step="showProgramslotList";
            req.setAttribute("step",step);
            ReviewSelectPresenterProducerDelegate ppdel=new ReviewSelectPresenterProducerDelegate();
            List<Producer> dataOfPro=ppdel.getAllProducers();
            req.setAttribute("ul",dataOfPro);
        //    req.setAttribute("ul",userList);
            req.setAttribute("reqtype","selectpro");
            return "/pages/userListPage.jsp";
        }


        String dateOfP=req.getParameter("dateOfProgram");
        /*
        Date dateOfProgram=Date.valueOf(dateOfP);
        ps.setDateOfProgram(dateOfProgram);
        */

        String eL = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!dateOfP.matches(eL)){
            req.setAttribute("err_message", "Format date error, Date must be in format yyyy-mm-dd");
            return "/pages/error.jsp";
        }

        /*
        if (/\d{4}[-\/]\d{2}[-\/]\d{2}/.test(dateOfP)) {
            req.setAttribute("err_message", "Format date error");
            return "/pages/error.jsp";
        }
        */

        //the format of input date is "2016/09/23 18:27:00"
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = dateFormat.parse(dateOfP);
            java.sql.Date dateOfProgram = new java.sql.Date(date.getTime());
            System.out.println(dateOfProgram.getTime());
            ps.setDateOfProgram(dateOfProgram);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //the format of input time is "00:00:30"
        String staT=req.getParameter("startTime");

        String tL = "^\\d{2}:\\d{2}:\\d{2}$";
        if (!staT.matches(tL)){
            req.setAttribute("err_message", "Format startTime error, startTime must be in format tt:tt:tt");
            return "/pages/error.jsp";
        }

        Time startTime=Time.valueOf(staT);
        ps.setStartTime(startTime);


        //the format of input time is "00:00:30"
        String dur=req.getParameter("duration");

        String dL = "^\\d{2}:\\d{2}:\\d{2}$";
        if (!dur.matches(dL)){
            req.setAttribute("err_message", "Format duration error, duration must be in format tt:tt:tt");
            return "/pages/error.jsp";
        }

        Time duration=Time.valueOf(dur);
        ps.setDuration(duration);

        String programName=req.getParameter("radioProgramName");
        RadioProgram rp=new RadioProgram(programName);
        ps.setRadioProgram(rp);

        String presenterId=req.getParameter("presenterId");
        Presenter presenter= new Presenter(presenterId);
        ps.setPresenter(presenter);

        String producerId=req.getParameter("producerId");
        Producer producer=new Producer(producerId);
        ps.setProducer(producer);

        String insps = (String) req.getParameter("insertps");
        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Insert Flag: " + insps);
        //ins==true means create or copy
        if (insps.equalsIgnoreCase("true")) {
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
