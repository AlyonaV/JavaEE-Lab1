package com.mycompany.dao;

import com.mycompany.model.Word;

/**
 *
 * @author Alona
 */
public interface WordDAO {
    public void insertWord(Word word);
    public void deleteWord(Word word);
    public Word findWord(String wordTitle);
}
