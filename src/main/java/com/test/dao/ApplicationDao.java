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
    
   public List<Product> searchProducts(String searchString){
       Product product = null; 
       List<Product> products = new ArrayList<>();
       
       try {
           
           Connection connection = DBConnection.getConnectionToDatabase();
       
       String sql = "select * from products where product_name like '%" +searchString+"%'";
       
       Statement statement = connection.createStatement();
       ResultSet set = statement.executeQuery(sql);
       
       while(set.next()){
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
   
   public int registerUser(User user){
       int rowsAffected = 0;
       try {
           // get the connection for the database
           Connection connection = DBConnection.getConnectionToDatabase();
           
           //write the insert query
           String insertQuery = "insert into users values (?,?,?,?,?,?)";
           
           //set parameters with PreparedStatement
           PreparedStatement statement  = connection.prepareStatement(insertQuery);
           statement.setString(1,user.getUsername());
           statement.setString(1,user.getPassword());
           statement.setString(1,user.getFirstName());
           statement.setString(1,user.getLastName());
           statement.setInt(1,user.getAge());
           statement.setString(1,user.getActivity());
           
           //execute the statement
           rowsAffected = statement.executeUpdate();
       } catch (SQLException e) {
       e.printStackTrace();
       }
       return rowsAffected;
   }
}
