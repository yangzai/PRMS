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
        user.setId(httpServletRequest.getParameter("id").trim());
        user.setPassword(httpServletRequest.getParameter("password").trim());
        user.setName(httpServletRequest.getParameter("name").trim());
        String[] chkRoles = httpServletRequest.getParameterValues("chkRoles");
        String ins = httpServletRequest.getParameter("ins");
        int returnCode;
        if (ins.equalsIgnoreCase("true")){
            returnCode = delegate.processCreate(user,chkRoles);
        } else {
            returnCode = delegate.processModify(user,chkRoles);
        }
        switch (returnCode) {
            case ReturnCode.SUCCESS:
                ReviewSelectUserDelegate reviewSelectUserDelegate = new ReviewSelectUserDelegate();
                List<User> userList = reviewSelectUserDelegate.getAllUsers();
                httpServletRequest.setAttribute("ul",userList);
                return "/pages/userListPage.jsp";
            case ReturnCode.USER_DUPLICATED:
                httpServletRequest.setAttribute("err_message","Duplicate user id");
                return "/pages/error.jsp";
            case ReturnCode.USER_HAS_NO_ROLE:
                httpServletRequest.setAttribute("err_message","Please choose at least one role");
                return "/pages/error.jsp";
            case ReturnCode.USER_NOT_FOUND:
                httpServletRequest.setAttribute("err_message","User doest not exist");
                return "/pages/error.jsp";
            default:
                httpServletRequest.setAttribute("err_message","System error");
                return "/pages/error.jsp";
        }

    }
}
