package com.LostFound.service.facade;

import com.LostFound.dto.UserDTO;
import com.LostFound.dto.UserLoginDTO;
import com.LostFound.entity.User;
import com.LostFound.facade.UserFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by bokos on 25/11/2016.
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    public void registerUser(UserDTO user, String unencryptedPassword) throws NoSuchAlgorithmException {
        User userEntity = beanMappingService.mapTo(user, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        user.setId(userEntity.getId());
    }

    public List<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    public boolean login(UserLoginDTO user) {
        return userService.login(
                userService.findUserById(user.getUserId()), user.getPassword());
    }


    public boolean isAdmin(UserDTO user) {
        return userService.isAdmin(beanMappingService.mapTo(user, User.class));
    }

    public UserDTO findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    public UserDTO findUserByName(String name) {
        User user = userService.findUserByName(name);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }


}
