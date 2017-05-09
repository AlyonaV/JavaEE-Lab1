package com.mycompany.dao.implMySQL;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Alona
 */
public class MySQLUserDAO implements UserDAO{
    //private Connection connection;

    public MySQLUserDAO(){
        //connection = MySQLDAOFactory.createConnection();
    }
    
    @Override
    public void insertUser(User user) {
        try (Connection connection = MySQLDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(email, username, password) values (?, ?, ? )");
            ){
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());            
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(User user) {
      try (Connection connection = MySQLDAOFactory.createConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("delete from users where user_id=?");
           )
           {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public User findUser(int userId) {
        User user = new User();
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_id=?");
                )
        {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
       try (Connection connection = MySQLDAOFactory.createConnection(); 
               PreparedStatement preparedStatement = connection.prepareStatement("update users set email=?, username=?, password=?"
                    + "where user_id=?");
               )
       {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public Collection getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = MySQLDAOFactory.createConnection(); 
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from users");
            )
        {
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
}
