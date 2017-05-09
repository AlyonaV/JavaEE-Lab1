package com.mycompany.dao.implMySQL;

import com.mycompany.dao.LanguageDAO;
import com.mycompany.model.Language;
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
public class MySQLLanguageDAO implements LanguageDAO{
    //private Connection connection;

    public MySQLLanguageDAO() {
        //connection = MySQLDAOFactory.createConnection();
    }
    
    @Override
    public void insertLanguage(Language language) {
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into languages ('language_title') values (?)");
                )
        {
            preparedStatement.setString(1, language.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteLanguage(Language language) {
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from lenguages where language_id = ?");
                )
        {
            preparedStatement.setInt(1, language.getLanguageId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Collection getAllLanguages() {
        List<Language> languages = new ArrayList<Language>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from languages");
                )
        {
            while(rs.next()){
                Language lang = new Language();
                lang.setLanguageId(rs.getInt("language_id"));
                lang.setTitle(rs.getString("language_title"));
                languages.add(lang);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return languages;
    }
    
}
