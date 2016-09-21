package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
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
 * Created by yao on 15/09/16.
 */
@Action("deleteuser")
public class DeleteUserCmd implements Perform {
    @Override
    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        UserDelegate userDelegate = new UserDelegate();
        String userId = httpServletRequest.getParameter("id");
        int returnCode = userDelegate.processDelete(userId);
        switch (returnCode){
            case ReturnCode.SUCCESS:
                List<User> ul = new ReviewSelectUserDelegate().getAllUsers();
                httpServletRequest.setAttribute("ul",ul);
                return "/pages/userListPage.jsp";
            default:
                httpServletRequest.setAttribute("err_message","System error");
                return "/pages/error.jsp";
        }
    }
}
