package com.test.servlets;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {

    private static final long serialVerionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the HTTPSession object
        HttpSession session = req.getSession();

        //create a cart as arraylist fot the user
        List<String> cart = (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        //add the selected product to the cart
        if (req.getParameter("product") != null) {
            cart.add(req.getParameter("product"));
        }

        session.setAttribute("cart", cart);

        //get search criteria from search servlet
        String search = (String) session.getAttribute("search");
        Connection connection = (Connection) getServletContext().getAttribute("dbconnection");

        //get the search results from dao
        ApplicationDao dao = new ApplicationDao();
        List<Product> products = dao.searchProducts(search, connection);

        //set the search results in request scope
        req.setAttribute("products", products);

        //forward to search.jsp
        req.getRequestDispatcher("/html/search.jsp").forward(req, resp);
    }

}
