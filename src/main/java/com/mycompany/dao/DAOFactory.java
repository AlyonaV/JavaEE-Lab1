package com.mycompany.dao;

import com.mycompany.dao.implMongoDB.MongoDBDAOFactory;
import com.mycompany.dao.implMySQL.MySQLDAOFactory;

/**
 *
 * @author Alona
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int MONGODB = 2;
    
    public abstract UserDAO getUserDAO();
    public abstract GlossaryDAO getGlossaryDAO();
    public abstract LanguageDAO getLanguageDAO();
    public abstract WordDAO getWordDAO();
    
    public static DAOFactory getDAOFactory(int whichFactory){
    
        switch (whichFactory){
            case MYSQL:
                return new MySQLDAOFactory();
            case MONGODB:
                return MongoDBDAOFactory.getInstance();
            default:
                    return null;
        }
    }
}
