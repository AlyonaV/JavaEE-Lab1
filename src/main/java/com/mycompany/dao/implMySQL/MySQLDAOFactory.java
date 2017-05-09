package com.mycompany.dao.implMySQL;

import com.mycompany.dao.DAOFactory;
import com.mycompany.dao.GlossaryDAO;
import com.mycompany.dao.LanguageDAO;
import com.mycompany.dao.UserDAO;
import com.mycompany.dao.WordDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alona
 */
public class MySQLDAOFactory extends DAOFactory{
    
    public static final String URL = "jdbc:mysql://localhost:3306/glossary";
    public static final String USER = "javaUser";
    public static final String PASSWORD = "javaUser";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public static Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            System.out.println("Unable to connect to DB");
        }
        return connection;
    }
    
    @Override
    public UserDAO getUserDAO() {
       return new MySQLUserDAO();
    }

    @Override
    public GlossaryDAO getGlossaryDAO() {
        return new MySQLGlossaryDAO();
    }

    @Override
    public LanguageDAO getLanguageDAO() {
        return new MySQLLanguageDAO();
    }

    @Override
    public WordDAO getWordDAO() {
        return new MySQLWordDAO();
    }
    
}
