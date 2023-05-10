package com.test.listeners;

import com.test.dao.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("in contextinitialized method");
        Connection connection = DBConnection.getConnectionToDatabase();
        sce.getServletContext().setAttribute("dbconnection", connection);
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("in contextDestroyed method");
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbconnection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
