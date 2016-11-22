package com.LostFound.service;

import com.LostFound.dao.UserDAO;
import com.LostFound.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Bokos
 */
@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
