package com.test.dao;

import com.test.beans.Product;
import com.test.beans.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
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
        int age = Integer.parseInt(req.getParameter("age"));
        String activity = req.getParameter("activity");
        
        //fill it up in a User bean
        User user = new User(username, password, firstName, lastName, age, activity);
        
        
        //call dao layer and save the user object to DB
        ApplicationDao dao = new ApplicationDao();
        int rows = dao.registerUser(user);
        
        
        //prepare an information message for user about the success or failure of the operation
        String infoMessage = null;
        if(rows==0){
            infoMessage = "Sorry, an error ocurred!";
        }else{
            infoMessage = "User registered successfully!";
        }
        
        //write the messsage back to the page in cient browser
        String page  = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), infoMessage);
        resp.getWriter().write(page);
        
        
    }
    
    public String getHTMLString(String filePath, String message) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line ="";
        StringBuffer buffer = new StringBuffer();
        while((line=reader.readLine())!=null){
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();
        
        page = MessageFormat.format(page, message);
        return page;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), "");
        resp.getWriter().write(page);
    }
}
