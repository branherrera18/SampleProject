package com.test.servlets;

import com.test.dao.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
    public Connection connection = null;
    
    //life cycle methods - init, service, destroy
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in service method");
        //forward the control to the index.html
        
        req.getRequestDispatcher("/html/index.html").forward(req, resp);
        
    }

    @Override
    public void init() throws ServletException {
        System.out.println("in init method"); 
        //initialization activity - set up connection object
        connection = DBConnection.getConnectionToDatabase();
    }

    @Override
    public void destroy() {
        System.out.println("in destroy method");
        try {
            //clean up activity  - close DB connection object
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    
}
