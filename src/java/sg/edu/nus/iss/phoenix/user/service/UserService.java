package sg.edu.nus.iss.phoenix.user.service;

import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by NguyenTrung on 9/9/16.
 */
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    DAOFactory factory;
    UserDao userDAO;
    RoleDao roleDAO;

    public UserService() {
        factory = new DAOFactoryImpl();
        userDAO = factory.getUserDAO();
        roleDAO = factory.getRoleDAO();
    }

    public List<Role> getAllRoles(){
        try {
            return roleDAO.loadAll();
        } catch (Exception ex){
            logger.log(Level.SEVERE,"SQL Error",ex.toString());
            return new ArrayList<>();
        }
    }
    public Map getUserRolesMapping(String userId){
        try {
            ArrayList roles = userDAO.searchMatching(userId).getRoles();
            List<Role> allroles = this.getAllRoles();
            HashMap<String,Boolean> rolesMapping = new HashMap<>();
            for (Role role: allroles){
                rolesMapping.put(role.getRole(),roles.contains(role));
            }
            return rolesMapping;

        } catch (Exception ex){
            logger.log(Level.SEVERE,"SQL Error",ex.toString());
            return new HashMap();
        }
    }
}
