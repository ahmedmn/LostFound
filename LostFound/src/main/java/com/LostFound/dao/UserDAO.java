package com.LostFound.dao;

import com.LostFound.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Martin
 */
@Repository
public interface UserDAO {
    public void create(User user);
    public void update(User user);
    public void delete(User user);
    public User findById(Long id);
    public User findByName(String name);
    public User findByEmail(String email);
    public List<User> findAll();
}
