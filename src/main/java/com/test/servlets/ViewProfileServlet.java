package com.test.servlets;

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
        
        //forward control to profile jsp
        
        req.getRequestDispatcher("html/profile.jsp").forward(req, resp);
    }
}
