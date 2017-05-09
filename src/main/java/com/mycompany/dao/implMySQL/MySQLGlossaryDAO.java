package com.mycompany.dao.implMySQL;

import com.mycompany.dao.GlossaryDAO;
import com.mycompany.model.Glossary;
import com.mycompany.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Alona
 */
public class MySQLGlossaryDAO implements GlossaryDAO{
    //private Connection connection;

    public MySQLGlossaryDAO() {
        //connection = MySQLDAOFactory.createConnection();
    }

    @Override
    public void insertGlossary(Glossary glossary) {
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into glossaries(glos_title, user_id) values (?, ? )");
                )
        {
            preparedStatement.setString(1, glossary.getTitle());
            preparedStatement.setInt(2, glossary.getUser().getUserId());           
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGlossary(Glossary glossary) {
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from glossaries where glos_id=?");
                )
        {
            preparedStatement.setInt(1, glossary.getGlosId());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection getUserGlossaries(User user) {
        List<Glossary> glossaries = new ArrayList<Glossary>();
        try (Connection connection = MySQLDAOFactory.createConnection();
                PreparedStatement statement = connection.prepareStatement("select * from glossaries where user_id=?");
                )
            {
            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Glossary glossary = new Glossary();
                glossary.setGlosId(rs.getInt("glos_id"));
                glossary.setTitle(rs.getString("glos_title"));
                glossary.setUser(user);
                glossaries.add(glossary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return glossaries;
    }
    
}
