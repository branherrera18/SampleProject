
package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.internal.org.jline.reader.PrintAboveWriter;


public class GetServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ServletConfig config = getServletConfig();
        System.out.println(config.getInitParameter("URL"));
        
        ServletContext context = getServletContext();
        System.out.println(context.getInitParameter("dbURL"));
       
        
        String htmlResponse = "<html><h3>Welcome to Servlets!</h3></html>";
        PrintWriter writer = resp.getWriter();
        writer.write(htmlResponse);
    }
    
    
    
}
