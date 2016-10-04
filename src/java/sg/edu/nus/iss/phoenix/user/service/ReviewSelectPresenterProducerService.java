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
 * <p> <strong>ReviewSelectPresenterProducerService</strong> is for Review Select Presenter/Producer use case.
 * This class will help user in selecting Presenter or Producer from database. The difference between this
 * class and <strong>ReviewSelectUserService</strong> is: this class will return the list type Presenter/Producer only
 * while the ReviewSelectUserService will display all user in the system</p>
 *
 * @author Gao Haijun
 * @version 1.0 15 Sep 2016
 */

public class ReviewSelectPresenterProducerService {
    private static final Logger logger =
            Logger.getLogger(ReviewSelectUserService.class.getName());
    DAOFactoryImpl factory;
    UserDao userDAO;

    /**
     * Constructor of the service, get all the DAO object from factory.
     */
    public ReviewSelectPresenterProducerService() {
        factory = new DAOFactoryImpl();
        userDAO = factory.getUserDAO();
    }

    /**
     * This method will return list of all Producer in the system
     * @return  List of all Producer
     */
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

    /**
     * This method will return list of all Presenter in the system
     * @return  List of all Presenter
     */
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
