package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.user.service.UserService;

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
    public Map<String, Boolean> getUserRolesMapping(String id){
        return service.getUserRolesMapping(id);
    }
}
