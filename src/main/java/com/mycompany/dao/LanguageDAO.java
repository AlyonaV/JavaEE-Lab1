package com.mycompany.dao;

import com.mycompany.model.Language;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public interface LanguageDAO {
    public void insertLanguage(Language language);
    public void deleteLanguage(Language language);
    public Collection getAllLanguages(); 
}
