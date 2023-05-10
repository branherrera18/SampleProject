package com.test.servlets;

import com.test.beans.User;
import com.test.dao.ApplicationDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the username from the session
        System.out.println("Username in profile servlet: " + req.getSession().getAttribute("username"));
        String username = (String)req.getSession().getAttribute("username");
        
        //call dao and get profile details
        ApplicationDao dao = new ApplicationDao();
        User user  = dao.getProfileDetails(username);
        
        //store all information in request object
        req.setAttribute("user", user);
        
        //forward control to profile jsp
        req.getRequestDispatcher("/html/profile.jsp").forward(req, resp);
    }
}
