package com.LostFound.facade;

import com.LostFound.dto.UserDTO;
import com.LostFound.dto.UserLoginDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Bokos
 */
public interface UserFacade {

    void registerUser(UserDTO user, String unencryptedPassword);

    List<UserDTO> getAllUsers();

    boolean login(UserLoginDTO user);

    boolean isAdmin(UserDTO user);

    UserDTO findUserById(Long userId);

    UserDTO findUserByEmail(String email);

    UserDTO findUserByName(String name);
}