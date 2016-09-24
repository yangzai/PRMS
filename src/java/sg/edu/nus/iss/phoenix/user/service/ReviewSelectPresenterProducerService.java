package sg.edu.nus.iss.phoenix.user.service;

import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Gao.Haijun on 15/09/16.
 */
public class ReviewSelectPresenterProducerService {
    private static final Logger logger =
            Logger.getLogger(ReviewSelectUserService.class.getName());
    DAOFactoryImpl factory;
    UserDao userDAO;

    public ReviewSelectPresenterProducerService() {
        factory = new DAOFactoryImpl();
        userDAO = factory.getUserDAO();
    }

    public List<Producer> getAllProducers() {
        try {
            ArrayList<Producer> pList = new ArrayList<>();
            for (User tmp:userDAO.loadAllProducers()) {
                pList.add((Producer)tmp);
            }
           return pList;
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "SQL Error: ", ex.toString());
            return new ArrayList<Producer>();
        }
    }

    public List<Presenter> getAllPresenters() {
        try {
            ArrayList<Presenter> pList = new ArrayList<>();
            for (User tmp:userDAO.loadAllPresenters()) {
                pList.add((Presenter)tmp);
            }
            return pList;
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "SQL Error: ", ex.toString());
            return new ArrayList<Presenter>();
        }
    }
}
