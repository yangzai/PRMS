package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.UserType;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectPresenterProducerDelegate;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Gao.Haijun on 15/09/16.
 */

@Action("selectpnp")
public class SelectPresenterProducerCmd implements Perform{
    @Override
    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        ReviewSelectPresenterProducerDelegate pnpDelegate = new ReviewSelectPresenterProducerDelegate();
        int roletype = Integer.parseInt(httpServletRequest.getParameter("rtype"));
        switch (roletype)
        {
            case UserType.PRSENTER:
                List<Presenter> presenterList = pnpDelegate.getAllPresenters();
                httpServletRequest.setAttribute("ul",presenterList);
                break;
            case UserType.PRODUCER:
                List<Producer> producerList = pnpDelegate.getAllProducers();
                httpServletRequest.setAttribute("ul",producerList);
                break;
            default:
                return "/pages/error.jsp";
        }
        httpServletRequest.setAttribute("reqtype","selectpnp");
        return "/pages/userListPage.jsp";
    }
}
