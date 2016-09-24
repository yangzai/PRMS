package sg.edu.nus.iss.phoenix.schedule.service;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yao on 15/09/16.
 */
public class ReviewSelectScheduledProgramService {
    DAOFactoryImpl factory;
    ScheduleDAO psdao;

    public ReviewSelectScheduledProgramService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }

    public List<ProgramSlot> reviewSelectScheduledProgram() {
        List<ProgramSlot> data = null;
        try {
            data = psdao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectScheduledProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
