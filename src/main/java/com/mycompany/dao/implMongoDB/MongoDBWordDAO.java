package com.mycompany.dao.implMongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mycompany.dao.WordDAO;
import com.mycompany.model.Word;

/**
 *
 * @author Alona
 */
public class MongoDBWordDAO implements WordDAO{
    private DB db;
    DBCollection col;

    public MongoDBWordDAO(DB db) {
        this.db = db;
        col = db.getCollection("words");
    }
    
    private static DBObject createDBObject(Word word){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        
        builder.append("_id", word.getWordId());
        builder.append("title", word.getTitle());
        builder.append("definition", word.getDefinition());
        builder.append("transcription", word.getTranscription());
        builder.append("pos", word.getPos());
        return builder.get();
    }
    
    private static Word parseDBObject(BasicDBObject dbObject){
        Integer id = Integer.parseInt(dbObject.getString("_id"));
        String title = dbObject.getString("title");
        String definition = dbObject.getString("definition");
        String transcription = dbObject.getString("transcription");
        String pos = dbObject.getString("pos");
        
        Word word = new Word();
        word.setTitle(title);
        word.setTranscription(transcription);
        word.setDefinition(definition);
        word.setWordId(id);
        word.setPos(pos);
        
        return word;
    }
    
    @Override
    public void insertWord(Word word) {
        DBObject wordDoc = createDBObject(word);
        WriteResult result = col.insert(wordDoc);        
    }

    @Override
    public void deleteWord(Word word) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", word.getWordId()).get();
        WriteResult result = col.remove(query);
    }

    @Override
    public Word findWord(String wordTitle) {
        DBObject query = BasicDBObjectBuilder.start().add("title", wordTitle).get();
        DBCursor cursor = col.find(query);
        Word word = new Word();
        while(cursor.hasNext()){
            BasicDBObject res = (BasicDBObject)cursor.next();
            word = parseDBObject(res);
        }
        return word;
    }
}
