package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectUserService;

import java.util.List;

/**
 * Created by NguyenTrung on 9/9/16.
 */
public class ReviewSelectUserDelegate {

    private ReviewSelectUserService service;

    public ReviewSelectUserDelegate() {
        super();
        service = new ReviewSelectUserService();
    }

    public List<User> getAllUsers(){
        return service.getAllUsers();
    }


}
