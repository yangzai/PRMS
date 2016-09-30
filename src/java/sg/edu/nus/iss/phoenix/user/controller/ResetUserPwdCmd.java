//package sg.edu.nus.iss.phoenix.user.controller;
//
//import at.nocturne.api.Action;
//import at.nocturne.api.Perform;
//import sg.edu.nus.iss.phoenix.authenticate.entity.User;
//import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;
//import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by NguyenTrung on 15/9/16.
// */
//@Action("resetUserPassword")
//public class ResetUserPwdCmd implements Perform {
//    @Override
//    public String perform(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
//        UserDelegate delegate = new UserDelegate();
//        String newPassword = httpServletRequest.getParameter("password");
//        String userId = httpServletRequest.getParameter("id");
//        int returnCode = delegate.processResetPwd(userId, newPassword);
//        switch (returnCode){
//            case ReturnCode.SUCCESS:
//                ReviewSelectUserDelegate reviewSelectUserDelegate = new ReviewSelectUserDelegate();
//                List<User> userList = reviewSelectUserDelegate.getAllUsers();
//                httpServletRequest.setAttribute("ul", userList);
//                return "/pages/userListPage.jsp";
//            case ReturnCode.USER_NOT_FOUND:
//                httpServletRequest.setAttribute("err_message","User doest not exist");
//                return "/pages/error.jsp";
//            default:
//                httpServletRequest.setAttribute("err_message","System Error");
//                return "/pages/error.jsp";
//        }
//
//    }
//}
//
