package com.mycompany.dao;

import com.mycompany.model.Glossary;
import com.mycompany.model.User;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public interface GlossaryDAO {
    public void insertGlossary(Glossary glossary);
    public void deleteGlossary(Glossary glossary);
    public Collection getUserGlossaries(User user);
}
