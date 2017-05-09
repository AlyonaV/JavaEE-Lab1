package com.mycompany.dao.implMongoDB;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mycompany.dao.DAOFactory;
import com.mycompany.dao.GlossaryDAO;
import com.mycompany.dao.LanguageDAO;
import com.mycompany.dao.UserDAO;
import com.mycompany.dao.WordDAO;

/**
 *
 * @author Alona
 */
public class MongoDBDAOFactory extends DAOFactory{
    
    private MongoClient connection;
    private DB db;
    
    private static MongoDBDAOFactory mongoDAO;
    
    private MongoDBDAOFactory(){
        connection = new MongoClient("localhost", 27017);
        db = connection.getDB("glossary");
    }
    
    public static MongoDBDAOFactory getInstance(){
        if(mongoDAO == null){
            mongoDAO = new MongoDBDAOFactory();
        }
        return mongoDAO;
    }

    @Override
    public UserDAO getUserDAO() {
        return new MongoDBUserDAO(db);
    }

    @Override
    public GlossaryDAO getGlossaryDAO() {
        return new MongoDBGlossaryDAO(db);
    }

    @Override
    public LanguageDAO getLanguageDAO() {
        return new MongoDBLanguageDAO(db);
    }

    @Override
    public WordDAO getWordDAO() {
        return new MongoDBWordDAO(db);
    }
    
}
