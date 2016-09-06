/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author boonkui
 */
@Action("login")
public class LoginCmd implements Perform {

  //  @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate ad = new AuthenticateDelegate();
        User user = new User();
        user.setId(req.getParameter("id"));
        user.setPassword(req.getParameter("password"));
        user = ad.validateUserIdPassword(user);
        if (null != user) {
            req.getSession().setAttribute("user", user);
            return "/pages/home.jsp";
        } else
            return "/pages/error.jsp";
    }
}
