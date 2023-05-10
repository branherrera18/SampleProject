package com.test.servlets;

import com.test.beans.Order;
import com.test.dao.ApplicationDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get username from session
        String username = (String) req.getSession().getAttribute("username");

        //call dao and get history
        ApplicationDao dao = new ApplicationDao();
        List<Order> orders = dao.getOrders(username);

        //set order data in request
        req.setAttribute("orders", orders);

        //forward to home.jsp
        req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
    }

}
