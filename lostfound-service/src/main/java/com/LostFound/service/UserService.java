package com.LostFound.service;

import com.LostFound.entity.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Bokos
 */
@Service
public interface UserService {

    void registerUser(User user, String unencryptedPassword);

    List<User> getAllUsers();

    boolean login(User user, String password);

    boolean isAdmin(User user);

    User findUserById(Long userId);

    User findUserByEmail(String email);

    User findUserByName(String name);
}