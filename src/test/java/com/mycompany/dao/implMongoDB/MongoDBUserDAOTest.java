package com.mycompany.dao.implMongoDB;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alona
 */
public class MongoDBUserDAOTest {
    
    private UserDAO userDAO;
    
    public MongoDBUserDAOTest() {
        userDAO = MongoDBDAOFactory.getInstance().getUserDAO();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of insertUser method, of class MongoDBUserDAO.
     */
    @org.junit.Test
    public void testInsertUser() {
        System.out.println("insertUser");
        User user = new User();
        user.setEmail("email@e.com");
        user.setPassword("123");
        user.setUsername("name");
        
        userDAO.insertUser(user);
        ArrayList<User> users = new ArrayList<>(userDAO.getAllUsers());
        assertEquals("Email of inserted fail",user.getEmail(), users.get(users.size()-1).getEmail());
        assertEquals("Password of inserted fail",user.getPassword(), users.get(users.size()-1).getPassword());
        assertEquals("Username of inserted fail",user.getUsername(), users.get(users.size()-1).getUsername());
    }

    /**
     * Test of deleteUser method, of class MongoDBUserDAO.
     */
    @org.junit.Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        ArrayList<User> users = new ArrayList<>(userDAO.getAllUsers());
        for(User us:users)
            userDAO.deleteUser(us);

        ArrayList<User> usersNew = new ArrayList<>(userDAO.getAllUsers());
        assertEquals(0, usersNew.size());
    }

    /**
     * Test of updateUser method, of class MongoDBUserDAO.
     */
    @org.junit.Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User userUpd = new User();
        userUpd.setEmail("emailUpd@e.com");
        userUpd.setPassword("123Upd");
        userUpd.setUsername("nameUpd");
        userDAO.insertUser(userUpd);
        userUpd.setEmail("updated@u.com");
        userUpd.setPassword("UpdatedPass");
        userUpd.setUsername("usernameUpdated");
        userDAO.updateUser(userUpd);
        ArrayList<User> users = new ArrayList<>(userDAO.getAllUsers());
    }

    /**
     * Test of getAllUsers method, of class MongoDBUserDAO.
     */
    @org.junit.Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        User user1 = new User();
        user1.setEmail("email1@e.com");
        user1.setPassword("1231");
        user1.setUsername("name1");
        
        User user2 = new User();
        user2.setEmail("email2@e.com");
        user2.setPassword("1232");
        user2.setUsername("name2");
        
        User user3 = new User();
        user3.setEmail("email3@e.com");
        user3.setPassword("1233");
        user1.setUsername("name3");
        
        userDAO.insertUser(user1);
        userDAO.insertUser(user2);
        userDAO.insertUser(user3);
        ArrayList<User> users = new ArrayList<>(userDAO.getAllUsers());
        Collection result = userDAO.getAllUsers();
        assertEquals(3, users.size());
    }
    
}
