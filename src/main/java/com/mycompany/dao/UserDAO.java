package com.mycompany.dao;

import com.mycompany.model.User;
import java.util.Collection;

/**
 *
 * @author Alona
 */
public interface UserDAO {
    public void insertUser(User user);
    public void deleteUser(User user);
    public User findUser(int userId);
    public void updateUser(User user);
    public Collection getAllUsers();
}
