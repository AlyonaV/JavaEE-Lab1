package com.mycompany.dao.implMongoDB;

import com.mongodb.DB;
import com.mycompany.dao.GlossaryDAO;
import com.mycompany.model.Glossary;
import com.mycompany.model.User;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public class MongoDBGlossaryDAO implements GlossaryDAO{
    private DB db;

    public MongoDBGlossaryDAO(DB db) {
        this.db = db;
    }

    @Override
    public void insertGlossary(Glossary glossary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGlossary(Glossary glossary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getUserGlossaries(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
