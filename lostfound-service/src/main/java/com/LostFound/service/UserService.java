package com.LostFound.service;

import com.LostFound.entity.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface UserService {

    void registerUser(User user, String unencryptedPassword) throws NoSuchAlgorithmException;

    List<User> getAllUsers();

    boolean login(User user, String password) throws NoSuchAlgorithmException;

    boolean isAdmin(User user);

    User findUserById(Long userId);

    User findUserByEmail(String email);

    User findUserByName(String name);
}