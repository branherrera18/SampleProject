package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //collect search string from the form
        String searchString = req.getParameter("search");
        //call DAO layer and get all products for search criteria
        
        //write the products data back to the client
    }
    
    
}
