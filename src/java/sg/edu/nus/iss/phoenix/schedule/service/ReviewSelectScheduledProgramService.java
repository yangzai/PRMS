package sg.edu.nus.iss.phoenix.schedule.service;

import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p><strong>ReviewSelectScheduleProgramService</strong> is for Review Select Scheduled Program use case.
 * It will get the list of all program slot and return </p>
 * @author Chen Yao
 * @version 1.0 20 Sep 2016
 */
public class ReviewSelectScheduledProgramService {
    DAOFactoryImpl factory;
    ScheduleDAO psdao;
    ProgramDAO pdao;
    UserDao udao;

    /**
     * Constructor of the service, get all the DAO object from factory.
     */
    public ReviewSelectScheduledProgramService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
        pdao = factory.getProgramDAO();
        udao = factory.getUserDAO();
    }

    /**
     * This method will return list of all Program Slot object and their attribute object.
     * Presenter/Producer and RadioProgram object will also be instantiate and add to the Program Slot.
     * @return  List of all Program Slot object
     */
    public List<ProgramSlot> reviewSelectScheduledProgram() {
        List<ProgramSlot> data = null;
        try {
            data = psdao.loadAll();
            for (int i = 0; i < data.size(); i++) {
                ProgramSlot programSlot = data.get(i);
                RadioProgram radioProgram = pdao.getObject(programSlot.getRadioProgram().getName());
                User userPresenter = udao.getObject(programSlot.getPresenter().getId());
                User userProducer = udao.getObject(programSlot.getProducer().getId());
                programSlot.setRadioProgram(radioProgram);
                programSlot.getPresenter().setAll(userPresenter.getId(), userPresenter.getPassword(), userPresenter.getName(), userPresenter.getRolesInString());
                programSlot.getProducer().setAll(userProducer.getId(), userProducer.getPassword(), userProducer.getName(), userProducer.getRolesInString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectScheduledProgramService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return data;
    }

}
