package sg.edu.nus.iss.phoenix.user.service;

import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.user.controller.ReturnCode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<Role> getAllRoles() {
        try {
            return roleDAO.loadAll();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "SQL Error", ex.toString());
            return new ArrayList<>();
        }
    }

    public int processCreate(User user, String[] chkRoles) {
        try {
            if (userDAO.searchMatching(user.getId()) != null) {
                // User id exists
                return ReturnCode.USER_DUPLICATED;
            }
            user.setRoles(this.searchRolesByStrings(chkRoles));
            if (user.getRoles().size() == 0) {
                return ReturnCode.USER_HAS_NO_ROLE;
            }
            userDAO.create(user);
            return ReturnCode.SUCCESS;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error", ex.toString());
        }
        return ReturnCode.FAIL;
    }

    public int resetPassword(String userId, String newPassword) {
        try {
            User user = userDAO.searchMatching(userId);
            if (user == null) {
                //User does not exist
                return ReturnCode.USER_NOT_FOUND;
            }
            user.setPassword(newPassword);
            userDAO.save(user);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error ", e.toString());
        }
        return ReturnCode.FAIL;
    }

    public int processModify(User user, String[] chkRoles) {
        try {
            User foundUser = userDAO.searchMatching(user.getId());
            if (user == null) {
                //User does not exist
                return ReturnCode.USER_NOT_FOUND;
            }
            if (user.getPassword().equals("") || chkRoles == null) {
                return ReturnCode.FAIL;
            }
            ArrayList<Role> roles = this.searchRolesByStrings(chkRoles);
            foundUser.setName(user.getName());
            foundUser.setPassword(user.getPassword());
            foundUser.setRoles(roles);
            userDAO.save(foundUser);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error ", e.toString());
        }
        return ReturnCode.FAIL;
    }

    public ArrayList<Role> searchRolesByStrings(String[] strs) throws Exception {
        ArrayList<Role> roles = new ArrayList<>();
        if (strs == null) return roles;
        for (String roleStr : strs) {
            Role role = null;
            try {
                role = roleDAO.searchMatching(roleStr);
                if (role != null) {
                    roles.add(role);
                }
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "SQL Error ", ex.toString());
            }
        }
        return roles;
    }

    public User checkUserExist(String userid) {
        User user = null;
        try {
            user = userDAO.searchMatching(userid);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Error ", e.toString());
        }
        return user;
    }

    public int deleteUser(User user) {
        int returnCode = ReturnCode.FAIL;
        try {
            userDAO.delete(user);
            returnCode = ReturnCode.SUCCESS;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error ", ex.toString());
        }
        return returnCode;
    }

    public Map getUserRolesMapping(String userId) {
        try {
            User user = userDAO.searchMatching(userId);
            if (user == null) {
                return new HashMap();
            }
            String userRoles = user.getRolesInString().toLowerCase();
            List<Role> allRoles = this.getAllRoles();
            HashMap<String, Boolean> rolesMapping = new HashMap<>();
            for (Role role : allRoles) {
                rolesMapping.put(role.getRole(), userRoles.contains(role.getRole().toLowerCase()));
            }
            return rolesMapping;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "SQL Error", ex.toString());
        }
        return new HashMap();
    }
}
