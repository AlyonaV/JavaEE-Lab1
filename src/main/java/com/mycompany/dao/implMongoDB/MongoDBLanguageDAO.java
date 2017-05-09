package com.mycompany.dao.implMongoDB;

import com.mongodb.DB;
import com.mycompany.dao.LanguageDAO;
import com.mycompany.model.Language;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public class MongoDBLanguageDAO implements LanguageDAO{
    private DB db;

    public MongoDBLanguageDAO(DB db) {
        this.db = db;
    }

    @Override
    public void insertLanguage(Language language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLanguage(Language language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAllLanguages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
