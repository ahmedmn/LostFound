package com.LostFound.service;

import com.LostFound.dao.UserDAO;
import com.LostFound.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author Bokos
 */
@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDao;


    public void registerUser(User user, String unencryptedPassword) throws NoSuchAlgorithmException {
        user.setPasswordHash(createHash(unencryptedPassword));
        userDao.create(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public boolean login(User user, String password) throws NoSuchAlgorithmException {
        return validatePassword(password, user.getPasswordHash());
    }

    public boolean isAdmin(User user) {
        return user.getAdmin();
    }

    public User findUserById(Long userId) {
        return userDao.findById(userId);
    }

    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    private static String createHash(String password) throws NoSuchAlgorithmException {
        if(password==null) throw new IllegalArgumentException("password is null");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hash, StandardCharsets.UTF_8);
    }

    private static boolean validatePassword(String password, String correctHash) throws NoSuchAlgorithmException {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");

        return createHash(password) == correctHash;
    }


}
