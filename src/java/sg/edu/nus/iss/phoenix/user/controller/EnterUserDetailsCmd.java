package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenTrung on 9/9/16.
 */
@Action("enteruser")
public class EnterUserDetailsCmd implements Perform{
    @Override
    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        UserDelegate delegate = new UserDelegate();
        User user = new User();
        user.setId(httpServletRequest.getParameter("id"));
        user.setPassword(httpServletRequest.getParameter("password"));
        user.setName(httpServletRequest.getParameter("name"));
        String[] chkRoles = httpServletRequest.getParameterValues("chkRoles");
        String ins = httpServletRequest.getParameter("ins");
        int returnCode = 0;
        if (ins.equalsIgnoreCase("true")){
            returnCode = delegate.processCreate(user,chkRoles);
        }
        switch (returnCode) {
            case 1:
                ReviewSelectUserDelegate reviewSelectUserDelegate = new ReviewSelectUserDelegate();
                List<User> userList = reviewSelectUserDelegate.getAllUsers();
                httpServletRequest.setAttribute("ul",userList);
                return "/pages/cruduser.jsp";
            case -1:
                httpServletRequest.setAttribute("err_message","Duplicate user id");
                return "/pages/error.jsp";
            default:
                httpServletRequest.setAttribute("err_message","System error");
                return "/pages/error.jsp";
        }

    }
}
