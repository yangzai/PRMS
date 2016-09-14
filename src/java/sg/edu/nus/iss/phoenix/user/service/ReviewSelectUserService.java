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
 * Created by NguyenTrung on 9/9/16.
 */
public class ReviewSelectUserService {
    private static final Logger logger =
            Logger.getLogger(ReviewSelectUserService.class.getName());

    DAOFactoryImpl factory;
    UserDao userDAO;

    public ReviewSelectUserService() {
        factory = new DAOFactoryImpl();
        userDAO = factory.getUserDAO();
    }

    public List<User> getAllUsers(){
        try {
            return userDAO.loadAll();
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "SQL Error: ", ex.toString());
            return new ArrayList<User>();
        }
    }
}
