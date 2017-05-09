package com.mycompany.dao.implMySQL;

import com.mycompany.dao.WordDAO;
import com.mycompany.model.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alona
 */
public class MySQLWordDAO implements WordDAO{

    //private Connection connection;

    public MySQLWordDAO() {
        //connection = MySQLDAOFactory.createConnection();
    }
    
    @Override
    public void insertWord(Word word) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into words(definition, transcription, word_pos) values (?,?,?)");
            )
        {
            preparedStatement.setString(1, word.getDefinition());
            preparedStatement.setString(2, word.getTranscription());
            preparedStatement.setInt(3, word.getWordId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteWord(Word word) {
        try (Connection connection = MySQLDAOFactory.createConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement("delete from words where word_id=?");
            )
        {
            preparedStatement.setInt(1, word.getWordId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Word findWord(String wordTitle) {
        Word word = new Word();
        
        try (Connection connection = MySQLDAOFactory.createConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement("select * from words where title = ?");
                )
        {
            preparedStatement.setString(1, wordTitle);
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()){
            word.setWordId(rs.getInt("word_id"));
            word.setTitle(rs.getString("title"));
            word.setDefinition(rs.getString("definition"));
            word.setTranscription(rs.getString("transcription"));
            word.setPos(rs.getString("pos"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return word;
    }
    
}
