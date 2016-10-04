package sg.edu.nus.iss.phoenix.user.service;

import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p><strong>ReviewSelectUserService</strong> is meant for use case <strong>Maintain User</strong> to display a list
 * of all users in the system</p>
 *
 * @author Nguyen Bui An Trung
 * @version 1.0 9 Sep 2016
 */
public class ReviewSelectUserService {
    private static final Logger logger =
            Logger.getLogger(ReviewSelectUserService.class.getName());

    DAOFactoryImpl factory;
    UserDao userDAO;

    /**
     * Constructor of the service, get all the DAO object from factory.
     */
    public ReviewSelectUserService() {
        factory = new DAOFactoryImpl();
        userDAO = factory.getUserDAO();
    }

    /**
     * This method will return list of all user in the system
     * @return  List of all User
     */
    public List<User> getAllUsers(){
        try {
            return userDAO.loadAll();
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "SQL Error: ", ex.toString());
            return new ArrayList<User>();
        }
    }
}
