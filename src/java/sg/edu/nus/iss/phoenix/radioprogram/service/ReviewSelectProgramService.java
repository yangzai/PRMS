package sg.edu.nus.iss.phoenix.radioprogram.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

public class ReviewSelectProgramService {
	DAOFactoryImpl factory;
	ProgramDAO rpdao;

	public ReviewSelectProgramService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		rpdao = factory.getProgramDAO();
	}

	public List<RadioProgram> reviewSelectRadioProgram() {
            List<RadioProgram> data = null;
            try {
                data = rpdao.loadAll();
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
	}

}
