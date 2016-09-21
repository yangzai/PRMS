package sg.edu.nus.iss.phoenix.user.controller;

/**
 * Created by NguyenTrung on 9/9/16.
 */
import at.nocturne.api.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Action("manageuser")
public class ManageUserCmd implements Perform {

    @Override
    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        ReviewSelectUserDelegate delegate = new ReviewSelectUserDelegate();
        List<User> userList = delegate.getAllUsers();
        httpServletRequest.setAttribute("ul",userList);
        return "/pages/userListPage.jsp";

    }
}
