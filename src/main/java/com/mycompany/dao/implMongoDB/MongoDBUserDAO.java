package com.mycompany.dao.implMongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public class MongoDBUserDAO implements UserDAO{
    private DB db;
    DBCollection col;

    public MongoDBUserDAO(DB db) {
        this.db = db;
        col = db.getCollection("users");
    }
    
    private static DBObject createDBObject(User user){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        
        //builder.append("_id", user.getUserId());
        builder.append("username", user.getUsername());
        builder.append("email", user.getEmail());
        builder.append("password", user.getPassword());
        return builder.get();
    }
    
    private static User parseDBObject(BasicDBObject dbObject){
        //Integer id = Integer.parseInt(dbObject.getString("_id"));
        String username = dbObject.getString("username");
        String email = dbObject.getString("email");
        String password = dbObject.getString("password");
        
        User user = new User();
        //user.setUserId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        
        return user;
    }
        
    @Override
    public void insertUser(User user) {
        DBObject userDoc = createDBObject(user);
        WriteResult result = col.insert(userDoc);
    }

    @Override
    public void deleteUser(User user) {
        DBObject query = BasicDBObjectBuilder.start().add("username", user.getUsername()).get();
        WriteResult result = col.remove(query);
    }

    @Override
    public User findUser(int userId) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", userId).get();
        DBCursor cursor = col.find(query);
        User user = new User();
        while(cursor.hasNext()){
            BasicDBObject res = (BasicDBObject)cursor.next();
            user = parseDBObject(res);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", user.getUserId()).get();
        DBObject userDoc = createDBObject(user);
        DBCollection col = db.getCollection("users");
        WriteResult result = col.update(query, userDoc);
    }

    @Override
    public Collection getAllUsers() {
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<User> users = new ArrayList<>();
        while(cursor.hasNext()){
            BasicDBObject res = (BasicDBObject)cursor.next();
            User user = parseDBObject(res);
            users.add(user);
        }
        return users;
    }
}
