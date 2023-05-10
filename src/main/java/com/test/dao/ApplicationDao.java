package com.test.dao;

import com.test.beans.Product;
import com.test.beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public List<Product> searchProducts(String searchString, Connection connection) {
        Product product = null;
        List<Product> products = new ArrayList<>();

        try {
            

            String sql = "select * from products where product_name like '%" + searchString + "%'";

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                product = new Product();
                product.setProductId(set.getInt("product_id"));
                product.setProductImgPath(set.getString("image_path"));
                product.setProductName(set.getString("product_name"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {
            // get the connection for the database
            Connection connection = DBConnection.getConnectionToDatabase();

            //write the insert query
            String insertQuery = "insert into users values (?,?,?,?,?,?)";

            //set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());

            //execute the statement
            rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public boolean validateUser(String username, String password) {
        boolean isValidUser = false;
        try {

            //get the connection for the database
            Connection connection = DBConnection.getConnectionToDatabase();

            //write the insert query
            String sql = "select* from users where username=? and password=?";

            //set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            //execute the statement and check whether users exists
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                isValidUser = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValidUser;
    }

    
    
    public User getProfileDetails(String username){
        User user = null;
        try {
            //get connection to db
            Connection connection = DBConnection.getConnectionToDatabase();
            
            //write select query to get profile details
            String sql = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            
            //execute query, get resultset and return user info
            ResultSet set = statement.executeQuery();
            
            while(set.next()){
                user = new User();
                user.setUsername(set.getString("username"));
                user.setFirstName(set.getString("first_name"));
                user.setLastName(set.getString("last_name"));
                user.setActivity(set.getString("activity"));
                user.setAge(set.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
}
