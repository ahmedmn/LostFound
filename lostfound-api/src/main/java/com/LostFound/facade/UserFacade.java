package com.LostFound.facade;

import com.LostFound.dto.UserDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by bokos on 23/11/2016.
 */
public interface UserFacade {

    void registerUser(UserDTO user, String unencryptedPassword) throws NoSuchAlgorithmException;

    List<UserDTO> getAllUsers();

    boolean login(UserDTO user, String password) throws NoSuchAlgorithmException;

    boolean isAdmin(UserDTO user);

    UserDTO findUserById(Long userId);

    UserDTO findUserByEmail(String email);

    UserDTO findUserByName(String name);
}