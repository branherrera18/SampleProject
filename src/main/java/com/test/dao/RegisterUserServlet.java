package com.test.dao;

import com.test.beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //collect all form data
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String activity = req.getParameter("activity");
        int age = Integer.parseInt(req.getParameter("age"));
        
        //fill it up in a User bean
        User user = new User(password, username, firstName, lastName, age, activity);
        
        
        //call dao layer and save the user object to DB
        ApplicationDao dao = new ApplicationDao();
        
        
        //prepare an information message for user about the success or failure of the operation
        
        //write the messsage back to the page in cient browser
        
    }
    
    
}
