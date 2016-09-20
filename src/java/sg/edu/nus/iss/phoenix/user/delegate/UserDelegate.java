package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.controller.ReturnCode;
import sg.edu.nus.iss.phoenix.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NguyenTrung on 9/9/16.
 */
public class UserDelegate {
    UserService service;

    public UserDelegate() {
        service = new UserService();
    }

    public List<Role> getAllRoles(){
        return service.getAllRoles();
    }

    public int processResetPwd(String userId, String newPassword){
        return service.resetPassword(userId, newPassword);
    }

    public int processCreate(User user, String[] chkRoles){
        return service.processCreate(user, chkRoles);
    }

    public int processDelete(String userid){
        User user = service.checkUserExist(userid);
        if (user == null){
            return ReturnCode.USER_NOT_FOUND;
        }
        return service.deleteUser(user);
    }
}
